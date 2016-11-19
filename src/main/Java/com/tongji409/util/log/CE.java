package com.tongji409.util.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by lijiechu on 16/11/18.
 */
public class CE {

    public static Document log = null;

    //初始化构造函数
    public CE(){
        PropertyConfigurator.configure("log4j.properties");
    }

    //全局日志控制器
    public static Logger lg = Logger.getRootLogger();

    //电话号码验证缓存列
    public static Map<String, String> telMap = new HashMap<String, String>();


    public static String getTelCode(String telNumber) {
        return telMap.get(telNumber);
    }

    public static void addTelNumber(String telNumber, String telCode) {
        telMap.put(telNumber, telCode);
    }

    /**
     * 输出调试信息
     * @param debugInfo
     */
    public static void lgDbg(String debugInfo){
        lg.debug(debugInfo);
    }

    /**
     * 输出记录信息
     * @param infoInfo
     */
    public static void lgInfo(String infoInfo){
        lg.info(infoInfo);
    }

    /**
     * 输出警告信息
     * @param warnInfo
     * @author Cogent Cui
     * 2015年7月2日 下午12:43:14
     */
    public static void lgWarn(String warnInfo){
        lg.warn(warnInfo);
    }

    /**
     * 写入日志
     * @param log
     * @author Cogent Cui
     * 2015年6月2日 下午2:32:26
     */
    public static void lgInfo(Document log){
//		MongoDBUtil.getDB().getCollection(SysLogModel.LOG_TBNAME_INFO).insertOne(log);
    }

    /**
     * 打印调试信息
     * @Title: sp
     * @param @param info
     * @author: Cogent Cui
     */
    public static void sp(Object info){
        System.out.println("-----syso----->\n "+info + "\n");
    }

    /**
     * 输出自动调试信息
     * @param debugInfo
     */
    private static int i = 1;
    public static void lgInfo(){
        lg.info("------调试标志：" + i);
        i++;
    }

    /**
     * 输出错误信息
     * @Title: lgErr
     * @param @param errInfo
     * @author: Cogent Cui
     */
    public static void lgErr(String errInfo){
        lg.error(errInfo);
    }

    /**
     * 输出错误信息
     * @param FuncName
     * @param e
     */
    public static void lgErr(String FuncName, Exception e){
        lg.error(FuncName+"出现错误，详情如下：\n --> "
                +e.getMessage(), e);
//		e.printStackTrace();
    }


    /**
     * 输出错误信息
     * @param FuncName
     * @param e
     */
    public static void lgErr(String FuncName, String sql, Exception e){
        lg.error(FuncName+"出现错误，详情如下：\n -->SQL: " + sql +"\n"
                +e.getMessage(), e);
//		e.printStackTrace();
    }



}