package com.tongji409.website.dao.impl;

import com.tongji409.website.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by lijiechu on 16/11/28.
 */
public class BaseDaoImpl<T,PK extends Serializable> implements BaseDao<T,PK>{

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> clazz; // 【实体类对应的Class对象】

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 保留指定clatt值的接口【通过子类显示调用父类的构造函数来指定】
     *
     * @param clazz
     */
    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public PK save(T entity) {
        Session session = this.sessionFactory.getCurrentSession();
        return (PK)session.save(entity);
    }

    @Override
    public T get(PK id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(clazz,id);
    }

    @Override
    public void update(T entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entity);
    }

    @Override
    public void delete(T entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    @Override
    public boolean deleteById(PK id) {
        T entity = get(id);
        if(null == entity){
            return false;
        } else{
            delete(entity);
            return true;
        }
    }
}
