package com.tongji409.website.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tongji409.domain.Task;
import com.tongji409.util.log.DLogger;
import com.tongji409.website.dao.TaskDao;
import com.tongji409.website.services.support.ServiceSupport;

import java.util.Date;

/**
 * Created by lijiechu on 16/11/15.
 */

public class TaskService extends ServiceSupport{

    private TaskDao taskDao;

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

    public int taskCount(){
        return taskDao.getAllUser().size();
    }

    public void addTask(){
        try {
            taskDao.addTask();
            this.packageResultJson();
        } catch (Exception e) {
            log.error("添加任务", e);

            packageError("添加任务失败！\n原因:" + e.getMessage());
        }
    }

    public String getAllTasks() {
        return JSONArray.toJSONString(taskDao.getAllTasks());
    }

    public void addTask(Task task) {
        try {
            taskDao.addTask(task);
            packageResultJson();
        } catch (Exception e) {
            log.error("添加任务", e);

            packageError("添加任务失败！\n原因:" + e.getMessage());
        }
    }

    public void addTask(int id){
        try {
            taskDao.addTask(id);
            this.packageResultJson();
        } catch (Exception e) {
            log.error("添加任务", e);

            packageError("添加任务失败！\n原因:" + e.getMessage());
        }
    }

    public void startTask(String projectName, String projectVersion, String projectPath){

        Task newTask = new Task();
        newTask.setStartTime(new Date());
        newTask.setTaskState(1);
        newTask.setProjectName(projectName);
        newTask.setProjectVersion(projectVersion);

        try {
            //向数据库添加新启动的作业
            taskDao.addTask(newTask);
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
            taskDao.addTask(newTask);
            this.packageResultJson();
        } catch (Exception e) {
            log.error("创建任务", e);

            packageError("创建任务失败！\n原因:" + e.getMessage());
        }

    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }
}
