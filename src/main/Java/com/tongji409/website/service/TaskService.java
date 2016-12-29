package com.tongji409.website.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tongji409.DimensionCalculator;
import com.tongji409.domain.Metrics;
import com.tongji409.domain.Module;
import com.tongji409.domain.StaticDefect;
import com.tongji409.domain.Task;
import com.tongji409.util.config.StaticConstant;
import com.tongji409.util.task.TaskPool;
import com.tongji409.website.dao.StaticDefectDao;
import com.tongji409.website.dao.TaskDao;
import com.tongji409.website.service.support.ServiceSupport;
import metrics.Dimension;
import metrics.MetricsEvaluator;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lijiechu on 16/11/15.
 */

public class TaskService extends ServiceSupport {

    private TaskDao taskDao;
    private StaticDefectDao staticDefectDao;
    private FileSystemService fileSystemService;
    private TaskPool taskPool;
    private MachineService machineService;

    public void countTask() {
        try {
            List<Task> tasks = taskDao.getAll();
            this.resultdata.put("tasknums", tasks.size());
            packageResultJson();
        } catch (Exception e) {
            log.error("获取任务数量", e);
            packageError("获取任务数量失败！\n原因:" + e.getMessage());
        }
    }


//    public String getAllTasks() {
//        return JSONArray.toJSONString(taskDao.getAllTasks());
//    }

    public void addTask(Task task) {
        try {
            taskDao.save(task);
            packageResultJson();
        } catch (Exception e) {
            log.error("添加任务", e);

            packageError("添加任务失败！\n原因:" + e.getMessage());
        }
    }

    public void getTasks() {
        List<Task> tasks = taskDao.getAll();
        String strString = JSON.toJSONString(tasks, SerializerFeature.WriteDateUseDateFormat);
        JSONArray jsonArrayTasks = JSONArray.parseArray(strString);
        this.resultdata.put("result", jsonArrayTasks);
        this.resultdata.put("chtest", "我爱你");

        try {
            this.packageResultJson();
        } catch (Exception e) {
            log.error("获取所有任务", e);
            packageError("获取所有任务失败！\n原因:" + e.getMessage());
        }
    }


//    public void addTask(int id){
//        try {
//            taskDao.addTask(id);
//            this.packageResultJson();
//        } catch (Exception e) {
//            log.error("添加任务", e);
//
//            packageError("添加任务失败！\n原因:" + e.getMessage());
//        }
//    }

    public void enqueueTask(String projectName, String projectVersion, String projectPath) {

        Task newTask = new Task();
        newTask.setStartTime(new Date());
        newTask.setTaskState(1);
        newTask.setProjectName(projectName);
        newTask.setProjectVersion(projectVersion);

        try {
            //向数据库添加新启动的作业
            taskDao.save(newTask);
            this.packageResultJson();
        } catch (Exception e) {
            log.error("创建任务", e);

            packageError("创建任务失败！\n原因:" + e.getMessage());
        }

        //此处启用线程异步处理代码分析工作
        //...
        //分析完成后修改TASK记录 设置endTime taskState

    }

    @SuppressWarnings("unchecked")
    public JSONObject enqueueTask(Task newTask) {
        newTask.setStartTime(new Date());
        //1:已完成 2:排队中 3:分析中 4:已失败
        newTask.setTaskState(2);
        JSONObject metricsObj = new JSONObject();


        try {
            //向数据库添加新启动的作业
            int taskID = (int) taskDao.save(newTask);
            //设置返回参数
            Task savedTask = (Task) taskDao.get(taskID);
            ;
            String startTime = JSON.toJSONString(savedTask.getStartTime(), SerializerFeature.WriteDateUseDateFormat);
            this.resultdata.put("taskid", taskID);
            this.resultdata.put("starttime", dateQuotesTrim(startTime));
            this.resultdata.put("taskstate", savedTask.getTaskState());

//            this.sendAliMsg("石琨小姐",dateQuotesTrim(startTime),newTask.getProjectName());
            this.packageResultJson();

            // 任务新建完成 交给任务池去处理接下来的工作
            String path = savedTask.getPath();
            // 1. 39维度分析
            // 下载
            fileSystemService.saveArchiveToFile(path, savedTask.getArchivePath());

            // 解压
            String pth = fileSystemService.unzipProject(path);
            // 分析
            // 如果缺少Jar包,请注释此行代码
            if (pth != null) {
                boolean hasDefect = false;
                DimensionCalculator calculator = new DimensionCalculator();
                calculator.calculateFiles(fileSystemService.listServerFiles(pth));
                java.io.File[] files = fileSystemService.listServerFiles(pth);

                List<List<MetricsEvaluator>> projectMetricsList = calculator.getProjectMetrics();
                JSONArray projectArray = new JSONArray();
                for (List<MetricsEvaluator> evaluators : projectMetricsList) {
                    JSONArray fileModuleArray = new JSONArray();
                    for (MetricsEvaluator evaluator : evaluators) {
                        JSONObject moduleResult = new JSONObject();
                        evaluator.setModulePath(evaluator.getModulePath().replace(files[0].getAbsolutePath(), ""));
                        StringBuilder builder = new StringBuilder();
                        Metrics metrics = new Metrics();
                        for (Map.Entry<Dimension, Double> entry : evaluator.dimensions.entrySet()) {
                            Double value = entry.getValue();
                            builder.append(value);
                            builder.append(" ");
                            // 使用反射找到对应方法
                            try {
                                Method methodInt = metrics.getClass().getMethod("set" + entry.getKey(), int.class);
                                int setterValue = value.intValue();
                                methodInt.invoke(metrics, setterValue);
                            } catch (NoSuchMethodException e) {
                                Method methodFloat = metrics.getClass().getMethod("set" + entry.getKey(), float.class);
                                float setterValue = value.floatValue();
                                methodFloat.invoke(metrics, setterValue);
                            }
//                            String s = String.format("%-35s%-5s", entry.getKey(), entry.getValue());
//                            System.out.println(s);
                        }
                        hasDefect = machineService.CallPython(builder.toString());
                        // 保存模块
                        Module module = new Module();
                        module.setDefective(hasDefect);
                        module.setModuleName(evaluator.moduleName);
                        int moduleId = (int) taskDao.save(module);
                        // 保存维度
                        metrics.setModuleID(moduleId);
                        int metricsId = (int) taskDao.save(metrics);
                        moduleResult.put("moduleMetadata", module);
                        moduleResult.put("metricsData", metrics);
                        moduleResult.put("path", evaluator.getModulePath());
                        fileModuleArray.add(moduleResult);
                    }
                    projectArray.add(fileModuleArray);
                }
                metricsObj.put("SoftwareMetrics", projectArray);
            }

            // 删除
            java.io.File[] files = fileSystemService.listServerFiles(pth);
            FileUtils.deleteDirectory(new File(files[0].getParent()));
            FileUtils.deleteDirectory(new File(fileSystemService.getServerFileRootPath() + path + "_dir"));
            // 2. 分析PMD缺陷
            // 如果你本机缺少PMD-JAR运行环境,请注释此行代码
//            analysePMDDefects(newTask);
            JSONObject normalResult = this.packageResultJson();
            metricsObj.put("statJson", normalResult);

        } catch (Exception e) {
            log.error("创建任务", e);

            packageError("创建任务失败！\n原因:" + e.getMessage());
        }
        return metricsObj;
    }

    @SuppressWarnings("all")
    public void analysePMDDefects(Task task) throws IOException {

        String path = task.getPath();
        String output;
        int moduleID = 0;
        String[] analyseResult;
        String command = StaticConstant.PMD_JAR_PATH + " pmd -d " + path + " -f text -R" + " " +
                StaticConstant.PMD_JAVA_RULESETS_PATH + "basic.xml";
        Process process = Runtime.getRuntime().exec(command);
        //Process process = Runtime.getRuntime().exec(" pmd -d /Users/lijiechu/Downloads/FileManager.java -f text -R /Users/lijiechu/Documents/pmd/pmd-java/target/classes/rulesets/java/comments.xml");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((output = bufferedReader.readLine()) != null) {
            analyseResult = output.split(":");
            StaticDefect staticDefect = new StaticDefect();
            staticDefect.setModuleID(moduleID++);
            staticDefect.setLocation(analyseResult[1]);
            staticDefect.setDescription(analyseResult[2].trim());
            staticDefectDao.save(staticDefect);
            System.out.println(output);
        }
    }

    //taskService的操作
    public void ensureNotLogin() {
        packageError("用户尚未登陆,无法进行此操作!");
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public StaticDefectDao getStaticDefectDao() {
        return staticDefectDao;
    }

    public void setStaticDefectDao(StaticDefectDao staticDefectDao) {
        this.staticDefectDao = staticDefectDao;
    }

    public TaskPool getTaskPool() {
        return taskPool;
    }

    public void setTaskPool(TaskPool taskPool) {
        this.taskPool = taskPool;
    }

    public FileSystemService getFileSystemService() {
        return fileSystemService;
    }

    public void setFileSystemService(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    public MachineService getMachineService() {
        return machineService;
    }

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }
}
