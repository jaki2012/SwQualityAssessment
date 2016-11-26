package com.tongji409.util.config;

/**
 * Created by lijiechu on 16/11/18.
 */
public class StaticConstant {



    /****************************** DTO标准字段BEGIN ***************************************/

    public static final  String DEL_TYPE_UNEFF = "1";   // 更新无效化
    public static final  String DEL_TYPE_DELETE = "2";  // 删除
    public static final  String DEL_TYPE_UPDATE = "3";  // 更新


    /****************************** DTO标准字段BEGIN ***************************************/

    public static final String ORG_USER_ID = "ORG_USER_ID";     // 用户ID
    public static final String ORG_USER_NAME = "ORG_USER_NAME";     // 用户名
    public static final String ORG_USER_TYPE = "ORG_USER_TYPE";     // 用户类型
    public static final String ORG_USER_MNGACODE = "ORG_USER_MNGACODE";     // 用户管理区域
    public static final String ORG_USER_STATE = "ORG_USER_STATE";     // 用户状态


    /****************************** 角色TYPE值 BEGIN ***************************************/

    public static final int ROLE_TYPE_USER = 1;     // 用户
    public static final int ROLE_TYPE_SHOP = 2;     // 商家
    public static final int ROLE_TYPE_MANAGER = 64;     // 管理员
    public static final int ROLE_TYPE_SUPERMANAGER = 128;     // 超级管理员
    public static final int ROLE_TYPE_ROOTMANAGER = 1024;     // 根管理员


    /****************************** 数据结果码BEGIN ***************************************/

    public static final String RELUST_SUCC = "100";     // 成功
    public static final String RELUST_ERROR = "299";    // 未知错误
    public static final String RELUST_NOUSER = "298";   // 用户信息丢失
    public static final String RELUST_OUTAREA = "297";  // 超出覆盖范围
    public static final String RELUST_NONE = "296";     // 无符合条件的信息
    public static final String RELUST_NOPOWER = "295";  // 无操作权限
    public static final String RELUST_ERRFIELD = "294"; // 错误字段
    public static final String RELUST_NOPOSITION = "293";  // 位置坐标信息丢失
    public static final String RELUST_NOOPENID = "292";  // OPENID信息丢失

    /**************************业务结果码***********************************/
    public static final String RELUST_NOPOST = "201";  // 咨询已经删除

    public static final String RELUST_POSTCHECK = "202";  // 咨询待审核

    /******************************JDBC相关BEGIN***************************************/
    public static final String JDBC_DESC_KEY = "0001000200030004";

    /**数据库类型**/
    public static final String JDBC_DATASOURCE_TYPE_KEY = "datasource.type";

    public static final String JDBC_DATASOURCE_DRIVERCLASSNAME_KEY = "datasource.driverClassName";

    public static final String JDBC_DATASOURCE_URL_KEY = "datasource.url";

    public static final String JDBC_DATASOURCE_USERNAME_KEY = "datasource.username";

    public static final String JDBC_DATASOURCE_PASSWORD_KEY = "datasource.password";

    /******************************JDBC相关END***************************************/


    /******************************客户端类型BEGIN***************************************/
    public static final String APP_CLIENTTYPE_ANDROID = "ANDROID";
    public static final String APP_CLIENTTYPE_IOS = "IOS";

    public static final String APP_CLIENTTYPE_SUFFIX_ANDROID = ".apk";
    public static final String APP_CLIENTTYPE_SUFFIX_IOS = ".ipa";

    public static final String APP_CLIENTNAME_DLCOMM = "DLCOMM";
    public static final String APP_CLIENTNAME_WX = "BJWX";
    /******************************客户端类型END***************************************/



    /******************************基本数据BEGIN***************************************/
    public static final String DB_DEFAULT_TIME = "1970-01-01 00:00:00";
    public static final int SYS_STATE_WAIT = 0; // 尚未生效
    public static final int SYS_STATE_PASS = 1; // 通过
    public static final int SYS_STATE_APPLY = 2; // 待审核
    public static final int SYS_STATE_VETO = 97; // 拒绝
    public static final int SYS_STATE_UNEFF = 99; // 删除

    /******************************基本数据BEGIN***************************************/
    public static final String PMD_JAR_PATH = "/Users/lijiechu/Documents/pmd-bin/pmd-bin-5.6.0-SNAPSHOT/bin/run.sh";
    public static final String PMD_JAVA_RULESETS_PATH = "/Users/lijiechu/Documents/pmd/pmd-java/target/classes/rulesets/java/";
}
