package com.tongji409;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.tongji409.domain.Task;

import java.util.Date;

/**
 * Created by lijiechu on 16/11/14.
 */
public class Client {

    public static void main(String[] args) {

        //读取hibernate.cfg.xml文件
        Configuration cfg = new Configuration().configure();

        //建立SessionFactory
        SessionFactory factory = cfg.buildSessionFactory();

        //取得session
        Session session = null;
        try {
            session = factory.openSession();
            //开启事务
            session.beginTransaction();
            Task task = new Task();
            task.setProjectName("Spring MVC");
            task.setProjectVersion("2.1");
            task.setStartTime(new Date());
            task.setEndTime(new Date());
            task.setTaskState(2);


            //保存User对象
            session.save(task);

            //提交事务
            session.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            //回滚事务
            session.getTransaction().rollback();
        }finally {
            if (session != null) {
                if (session.isOpen()) {
                    //关闭session
                    session.close();
                }
            }
        }
    }
}
