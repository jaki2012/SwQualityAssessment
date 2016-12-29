package com.tongji409.website.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tongji409.DimensionCalculator;
import com.tongji409.domain.StaticDefect;
import com.tongji409.domain.Task;
import com.tongji409.util.config.StaticConstant;
import com.tongji409.util.task.TaskPool;
import com.tongji409.website.dao.StaticDefectDao;
import com.tongji409.website.dao.TaskDao;
import com.tongji409.website.services.support.ServiceSupport;
import metrics.MetricsEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

/**
 * Created by lijiechu on 16/11/15.
 */

public class TaskService extends ServiceSupport {

    private TaskDao taskDao;
    private StaticDefectDao staticDefectDao;
    private FileSystemService fileSystemService;
    private TaskPool taskPool;


    //    public int taskCount(){
//        return taskDao.getAllUser().size();
//    }
//
//    public void addTask(){
//        try {
//            taskDao.addTask();
//            this.packageResultJson();
//        } catch (Exception e) {
//            log.error("添加任务", e);
//
//            packageError("添加任务失败！\n原因:" + e.getMessage());
//        }
//    }

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
            this.resultdata.put("taskid", taskID);
            this.resultdata.put("starttime", savedTask.getStartTime());
            this.resultdata.put("taskstate", savedTask.getTaskState());

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

                DimensionCalculator calculator = new DimensionCalculator();
                calculator.calculateFiles(fileSystemService.listServerFiles(pth));
                List<List<MetricsEvaluator>> projectMetricsList = calculator.getProjectMetrics();
                metricsObj.put("SoftwareMetrics", projectMetricsList);

//                for (List<MetricsEvaluator> moduleMetricList : projectMetricsList) {
//                    for (MetricsEvaluator item : moduleMetricList) {
//                        System.out.println(item.moduleName);
//                    }
//                }
            }

            // 删除
            java.io.File[] files = fileSystemService.listServerFiles(pth);
            for (java.io.File file : files)
                file.delete();

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
            //staticDefectDao.addStaticDefect(staticDefect);
            staticDefectDao.save(staticDefect);
            System.out.println(output);
        }
    }

    //taskService的操作
    public void ensureNotLogin() {
        packageError("用户尚未登陆,无法进行此操作!");
    }


    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
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
}
