package com.tongji409.website.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tongji409.domain.StaticDefect;
import com.tongji409.domain.Task;
import com.tongji409.util.config.StaticConstant;
import com.tongji409.util.log.DLogger;
import com.tongji409.website.dao.StaticDefectDao;
import com.tongji409.website.dao.TaskDao;
import com.tongji409.website.services.support.ServiceSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

/**
 * Created by lijiechu on 16/11/15.
 */

public class TaskService extends ServiceSupport{

    private TaskDao taskDao;
    private StaticDefectDao staticDefectDao;

    public TaskService(){

    }

    public TaskService(DLogger log){
        super(log);
    }

    public TaskService(DLogger log, String funcname) {
        super(log, funcname);
    }

    public TaskService(DLogger log, String funcname, JSONObject requestJson) {
        super(log, funcname, requestJson);
    }

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

    public void getTasks(){
        List<Task> tasks = taskDao.getAll();
        String strString = JSON.toJSONString(tasks, SerializerFeature.WriteDateUseDateFormat);
        JSONArray jsonArrayTasks = JSONArray.parseArray(strString);
        this.resultdata.put("result",jsonArrayTasks);

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

    public void startTask(String projectName, String projectVersion, String projectPath){

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

    public void startTask(Task newTask){
        newTask.setStartTime(new Date());
        newTask.setTaskState(1);
        try {
            //向数据库添加新启动的作业
            taskDao.save(newTask);
            //分析PMD缺陷,
            //注释这行代码 如果你本机缺少PMD-JAR运行环境
            analysePMDDefects(newTask);
            this.packageResultJson();
        } catch (Exception e) {
            log.error("创建任务", e);

            packageError("创建任务失败！\n原因:" + e.getMessage());
        }

    }

    public void analysePMDDefects(Task task) throws IOException {

        String path = task.getPath();
        String output;
        int moduleID = 0;
        String [] analyseResult;
        String command = StaticConstant.PMD_JAR_PATH + " pmd -d " + path + " -f text -R" +" " +
                StaticConstant.PMD_JAVA_RULESETS_PATH+"basic.xml";
        Process process = Runtime.getRuntime().exec(command);
        //Process process = Runtime.getRuntime().exec(" pmd -d /Users/lijiechu/Downloads/FileManager.java -f text -R /Users/lijiechu/Documents/pmd/pmd-java/target/classes/rulesets/java/comments.xml");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while((output = bufferedReader.readLine())!=null){
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
}
