package com.tongji409.website.dao.impl;

import com.tongji409.website.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lijiechu on 16/11/28.
 */
public class BaseDaoImpl<T,PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T,PK> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> clazz; // 【实体类对应的Class对象】

//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }



    //精简代码 获取session的函数
    public Session getSession(){
//        在使用HibernateDaoSupport前获取session的办法
//        Session session = this.sessionFactory.getCurrentSession();
        Session session = currentSession();
        return session;
    }

    @Autowired
    public void setTemplate(HibernateTemplate hibernateTemplate){
        setHibernateTemplate(hibernateTemplate);
    }

    /**
     * 保留指定clazz值的接口【通过子类显示调用父类的构造函数来指定】
     *
     * @param clazz
     */
    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public PK save(T entity) {
        Session session = this.getSession();
        return (PK)session.save(entity);
    }

    @Override
    public T get(PK id) {
        Session session = this.getSession();
        return session.get(clazz,id);
    }

    @Override
    public void update(T entity) {
        Session session = this.getSession();
        session.update(entity);
    }

    @Override
    public void delete(T entity) {
        Session session = this.getSession();
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

    @Override
    public List<T> getAll(){
        return getHibernateTemplate().loadAll(clazz);
    }
}
