package com.tongji409.website.dao.impl;

import com.tongji409.domain.User;
import com.tongji409.website.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lijiechu on 16/11/30.
 */

@Repository(value = "userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    //通过调用父类的构造函数指定clazz值，即实体类的类类型
    public UserDaoImpl() {
        super(User.class);
    }

    @Transactional
    public User findUserByName(String username){
        Session session = getSession();
        String hql = "from User where username=:n";
        Query query = session.createQuery(hql);
        query.setString("n",username);
        User user = (User) query.uniqueResult();
        return user;
    }
}
