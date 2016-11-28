package com.tongji409.website.dao;

import java.io.Serializable;

/**
 * Created by lijiechu on 16/11/18.
 */

public interface BaseDao<T, PK extends Serializable>{

    /******************基本的CRUD操作:创建(Create),检索(Retrieve),更新Update,删除(Delete)*********************/

    /**
     * 存储实体到数据库,并返回主键
     * @param entity
     * @return PK
     */
    public PK save(T entity);

    /**
     * 根据主键获取实体。如果没有相应的实体，返回null。
     * @param id
     * @return T
     */
    public T get(PK id);

    /**
     * 更新实体
     * @param entity
     */
    public void update(T entity);

    /**
     * 删除数据库中对应的实体
     * @param entity
     */
    public void delete(T entity);

    /**
     * 根据指定的主键删除数据库中对应的实体
     * @param id
     */
    public boolean deleteById(PK id);

}
