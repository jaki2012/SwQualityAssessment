package com.tongji409.util.log;

import com.alibaba.fastjson.JSONObject;
import com.tongji409.domain.SysLogModel;
import com.tongji409.util.config.SysProperties;
import com.tongji409.util.mongodb.MongoOper;
import com.tongji409.util.tools.Tools;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

//~--- classes ----------------------------------------------------------------

/**
 * @title  DLogger.java
 * @desc   日志工具类
 *
 * @author         Cogent   (brilliantree@126.com)
 * @version        v1.0.0, 2016-01-15
 */
public class DLogger {

    // 操作ID
    private String id="";

    // 操作标记
    private int step = 0;

    /** logger */
    static Logger logger;

    /** request func */
    private String func = "-";

    /** request user */
    private int user = 0;

    /** request client */
    private String client = "";

    /** response code */
    private String result = "";

    /** response note */
    private String resultnote = "";

    /** request time */
    private String time = "";

    /** request starttime */
    private long starttime = 0;

    /** request endtime */
    private long endtime = 0;

    /** request costtime */
    private long costtime = 0;

    /** 日志类 */
    private Class clazzname;

    /** requestData */
    private JSONObject requestdata;

    /** responseData */
    private JSONObject responseData;

    /** ************** temp group *********** */
    /** func */
    private String temp_func = "";

    /** start time */
    private long temp_start = 0;

    private DLogger() {}


    private DLogger(Logger logger) {
        this.id = UUID.randomUUID().toString();
        this.starttime = System.currentTimeMillis();
        this.logger    = logger;
    }

    private DLogger(Logger logger, JSONObject requestjson) {
        this.id = UUID.randomUUID().toString();
        this.starttime   = System.currentTimeMillis();
        this.logger      = logger;
        this.requestdata = requestjson;
    }

    private DLogger(Logger logger, JSONObject requestjson, int user, String func) {
        this.id = UUID.randomUUID().toString();
        this.starttime   = System.currentTimeMillis();
        this.logger      = logger;
        this.user        = user;
        this.func        = func;
        this.requestdata = requestjson;
    }

    /**
     * @title p
     * @descrb 打印信息
     * @param  msg  Object
     *
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public static void p(Object msg) {
        System.out.println(msg);
    }

    /**
     * @param clazzname Class
     * @return DLogger
     * @title getDLogger
     * @descrb
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public static DLogger getDLogger(Class clazzname) {
        return new DLogger(LoggerFactory.getLogger(clazzname));
    }

    /**
     * @param clazzname   Class
     * @param requestjson JSONObject
     * @return DLogger
     * @title getDLogger
     * @descrb
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public static DLogger getDLogger(Class clazzname, JSONObject requestjson) {
        return new DLogger(LoggerFactory.getLogger(clazzname), requestjson);
    }

    /**
     * @param clazzname   Class
     * @param requestjson JSONObject
     * @param user        String
     * @param func        String
     * @return DLogger
     * @title getDLogger
     * @descrb
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public static DLogger getDLogger(Class clazzname, JSONObject requestjson, int user, String func) {
        return new DLogger(LoggerFactory.getLogger(clazzname), requestjson, user, func);
    }

    /**
     * @title debug
     * @descrb 输出debug信息
     * @param  msg  Object
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void debug(Object msg) {
        logger.debug(msg.toString());
    }

    /**
     * @title  info
     * @descrb 输出info信息
     * @param  msg  Object
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void info(Object msg) {
        info(msg, null);
    }

    /**
     * log info msg
     * @param msg 信息
     * @param data 数据
     */
    public void info(Object msg, Object data) {
        logger.info(msg.toString());
        logToMongo(SysLogModel.LOG_LEVEL_INFO, msg, data);
    }

    /**
     * @title  warn
     * @descrb 输出warn信息
     * @param  msg  Object
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void warn(Object msg) {
        warn(msg, null);
    }

    public void warn(Object msg, Object data) {
        logger.warn(msg.toString());
        logToMongo(SysLogModel.LOG_LEVEL_WARN, msg, data);
    }

    /**
     * @title  sp
     * @descrb 打印信息
     * @param  msg  Object
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void sp(Object msg) {
        System.out.println(msg);
    }

    /**
     * @title error
     * @descrb 输出error信息
     * @param  errmsg  Object
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void error(Object errmsg) {
        logger.error("用户{}执行{}时，出现【" + errmsg.toString() + "】错误", user, func);
    }

    /**
     * 记录错误到日志文件
     * @param errmsg
     * @param e
     */
    public void errorToLogFile(Object errmsg, Exception e) {
        logger.error("用户{}执行{}时，出现【" + errmsg.toString() + "】错误", user, func, e);
    }

    /**
     * @title  error
     * @descrb 输出error信息和错误堆栈
     * @param  errmsg  Object
     * @param  e  Exception
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void error(Object errmsg, Exception e) {
        logger.error("用户{}执行{}失败，出现【" + e.getMessage() + "】错误", user, errmsg, e);
        logToMongo(SysLogModel.LOG_LEVEL_ERROR, "执行"+errmsg+"失败，出现【" + e.getMessage() + "】错误", e);
    }

    /**
     * @title  error
     * @descrb 输出error信息和错误sql
     * @param  errmsg  Object
     * @param  sql  String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void error(Object errmsg, String sql) {
        logger.error("用户{}执行{}是，出现【" + errmsg.toString() + "】错误, 错误SQL：{}", user, func, sql);
        logToMongo(SysLogModel.LOG_LEVEL_ERROR, "执行" + errmsg + "失败，SQL错误", sql);
    }

    /**
     * @title  error
     * @descrb 输出error信息、错误sql和错误堆栈
     * @param  errmsg  Object
     * @param  sql  String
     * @param  e  Exception
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void error(Object errmsg, String sql, Exception e) {
        logger.error("用户{}执行{}是，出现【" + errmsg.toString() + "】错误, 错误SQL：{}", user, func, sql, e);
        logToMongo(SysLogModel.LOG_LEVEL_ERROR, "执行" + errmsg + "失败，错误SQL:"+sql, e);
    }


    /**
     * 写入Mongo
     * @param level
     * @param logcontent
     */
    private void logToMongo(String level, Object logcontent, Object data){
        if(((SysProperties.LOG_TYPE&SysProperties.LOG_TYPE_MONGO)
                ==SysProperties.LOG_TYPE_MONGO) && !func.contains("getsyslog.php")){
            String clazz = Thread.currentThread() .getStackTrace()[3].getClassName();
            String method = Thread.currentThread() .getStackTrace()[3].getMethodName();

            Document log = new  Document()
                    .append(SysLogModel.LOG_OPID, this.id)
                    .append(SysLogModel.LOG_OPSTEP, step)
                    .append(SysLogModel.LOG_LEVEL, level)
                    .append(SysLogModel.LOG_FUNC, this.func)
                    .append(SysLogModel.LOG_CLASS, clazz)
                    .append(SysLogModel.LOG_METHOD, method)
                    .append(SysLogModel.LOG_USER, this.user)
                    .append(SysLogModel.LOG_TIME, Tools.formatTime(System.currentTimeMillis() + ""))
                    .append(SysLogModel.LOG_MSG, logcontent)
                    ;

            if(data != null){
                if(data instanceof Exception){
                    ByteArrayOutputStream buf = new ByteArrayOutputStream();
                    ((Exception)data).printStackTrace(new java.io.PrintWriter(buf, true));
                    String  expMessage = buf.toString();
                    try {
                        buf.close();
                    } catch (IOException e) {
                        errorToLogFile("记录错误到mongo", e);
                    }
                    log.append(SysLogModel.LOG_DATA, ((Exception)data).getMessage()+"\n"+expMessage);
                }
                else{
                    log.append(SysLogModel.LOG_DATA, data);
                }
            }

            step++;

            // 写日志
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MongoOper.addToMongo(SysLogModel.LOG_TBNAME_INFO, log);
                }
            }).start();
        }
    }

    /**
     * @title start
     * @descrb
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void start() {
        info("用户"+user+"执行"+func+"操作", this.requestdata);
    }

    /**
     * @title  finish
     * @descrb 结束
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void finish() {
        info("用户"+user+"执行"+func+"完成",this.responseData);
        step=99;
        info("用户"+user+"执行"+func+"完成， 耗时" + (System.currentTimeMillis() - starttime) + "ms",System.currentTimeMillis() - starttime);
        this.temp_start = 0;
    }

    /**
     * @title  start
     * @descrb
     * @param  temp_func  String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void temp_start(String temp_func) {
        this.temp_func  = temp_func;
        this.temp_start = System.currentTimeMillis();
    }

    /**
     * @title  finish
     * @descrb 结束
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void temp_finish() {
        debug("【" + temp_func + "】执行完成， 耗时" + (System.currentTimeMillis() - temp_start) + "ms");
        this.temp_start = 0;
    }


    /********************* Getters and Setters ********************/

    /**
     * @title getClient
     * @descrb
     * @return String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public String getClient() {
        return client;
    }

    /**
     * @title setClient
     * @descrb
     * @param  client  String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * @title getCosttime
     * @descrb
     * @return long
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public long getCosttime() {
        return costtime;
    }

    /**
     * @title setCosttime
     * @descrb
     * @param  costtime  long
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setCosttime(long costtime) {
        this.costtime = costtime;
    }

    /**
     * @title  getEndtime
     * @descrb
     * @return long
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public long getEndtime() {
        return endtime;
    }

    /**
     * @title  setEndtime
     * @descrb
     * @param  endtime  long
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    /**
     * @title  getFunc
     * @descrb
     * @return String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public String getFunc() {
        return func;
    }

    /**
     * @title  setFunc
     * @descrb
     * @param  func  String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setFunc(String func) {
        this.func = func;
    }

    // Getters and Setters

    /**
     * @title  getLogger
     * @descrb
     * @return Logger
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * @title  setLogger
     * @descrb
     * @param  logger  Logger
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * @title  getRequestdata
     * @descrb
     * @return JSONObject
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public JSONObject getRequestdata() {
        return requestdata;
    }

    /**
     * @title  setRequestdata
     * @descrb
     * @param  requestdata  JSONObject
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setRequestdata(JSONObject requestdata) {
        this.requestdata = requestdata;
    }

    /**
     * @title  getResponseData
     * @descrb
     * @return JSONObject
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public JSONObject getResponseData() {
        return responseData;
    }

    /**
     * @title  setResponseData
     * @descrb
     * @param  responseData  JSONObject
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setResponseData(JSONObject responseData) {
        this.responseData = responseData;
    }

    /**
     * @title  getResult
     * @descrb
     * @return String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public String getResult() {
        return result;
    }

    /**
     * @title  setResult
     * @descrb
     * @param  result  String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @title  getResultnote
     * @descrb
     * @return String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public String getResultnote() {
        return resultnote;
    }

    /**
     * @title  setResultnote
     * @descrb
     * @param  resultnote  String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setResultnote(String resultnote) {
        this.resultnote = resultnote;
    }

    /**
     * @title  getStarttime
     * @descrb
     * @return long
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public long getStarttime() {
        return starttime;
    }

    /**
     * @title  setStarttime
     * @descrb
     * @param  starttime  long
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    /**
     * @title  getTime
     * @descrb
     * @return String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public String getTime() {
        return time;
    }

    /**
     * @title  setTime
     * @descrb
     * @param  time  String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @title  getUser
     * @descrb
     * @return String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public int getUser() {
        return user;
    }

    /**
     * @title  setUser
     * @descrb
     * @param  user  String
     *
     * @author  Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-15
     */
    public void setUser(int user) {
        this.user = user;
    }
}

