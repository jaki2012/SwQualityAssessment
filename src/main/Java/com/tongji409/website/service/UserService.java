package com.tongji409.website.service;

import com.tongji409.domain.User;
import com.tongji409.website.dao.UserDao;

/**
 * Created by lijiechu on 16/11/30.
 */
public class UserService {

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserDao userDao;

    public User getUserByName(String username){
        return userDao.findUserByName(username);
    }
}
