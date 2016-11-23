package com.tongji409.website.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tongji409.domain.Task;
import com.tongji409.util.log.DLogger;
import com.tongji409.website.dao.TaskDao;
import com.tongji409.website.services.support.ServiceSupport;

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

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }
}
