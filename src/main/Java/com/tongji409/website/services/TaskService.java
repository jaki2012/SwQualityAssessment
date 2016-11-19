package com.tongji409.website.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongji409.util.log.DLogger;
import com.tongji409.website.dao.TaskDao;
import com.tongji409.website.services.support.ServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

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
            log.error("插入图片索引信息", e);
            this.packageError("插入图片索引信息失败！\n原因：" + e.getMessage());
        }
    }

    public void addTask(int id){
        try {
            taskDao.addTask(id);
            this.packageResultJson();
        } catch (Exception e) {
            log.error("插入图片索引信息", e);
            this.packageError("插入图片索引信息失败！\n原因：" + e.getMessage());
        }
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }
}
