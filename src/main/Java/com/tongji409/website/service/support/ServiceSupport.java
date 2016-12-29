package com.tongji409.website.service.support;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.tongji409.util.config.StaticConstant;
import com.tongji409.util.log.DLogger;
import com.tongji409.util.tools.Tools;
import org.springframework.stereotype.Repository;

/**
 * Created by lijiechu on 16/11/18.
 */
@Repository(value = "serviceSupport")
public class ServiceSupport {//} implements ServiceInterface {

    protected DLogger log;    // 日志控制器

    protected String funcname = "";    // 操作跟踪
    protected int dl_userid = 0;    // 操作跟踪

    protected int totalcount = -1;  // 条目数量

    protected JSONObject transJson = new JSONObject(); //内部数据传输json
    protected JSONObject requestJson = new JSONObject();    //传入JSON
    protected JSONObject responseJson = new JSONObject();    //输出JSON
    protected String encryptkey = "";    //加密key
    protected JSONObject requestJson_encry = new JSONObject();    //传入JSON加密
    protected String resultCode = "";
    protected String resultNote = "";
    protected String responseJson_encry = "";    //输出JSON加密
    protected String resultString = "";    // 查询结果
    protected JSONObject resultuser = new JSONObject();        //查询结果JSON
    protected JSONObject resultshop = new JSONObject();        //查询结果JSON
    protected JSONObject resultrunner = new JSONObject();    //查询结果JSON
    protected JSONObject resultdata = new JSONObject();        //查询结果JSON
    protected JSONArray resultdatas = new JSONArray();        //查询结果JSON
    protected long starttime = 0;    //当前时间
    private boolean isRSA = false; //加密标志

    public ServiceSupport() {

    }

    public ServiceSupport(DLogger log) {
        this.starttime = System.currentTimeMillis();
        this.log = log;
    }

    /**
     * 构造函数
     *
     * @param funcname
     */
    public ServiceSupport(DLogger log, String funcname) {
        this.starttime = System.currentTimeMillis();
        this.log = log;
        this.funcname = funcname;
    }

    public ServiceSupport(DLogger log, String funcname, JSONObject requestJson) {
        try {
            this.starttime = System.currentTimeMillis();
            this.log = log;
            this.funcname = funcname;
            this.requestJson = requestJson;
            if (funcname.contains(".u.")) {
                this.dl_userid = Tools.getOperUserIDStrict(this.requestJson);
            }
            log.info("requestJson:" + this.requestJson);

        } catch (Exception e) {
            log.error("接口父类跳转", e);
            this.packageResultJson("298", "抱歉，服务器提了一个问题，请稍后再试");
        }
    }


    /**
     * 获取结果
     */
    //@Override
    public String getResultJson() {
        String resJson = this.responseJson.size() > 0 ? this.responseJson.toString() : "";
        return resJson;
    }

    public String getResultDatas() {
        String resJson = "";
        // 判断是否为分页查询，如果存在条目数据
        if (totalcount != -1) {
            this.resultdata.put("total", totalcount);
            this.resultdata.put("rows", this.resultdatas);
            resJson = this.resultdata.toString();
        } else {
            resJson = this.resultdatas.toString();
        }
        // 触发日志
        this.packageResultJson();
        return resJson;
    }

    /**
     * 封装成功输出结果
     *
     * @param @return
     * @Title: packageResultJson
     * @author: Cogent Cui
     */
    public final JSONObject packageResultJson() {
        return packageResultJson(StaticConstant.RELUST_SUCC, "");
    }

    /**
     * 封装错误输出结果
     *
     * @param @return
     * @Title: packageError
     * @author: Cogent Cui
     */
    public final JSONObject packageError() {
        return packageResultJson(StaticConstant.RELUST_ERROR, "服务器繁忙，请稍候再试");
    }

    /**
     * 封装错误输出结果
     *
     * @param @return
     * @Title: packageError
     * @author: Cogent Cui
     */
    public final JSONObject packageError(String errmsg) {
        return packageResultJson(StaticConstant.RELUST_ERROR, errmsg);
    }

    /**
     * 封装URL跳转信息
     *
     * @param @return
     * @Title: packageUrl
     * @author: Cogent Cui
     */
    public final void packageUrl(String url) {
        this.resultdata.put("FORWARD_URL", url);
    }

    /**
     * 封装输出结果
     *
     * @param @param  resultCode
     * @param @return
     * @Title: packageResultJson
     * @author: Cogent Cui
     */
    protected final JSONObject packageResultJson(String resultCode) {
        return packageResultJson(resultCode, "");
    }

    /**
     * 封装输出结果
     *
     * @param @param  resultCode 结果代码
     * @param @return
     * @Title: packageResultJson
     * @author: Cogent Cui
     */
    protected final JSONObject packageResultJson(String resultCode, String resultNote) {

        this.resultCode = resultCode;
        this.resultNote = resultNote;

        responseJson.put("RESULT_CODE", resultCode);

        //封装校准时钟信息
        responseJson.put("RESULT_SERCLOCK", Tools.getDateTime());

        //封装用户信息
        if (resultuser.size() > 0) {
            responseJson.put("RESULT_USERINFO", this.resultuser);
        }

        //封装店铺信息
        if (resultshop.size() > 0) {
            responseJson.put("RESULT_SHOPINFO", this.resultshop);
        }

        //封装配送信息
        if (resultrunner.size() > 0) {
            responseJson.put("RESULT_RUNINFO", this.resultrunner);
        }

        //封装错误信息
        if (!resultNote.equals("")) {
            responseJson.put("RESULT_NOTE", resultNote);
        }

        //遇到错误，处理结果集信息
        if (!resultCode.equals("100")) {
            this.resultdata.clear();
            this.resultdatas.clear();
            this.resultuser.clear();
            this.resultshop.clear();
            this.resultrunner.clear();
        }


        //封装结果集
        if (!resultString.equals("")) {
            responseJson.put("RESULT_DATA", resultString);
        } else if (resultdatas.size() > 0) {
            responseJson.put("RESULT_DATA", resultdatas.size() > 0 ? resultdatas : "");
        } else {
            responseJson.put("RESULT_DATA", resultdata.size() > 0 ? resultdata : "");
        }

        log.sp(this.responseJson);

        //存储日志
        log.setResponseData(this.responseJson);
        log.finish();
        return responseJson;
    }


    /**
     * 获取请求字符串中的字段
     *
     * @param @param  key
     * @param @return
     * @Title: getJsonString
     * @author: Cogent Cui
     */
    protected String getJsonString(String key) {
        return getJsonString(key, this.requestJson);
    }


    /**
     * 获取JSON中的字符串
     *
     * @param @param  key
     * @param @param  jo
     * @param @return
     * @Title: getJsonString
     * @author: Cogent Cui
     */
    protected String getJsonString(String key, JSONObject jo) {
        if (jo.containsKey(key)) {
            return jo.getString(key);
        } else {
            return "";
        }
    }

    /**
     * 解决FastJson单独转换date为标准格式string后多出两个双引号的问题
     *
     * @param @param  date
     * @param @return date
     * @Title: dateQuotesTrim
     * @author: lijiechu
     */
    protected String dateQuotesTrim(String date){
        if(null == date || date.length()< 2){
            return "error date format";
        }
        return date.substring(1,date.length()-1);
    }

    /**
     * 阿里发送短信接口
     *
     * @param name
     * @param projectName
     * @param date
     * @param @return date
     * @Title: dateQuotesTrim
     * @author: lijiechu
     */
    protected void sendAliMsg(String name, String date, String projectName){
        String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "23563986";
        String secret = "c338161bfeab8ac71afbf29e62a2b1fc";
        String shortenDate = date.split(" ")[1];
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("同济众包");
        req.setSmsParamString("{\"name\":\""+name+"\",\"time\":\""+shortenDate+"\",\"project\":\""+projectName+"\"}");
        req.setRecNum("18217769863");
        req.setSmsTemplateCode("SMS_33890131");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }


    //Getters and Setters

    /**
     * @return the requestJson
     */
    public JSONObject getRequestJson() {
        return requestJson;
    }

    /**
     * @param requestJson the requestJson to set
     */
    public void setRequestJson(JSONObject requestJson) {
        this.requestJson = requestJson;
    }

    /**
     * @return the responseJson
     */
    public JSONObject getResponseJson() {
        return responseJson;
    }

    /**
     * @param responseJson the responseJson to set
     */
    public void setResponseJson(JSONObject responseJson) {
        this.responseJson = responseJson;
    }


    public JSONObject getTransJson() {
        return transJson;
    }

    public void setTransJson(JSONObject transJson) {
        this.transJson = transJson;
    }

    public JSONObject getResultdata() {
        return resultdata;
    }

    public void setResultdata(JSONObject resultData) {
        this.resultdata = resultData;
    }

    public JSONArray getResultdatas() {
        return resultdatas;
    }

    public void setFuncname(String funcname) {
        this.funcname = funcname;
    }

    public void setLog(DLogger log) {
        this.log = log;
    }
}
