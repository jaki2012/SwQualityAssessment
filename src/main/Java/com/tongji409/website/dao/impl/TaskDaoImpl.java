package com.tongji409.website.dao.impl;

import com.tongji409.domain.Task;
import com.tongji409.domain.User;
import com.tongji409.website.dao.TaskDao;
import com.tongji409.website.vo.MetricsInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Task> getTasksByUserId(int userId) {
        Task task = new Task();
        task.setUserID(userId);
        return this.getHibernateTemplate().findByExample(task);
    }

    @Override
    public Task getTaskById(int taskId) {
        Task task = new Task();
        task.setUserID(taskId);
        // 根据Id查找
        return this.getHibernateTemplate().get(Task.class, taskId);
    }

    @Override
    public List<Object[]> getMetricsInfoById(int taskId) {
        String hql = "select m2.moduleName, m2.defective, f.fileName, m1 from Metrics m1, Module m2, File f where m1.moduleID=m2.moduleID and m2.fileID=f.fileID and m1.taskID = %d order by f.fileName";
        String finalHql = String.format(hql, taskId);
        return (List<Object[]>) this.getHibernateTemplate().find(finalHql);
    }
}
