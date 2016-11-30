package com.tongji409.website.dao;

import com.tongji409.domain.User;

/**
 * Created by lijiechu on 16/11/30.
 */
public interface UserDao extends BaseDao {
    User findUserByName(String username);
}
