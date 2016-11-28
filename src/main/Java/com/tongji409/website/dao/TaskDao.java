package com.tongji409.website.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tongji409.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by lijiechu on 16/11/15.
 */

public interface TaskDao extends BaseDao {


//    public List<Task> getAllUser() {
//        String hsql = "from Task";
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery(hsql);
//
//        return query.list();
//    }
//
//    public void addTask() {
//        Session session = null;
//        try {
//            session = sessionFactory.getCurrentSession();
//            //开启事务
//            //session.beginTransaction();
//            Task task = new Task();
//            task.setProjectName("Task Scanner");
//            task.setProjectVersion("3.0");
//            task.setStartTime(new Date());
//            task.setEndTime(new Date());
//            task.setTaskState(3);
//
//
//            //保存User对象
//            session.save(task);
//
//            //提交事务
//            //  session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //回滚事务
//            session.getTransaction().rollback();
//        } finally {
////            if (session != null) {
////                if (session.isOpen()) {
////                    //关闭session
////                    session.close();
////                }
////            }
//        }
//    }
//
//    public List<Task> getAllTasks() {
//        try {
//            Session session = sessionFactory.getCurrentSession();
//
//            Query query = session.createQuery("from Task");
//            List<Task> tasks = query.list();
//            for (Task task : tasks) {
//                System.out.println(task.getTaskID() + " State:" + task.getTaskState());
//            }
//            return tasks;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public void addTask(Task task) {
//        if (null == task)
//            return;
//        Session session = null;
//        try {
//            session = sessionFactory.getCurrentSession();
//            session.save(task);
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.getTransaction().rollback();
//        }
//    }
//
//    public void addTask(int i) {
//        Session session = null;
//        try {
//            session = sessionFactory.getCurrentSession();
//            //开启事务
//            //session.beginTransaction();
//            Task task = new Task();
//            task.setProjectName("Task Scanner");
//            task.setProjectVersion("3.0");
//            task.setStartTime(new Date());
//            //task.setEndTime(new Date());
//            task.setTaskState(i);
//
//
//            //保存User对象
//            session.save(task);
//
//            //提交事务
//            //  session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            //回滚事务
//            session.getTransaction().rollback();
//        } finally {
////            if (session != null) {
////                if (session.isOpen()) {
////                    //关闭session
////                    session.close();
////                }
////            }
//        }
//    }
}
