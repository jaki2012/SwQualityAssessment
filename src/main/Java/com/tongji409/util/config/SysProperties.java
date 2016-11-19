package com.tongji409.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by lijiechu on 16/11/18.
 */
@Component
public class SysProperties {

    //拦截方法等级规则
    public static String URLPOWER_WEBSER;

    // 运行环境-开发
    public static final String EVR_EVR_DEV="DEV";
    // 运行环境-生产
    public static final String EVR_EVR_PDT ="PDT";
    // 客户端类型-APP
    public static final String EVR_CLIENT_APP = "APP";
    // 客户端类型-WEB
    public static final String EVR_CLIENT_WEB = "WEB";



    // 日志类型-文件
    public static final int LOG_TYPE_FILE = 1;
    // 日志类型-mongoDB
    public static final int LOG_TYPE_MONGO = 2;
    // 日志类型-混合
    public static final int LOG_TYPE_FILE_AND_MONGO = 3;
    // 运行环境
    public static String EVR_EVR;
    // 客户端类型
    public static String EVR_CLIENT;
    // 业务参数-敏感词过滤级别,0-4,敏感度依次升高
    public static String BUSS_SSTLEVEL;
    // 日志类型
    public static int LOG_TYPE;

    // 访问地址
    public static String URL_HOST;

    // 图片地址
    public static String URL_PIC;
    // 图片地址-缩略
    public static String URL_PIC_THB;
    // 图片地址-压缩
    public static String URL_PIC_CPR;
    // 图片地址-原图
    public static String URL_PIC_SRC;
    //系统图片
    public static String URL_PIC_SYS;

    // HTML地址
    public static String URL_HTML;
    //APP端内链网址
    public static String URL_HTML_APP;
    // HTML地址-店铺
    public static String URL_HTML_SHOP;
    // HTML地址-商品
    public static String URL_HTML_GOODS;
    // HTML地址-帖子
    public static String URL_HTML_POST;
    // HTML地址-帖子分享
    public static String URL_HTML_POST_SHAREURL;

    //升级文件地址
    public static String URL_UPTFILE;

    // 图片存储
    public static String FILE_PIC;
    // 图片存储-缩略
    public static String FILE_PIC_THB;
    // 图片存储-压缩
    public static String FILE_PIC_CPR;
    // 图片存储-原图
    public static String FILE_PIC_SRC;
    //系统图片存储
    public static String FILE_PIC_SYS;

    //系统图片存储
    public static String FILE_PIC_SYS_WATERMARKER;


    // HTML存储
    public static String FILE_HTML;
    // HTML存储-商品
    public static String FILE_HTML_GOODS;
    // HTML存储-帖子
    public static String FILE_HTML_POST;
    // HTML存储-店铺
    public static String FILE_HTML_SHOP;

    // 升级文件存储
    public static String FILE_UPTFILE;


    // mongDB-HOST
    public static String MGDB_HOST;
    // mongDB-PORT
    public static String MGDB_PORT;
    // mongDB-USER
    public static String MGDB_USER;
    // mongDB-PWD
    public static String MGDB_PWD;
    // mongDB-NAME
    public static String MGDB_NAME;
    // mongDB-TIMEOUT
    public static int MGDB_TIMEOUT;


    // MySQL-HOST
    public static String MYSQL_HOST;
    // MySQL-PORT
    public static String MYSQL_PORT;
    // MySQL-USER
    public static String MYSQL_USER;
    // MySQL-PWD
    public static String MYSQL_PWD;
    // MySQL-NAME
    public static String MYSQL_NAME;
    // MySQL-TIMEOUT
    public static int MYSQL_TIMEOUT;


    //同城圈列表排序模式
    public static String CITYCIRCLE_RETRIEVAL_TYPE;

    /****************
     * 业务参数
     *******************/
    //积分商城ID
    public static int BUSS_SCORESHOP_ID = 1;

    /****************
     * 微信端参数
     *******************/

    //ping++ APP_KEY
    public static String PING_APP_KEY = "";

    //ping++ API_KEY_TEST
    public static String PING_API_KEY_TEST = "";

    //ping++ API_KEY_LIVE
    public static String PING_API_KEY_LIVE = "";

    //点啦微信公众号appid
    public static String SYS_WX_ID = "";

    //点啦微信公众号appsecret
    public static String SYS_WX_APPSECRET = "";

    //CIA手机验证appid
    public static String SYS_CIA_APP_ID = "";

    //CIA手机验证authkey
    public static String SYS_CIA_AUTHKEY = "";

    //融云appkey
    public static String RONGCLOUD_APP_KEY="";

    //融云appsecret
    public static String RONGCLOUD_APP_SECRET="";

    //百度apistorekey
    public static String BAIDU_APISTORE_KEY="";

    /**********************************
     *  腾讯云参数
     **********************************/
    // APP_ID
    public static int COS_APP_ID=0;

    // SECRET_ID
    public static String COS_SECRET_ID="";

    // SECRET_KEY
    public static String COS_SECRET_KEY="";

    // APP_ID
    public static int UPIC_APP_ID=0;

    // BUCKET_ID
    public static String UPIC_BUCKET_ID="";

    // BUCKET_NAME
    public static String UPIC_BUCKET_NAME="";

    // SECRET_ID
    public static String UPIC_SECRET_ID="";

    // SECRET_KEY
    public static String UPIC_SECRET_KEY="";


    /**********************************
     *  BEECLOUD 参数
     **********************************/

    // APP_ID
    public static String BC_APP_ID = "";

    // APP_SECRET
    public static String BC_APP_SECRET = "";

    // APP_SECRET_TEST
    public static String BC_APP_SECRET_TEST = "";

    // APP_SECRET_MASTER
    public static String BC_APP_SECRET_MASTER = "";


    /**********************************
     *  又拍云 参数
     **********************************/

    // UPYUN_URL
    public static String UPYUN_URL = "";

    // UPYUN_UPURL
    public static String UPYUN_UPURL = "";

    // UPYUN_REQURL
    public static String UPYUN_REQURL = "";

    // UPYUN_BUCKET
    public static String UPYUN_BUCKET = "";

    // UPYUN_APISECRET
    public static String UPYUN_APISECRET = "";



    /**********************************
     *  默认参数
     **********************************/
    //默认头像
    public static String URL_DEFAULT_HEADIMG="";




    @Value("#{evrconfig['ping_app_key']}")
    public void setPingAppKey(String pingAppKey) {
        PING_APP_KEY = pingAppKey;
    }

    @Value("#{evrconfig['ping_api_key_test']}")
    public void setPingApiKeyTest(String pingApiKeyTest) {
        PING_API_KEY_TEST = pingApiKeyTest;
    }

    @Value("#{evrconfig['ping_api_key_live']}")
    public void setPingApiKeyLive(String pingApiKeyLive) {
        PING_API_KEY_LIVE = pingApiKeyLive;
    }

    @Value("#{evrconfig['wx_appid']}")
    public void setSysWxId(String sysWxId) {
        SYS_WX_ID = sysWxId;
    }

    @Value("#{evrconfig['wx_appsecret']}")
    public void setSysWxAppsecret(String sysWxAppsecret) {
        SYS_WX_APPSECRET = sysWxAppsecret;
    }

    @Value("#{evrconfig['appId_CIA']}")
    public void setSysCiaAppId(String sysCiaAppId) {
        SYS_CIA_APP_ID = sysCiaAppId;
    }

    @Value("#{evrconfig['authKey_CIA']}")
    public void setSysCiaAuthkey(String sysCiaAuthkey) {
        SYS_CIA_AUTHKEY = sysCiaAuthkey;
    }

    @Value("#{evrconfig['evr.evr']}")
    public void setEVR_EVR(String EVR_EVR) {
        this.EVR_EVR = EVR_EVR;
    }

    @Value("#{evrconfig['evr.client']}")
    public void setEVR_CLIENT(String EVR_CLIENT) {
        this.EVR_CLIENT = EVR_CLIENT;
    }

    @Value("#{evrconfig['log.type']}")
    public void setLogType(int logType) {
        LOG_TYPE = logType;
    }

    @Value("#{evrconfig['url.host']}")
    public void setUrlHost(String urlHost) {
        URL_HOST = urlHost;
    }

    @Value("#{evrconfig['url.pic']}")
    public void setUrlPic(String urlPic) {
        URL_PIC = urlPic;
    }

    @Value("#{evrconfig['url.pic.thb']}")
    public void setUrlPicThb(String urlPicThb) {
        URL_PIC_THB = urlPicThb;
    }

    @Value("#{evrconfig['url.pic.cpr']}")
    public void setUrlPicCpr(String urlPicCpr) {
        URL_PIC_CPR = urlPicCpr;
    }

    @Value("#{evrconfig['url.pic.src']}")
    public void setUrlPicSrc(String urlPicSrc) {
        URL_PIC_SRC = urlPicSrc;
    }

    @Value("#{evrconfig['url.pic.sys']}")
    public void setUrlPicSys(String urlPicSys) {
        URL_PIC_SYS = urlPicSys;
    }

    @Value("#{evrconfig['url.html']}")
    public void setUrlHtml(String urlHtml) {
        URL_HTML = urlHtml;
    }

    @Value("#{evrconfig['url.html.app']}")
    public void setUrlHtmlApp(String urlHtmlApp) {
        URL_HTML_APP = urlHtmlApp;
    }

    @Value("#{evrconfig['url.html.shop']}")
    public void setUrlHtmlShop(String urlHtmlShop) {
        URL_HTML_SHOP = urlHtmlShop;
    }

    @Value("#{evrconfig['url.html.goods']}")
    public void setUrlHtmlGoods(String urlHtmlGoods) {
        URL_HTML_GOODS = urlHtmlGoods;
    }

    @Value("#{evrconfig['url.html.post']}")
    public void setUrlHtmlPost(String urlHtmlPost) {
        URL_HTML_POST = urlHtmlPost;
    }

    @Value("#{evrconfig['url.html.post.share']}")
    public void setUrlHtmlPostShareurl(String urlHtmlPostShareurl) {
        URL_HTML_POST_SHAREURL = urlHtmlPostShareurl;
    }

    @Value("#{evrconfig['file.pic']}")
    public void setFilePic(String filePic) {
        FILE_PIC = filePic;
    }

    @Value("#{evrconfig['file.pic.thb']}")
    public void setFilePicThb(String filePicThb) {
        FILE_PIC_THB = filePicThb;
    }

    @Value("#{evrconfig['file.pic.cpr']}")
    public void setFilePicCpr(String filePicCpr) {
        FILE_PIC_CPR = filePicCpr;
    }

    @Value("#{evrconfig['file.pic.src']}")
    public void setFilePicSrc(String filePicSrc) {
        FILE_PIC_SRC = filePicSrc;
    }

    @Value("#{evrconfig['file.pic.sys']}")
    public void setFilePicSys(String filePicSys) {
        FILE_PIC_SYS = filePicSys;
    }

    @Value("#{evrconfig['file.pic.sys.watermarker']}")
    public void setFilePicSysWatermarker(String filePicSysWatermarker) {
        FILE_PIC_SYS_WATERMARKER = filePicSysWatermarker;
    }

    @Value("#{evrconfig['file.html']}")
    public void setFileHtml(String fileHtml) {
        FILE_HTML = fileHtml;
    }

    @Value("#{evrconfig['file.html.goods']}")
    public void setFileHtmlGoods(String fileHtmlGoods) {
        FILE_HTML_GOODS = fileHtmlGoods;
    }

    @Value("#{evrconfig['file.html.post']}")
    public void setFileHtmlPost(String fileHtmlPost) {
        FILE_HTML_POST = fileHtmlPost;
    }

    @Value("#{evrconfig['file.html.shop']}")
    public void setFileHtmlShop(String fileHtmlShop) {
        FILE_HTML_SHOP = fileHtmlShop;
    }

    @Value("#{evrconfig['mgdb.host']}")
    public void setMgdbHost(String mgdbHost) {
        MGDB_HOST = mgdbHost;
    }

    @Value("#{evrconfig['mgdb.port']}")
    public void setMgdbPort(String mgdbPort) {
        MGDB_PORT = mgdbPort;
    }

    @Value("#{evrconfig['mgdb.user']}")
    public void setMgdbUser(String mgdbUser) {
        MGDB_USER = mgdbUser;
    }

    @Value("#{evrconfig['mgdb.pwd']}")
    public void setMgdbPwd(String mgdbPwd) {
        MGDB_PWD = mgdbPwd;
    }

    @Value("#{evrconfig['mgdb.name']}")
    public void setMgdbName(String mgdbName) {
        MGDB_NAME = mgdbName;
    }

    @Value("#{evrconfig['mgdb.timeout']}")
    public void setMgdbTimeout(int mgdbTimeout) {
        MGDB_TIMEOUT = mgdbTimeout;
    }

    @Value("#{evrconfig['mysql.host']}")
    public void setMysqlHost(String mysqlHost) {
        MYSQL_HOST = mysqlHost;
    }

    @Value("#{evrconfig['mysql.port']}")
    public void setMysqlPort(String mysqlPort) {
        MYSQL_PORT = mysqlPort;
    }

    @Value("#{evrconfig['mysql.user']}")
    public void setMysqlUser(String mysqlUser) {
        MYSQL_USER = mysqlUser;
    }

    @Value("#{evrconfig['mysql.pwd']}")
    public void setMysqlPwd(String mysqlPwd) {
        MYSQL_PWD = mysqlPwd;
    }

    @Value("#{evrconfig['mysql.dbname']}")
    public void setMysqlName(String mysqlName) {
        MYSQL_NAME = mysqlName;
    }

    @Value("#{evrconfig['mysql.timeout']}")
    public void setMysqlTimeout(int mysqlTimeout) {
        MYSQL_TIMEOUT = mysqlTimeout;
    }

    @Value("#{evrconfig['url.uptfiles']}")
    public void setUrlUptfile(String urlUptfile) {
        URL_UPTFILE = urlUptfile;
    }

    @Value("#{evrconfig['file.uptfiles']}")
    public void setFileUptfile(String fileUptfile) {
        FILE_UPTFILE = fileUptfile;
    }

    @Value("#{evrconfig['urlpower_websvr']}")
    public void setUrlpowerWebser(String urlpowerWebser) {
        URLPOWER_WEBSER = urlpowerWebser;
    }

    @Value("#{evrconfig['rongcloud_app_key']}")
    public void setRongcloudAppKey(String rongcloudAppKey) {
        RONGCLOUD_APP_KEY = rongcloudAppKey;
    }

    @Value("#{evrconfig['rongcloud_app_secret']}")
    public void setRongcloudAppSecret(String rongcloudAppSecret) {
        RONGCLOUD_APP_SECRET = rongcloudAppSecret;
    }

    @Value("#{evrconfig['citycircle.retrievaltype']}")
    public void setCitycircleRetrievalType(String citycircleRetrievalType) {
        CITYCIRCLE_RETRIEVAL_TYPE = citycircleRetrievalType;
    }
    @Value("#{evrconfig['baidu_apistore_key']}")
    public void setBaiduApistoreKey(String baiduApistoreKey) {
        BAIDU_APISTORE_KEY = baiduApistoreKey;
    }

    @Value("#{evrconfig['url.default.headimg']}")
    public void setUrlDefaultHeadimg(String urlDefaultHeadimg) {
        URL_DEFAULT_HEADIMG = urlDefaultHeadimg;
    }

    @Value("#{evrconfig['cos.app.id']}")
    public void setCosAppId(int cosAppId) {
        COS_APP_ID = cosAppId;
    }

    @Value("#{evrconfig['cos.secret.id']}")
    public void setCosSecretId(String cosSecretId) {
        COS_SECRET_ID = cosSecretId;
    }

    @Value("#{evrconfig['cos.secret.key']}")
    public void setCosSecretKey(String cosSecretKey) {
        COS_SECRET_KEY = cosSecretKey;
    }

    @Value("#{evrconfig['upic.app.id']}")
    public void setUpicAppId(int upicAppId) {
        UPIC_APP_ID = upicAppId;
    }
    @Value("#{evrconfig['upic.secret.id']}")
    public void setUpicSecretId(String upicSecretId) {
        UPIC_SECRET_ID = upicSecretId;
    }
    @Value("#{evrconfig['upic.secret.key']}")
    public void setUpicSecretKey(String upicSecretKey) {
        UPIC_SECRET_KEY = upicSecretKey;
    }
    @Value("#{evrconfig['upic.bucket.id']}")
    public void setUpicBucketId(String upicBucketId) {
        UPIC_BUCKET_ID = upicBucketId;
    }
    @Value("#{evrconfig['upic.bucket.name']}")
    public void setUpicBucketName(String upicBucketName) {
        UPIC_BUCKET_NAME = upicBucketName;
    }

    @Value("#{evrconfig['bc.app.id']}")
    public void setBcAppId(String bcAppId) {
        BC_APP_ID = bcAppId;
    }
    @Value("#{evrconfig['bc.app.secret']}")
    public void setBcAppSecret(String bcAppSecret) {
        BC_APP_SECRET = bcAppSecret;
    }
    @Value("#{evrconfig['bc.app.secret.test']}")
    public void setBcAppSecretTest(String bcAppSecretTest) {
        BC_APP_SECRET_TEST = bcAppSecretTest;
    }
    @Value("#{evrconfig['bc.app.secret.master']}")
    public void setBcAppSecretMaster(String bcAppSecretMaster) {
        BC_APP_SECRET_MASTER = bcAppSecretMaster;
    }

    @Value("#{evrconfig['upyun.url']}")
    public void setUpyunUrl(String upyunUrl) {
        UPYUN_URL = upyunUrl;
    }
    @Value("#{evrconfig['upyun.upurl']}")
    public void setUpyunUpurl(String upyunUpurl) {
        UPYUN_UPURL = upyunUpurl;
    }
    @Value("#{evrconfig['upyun.requrl']}")
    public void setUpyunRequrl(String upyunRequrl) {
        UPYUN_REQURL = upyunRequrl;
    }
    @Value("#{evrconfig['upyun.bucket']}")
    public void setUpyunBucket(String upyunBucket) {
        UPYUN_BUCKET = upyunBucket;
    }
    @Value("#{evrconfig['upyun.apisecret']}")
    public void setUpyunApisecret(String upyunApisecret) {
        UPYUN_APISECRET = upyunApisecret;
    }
}
