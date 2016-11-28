package com.tongji409.website.dao.impl;

import com.tongji409.domain.StaticDefect;
import com.tongji409.website.dao.StaticDefectDao;
import org.springframework.stereotype.Repository;

/**
 * Created by lijiechu on 16/11/28.
 */
@Repository(value = "staticDefectDao")
public class StaticDefectDaoImpl extends BaseDaoImpl implements StaticDefectDao {

    //通过调用父类的构造函数指定clazz值，即实体类的类类型
    public StaticDefectDaoImpl() {
        super(StaticDefect.class);
    }
}
