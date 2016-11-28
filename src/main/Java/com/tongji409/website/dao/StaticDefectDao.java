package com.tongji409.website.dao;

import com.tongji409.domain.StaticDefect;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by lijiechu on 16/11/26.
 */

public interface StaticDefectDao extends BaseDao{

//    public void addStaticDefect(StaticDefect staticDefect) {
//        Session session = null;
//        try {
//            session = sessionFactory.getCurrentSession();
//            session.save(staticDefect);
//        } catch (Exception e) {
//            e.printStackTrace();
//            //回滚事务
//            session.getTransaction().rollback();
//        }
//    }
}
