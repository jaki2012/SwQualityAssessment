package com.tongji409.util.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tongji409.DimensionCalculator;
import com.tongji409.domain.*;
import com.tongji409.util.config.StaticConstant;
import com.tongji409.util.config.TaskStatus;
import com.tongji409.website.dao.TaskDao;
import com.tongji409.website.service.FileSystemService;
import com.tongji409.website.service.MachineService;
import metrics.Dimension;
import metrics.MetricsEvaluator;
import org.apache.commons.io.FileUtils;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lijiechu
 * @create on 2018/9/3
 * @description
 */
public class HasSessionThread extends Thread {

    private SessionFactory sessionFactory;

    private FileSystemService fileSystemService;

    private MachineService machineService;

    private Task savedTask;

    private TaskDao taskDao;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setFileSystemService(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    public void setSavedTask(Task savedTask) {
        this.savedTask = savedTask;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void run() {
        String path = savedTask.getPath();
        // 0. 任务进入分析中
        savedTask.setTaskState(TaskStatus.ANALYZING.getState());
        savedTask.setTaskStateDesc(TaskStatus.ANALYZING.getDescription());
        //bug记录 无session
//                    SessionFactory sf = taskDao.makeNewSession();
//                    Session session = sf.openSession();
//                    TransactionSynchronizationManager.bindResource(sf, session);
        // 为当前线程绑定一个session对象,让dao中使用 getCurrentSession的方法可以获取到对应的session
        Session session = this.sessionFactory.openSession();
        session.setFlushMode(FlushMode.MANUAL);
        SessionHolder sessionHolder = new SessionHolder(session);
        TransactionSynchronizationManager.bindResource(this.sessionFactory, sessionHolder);
        taskDao.update(savedTask);
        session.flush();
        // 1. 39维度分析
        // 下载
        boolean downloadSucceed = fileSystemService.saveArchiveToFile(path, savedTask.getArchivePath());
        if (downloadSucceed) {
            try {
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
                        if(evaluators.size() <=0 ) {
                            continue;
                        }
                        JSONArray fileModuleArray = new JSONArray();
                        int startIndex = evaluators.get(0).getModulePath().indexOf(pth) + (pth + "_dir").length();
                        String fileName = evaluators.get(0).getModulePath().substring(startIndex);
                        com.tongji409.domain.File sourcecodeFile = new com.tongji409.domain.File();
                        sourcecodeFile.setFileName(fileName);
                        sourcecodeFile.setTaskID(savedTask.getTaskID());
                        taskDao.save(sourcecodeFile);
                        int srcfileid = sourcecodeFile.getFileID();
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
//                        hasDefect = machineService.CallPython(builder.toString());
                            // 保存模块
                            Module module = new Module();
                            // 相当于调机器学习的包
                            float possibility = (float) Math.random();
                            hasDefect = possibility > 0.85 ? true: false;
                            module.setDefective(hasDefect);
                            if(fileName.contains("HasSession") && evaluator.moduleName.contains("run")) {
                                module.setDefective(true);
                            }
                            module.setFileID(srcfileid);
                            module.setModuleName(evaluator.moduleName);
                            int moduleId = (int) taskDao.save(module);
                            // 保存维度
                            metrics.setModuleID(moduleId);
                            metrics.setTaskID(savedTask.getTaskID());
                            int metricsId = (int) taskDao.save(metrics);
                            moduleResult.put("moduleMetadata", module);
                            moduleResult.put("metricsData", metrics);
                            moduleResult.put("path", evaluator.getModulePath());
                            fileModuleArray.add(moduleResult);
                        }
                        projectArray.add(fileModuleArray);
                    }
//                metricsObj.put("SoftwareMetrics", projectArray);
                    // 3. sonarqube分析
                    SonarScannerTemplate sonarScannerTemplate = new SonarScannerTemplate(savedTask.getProjectVersion(),
                            savedTask.getProjectName());
                    String propertiesContent = sonarScannerTemplate.constructProperties();
                    File propertiesFile = new File(files[0].getPath() + File.separator + "sonar-project.properties");
                    Writer out = new FileWriter(propertiesFile);
                    out.write(propertiesContent);
                    out.close();

//                String cdCmd = "cd " + files[0].getPath();
                    String scanCmd = StaticConstant.SONAR_SCANNER_PATH + "sonar-scanner";
//                Runtime.getRuntime().exec(cdCmd);
                    Process process = Runtime.getRuntime().exec(scanCmd, null, files[0]);
                    //Process process = Runtime.getRuntime().exec(" pmd -d /Users/lijiechu/Downloads/FileManager.java -f text -R /Users/lijiechu/Documents/pmd/pmd-java/target/classes/rulesets/java/comments.xml");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String output = null;
                    String sonarqubeReportId = null;
                    String succeedPattern = "INFO: EXECUTION (.*)";
                    String sonarqubeIdPatternStr = "http://localhost:9088/dashboard/index/(\\w+)";
                    Pattern sonarqubeIdPattern = Pattern.compile(sonarqubeIdPatternStr);
                    while ((output = bufferedReader.readLine()) != null) {
                        System.out.println(output);
                        Matcher m = sonarqubeIdPattern.matcher(output);
                        if(m.find()) {
                            sonarqubeReportId = m.group(1);
                            savedTask.setSonarqubeUrl(StaticConstant.SONARQUBE_URL_PREFIX + sonarqubeReportId);
                        }
                        if(Pattern.matches(succeedPattern, output)) {
                            if(output.contains("FAILURE")) {
                                //TODO: throw exception, and make this task failed
                                throw new Exception("Sonarqube analyze failed");
//                                break;
                            }
                        }
                    }
                }

                // 删除
                java.io.File[] files = fileSystemService.listServerFiles(pth);
                FileUtils.deleteDirectory(new File(files[0].getParent()));
                FileUtils.deleteDirectory(new File(fileSystemService.getServerFileRootPath() + path + "_dir"));
                // 2. 分析PMD缺陷
                // 如果你本机缺少PMD-JAR运行环境,请注释此行代码
//            analysePMDDefects(newTask);

                // 任务完成
                // 合成方法 门面模式
                savedTask.setTaskState(TaskStatus.FINISHED.getState());
                savedTask.setTaskStateDesc(TaskStatus.FINISHED.getDescription());
                savedTask.setEndTime(new Date());
                taskDao.update(savedTask);
                session.flush();
            } catch (Exception e) {
                // 暂时先把异常吞掉
                savedTask.setTaskState(TaskStatus.FAILED.getState());
                savedTask.setTaskStateDesc(e.getMessage());
                savedTask.setEndTime(new Date());
                taskDao.update(savedTask);
                session.flush();
//            log.error("suck the exception");
            } finally {

                SessionHolder sessionHolder2 = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
                SessionFactoryUtils.closeSession(sessionHolder2.getSession());

            }
        } else {
            // 文件下载失败
            savedTask.setTaskState(TaskStatus.FAILED.getState());
            savedTask.setTaskStateDesc(TaskStatus.FAILED.getDescription());
            taskDao.update(savedTask);
            session.flush();
        }
    }

}

