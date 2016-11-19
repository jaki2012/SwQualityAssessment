package com.tongji409.util.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import com.tongji409.util.config.SysProperties;
import com.tongji409.util.log.CE;
/**
 * Created by lijiechu on 16/11/18.
 */
//数据库工具类
public class MongoUtil {
    private static MongoClient mongoClient = null;
    private static MongoDatabase db = null;

    private static String name = "";// 数据库名
    private static String host = "";// 主机名
    private static int port = 27017;// 端口号

//	private static int poolSize = 10;//连接池大小


    /**
     * 获取数据库连接
     * @Title: getDB
     * @Description:
     * @param @return
     * @return MongoDatabase
     * @throws
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年10月12日 上午8:51:00
     */
    public static MongoDatabase getDB() {
        if (db == null) {
            init();
        }
        return db;
    }

    /**
     * 初始化数据库连接
     * @Title: init
     * @Description:
     * @param
     * @return void
     * @throws
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年10月12日 上午8:51:12
     */
    private static void init() {
        try {
            //初始化系统配置参数
            host = SysProperties.MGDB_HOST;
            port = Integer.parseInt(SysProperties.MGDB_PORT);
            name = SysProperties.MGDB_NAME;

            mongoClient = new MongoClient(host, port);
            db = mongoClient.getDatabase(name);
        } catch (Exception e) {
            CE.lgErr("创建Mongo连接失败", e);
        }
    }

}