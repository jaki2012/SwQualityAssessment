package com.tongji409.website.dao.impl;

import com.tongji409.domain.Task;
import com.tongji409.website.dao.TaskDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by lijiechu on 16/11/28.
 */
@Repository(value = "taskDao")
public class TaskDaoImpl extends BaseDaoImpl implements TaskDao {

    //通过调用父类的构造函数指定clazz值，即实体类的类类型
    public TaskDaoImpl() {
        super(Task.class);
    }


    @Override
    public SessionFactory makeNewSession() {
        return getSessionFactory();
    }
}
