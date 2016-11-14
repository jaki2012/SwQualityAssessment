package com.tongji409.website.services;

import com.tongji409.website.dao.TaskDao;

/**
 * Created by lijiechu on 16/11/15.
 */

public class TaskService {
    private TaskDao taskDao;

    public int taskCount(){
        return taskDao.getAllUser().size();
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }
}
