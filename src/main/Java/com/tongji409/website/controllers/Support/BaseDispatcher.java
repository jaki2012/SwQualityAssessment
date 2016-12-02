package com.tongji409.website.controllers.Support;

import com.alibaba.fastjson.JSONObject;
import com.tongji409.exception.NoUserException;
import com.tongji409.util.config.StaticConstant;
import com.tongji409.util.log.DLogger;
import com.tongji409.util.tools.Tools;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

/**
 * Created by lijiechu on 16/11/18.
 */


@Controller
//@RequestMapping(value = "/ws")
@Scope("prototype")
public abstract class BaseDispatcher {

    protected  DLogger log; // 日志控制器

    protected RequestAttributes ra;
    protected HttpServletRequest request;
    protected HttpSession session;

    protected JSONObject requestjson = new JSONObject();
    protected int dl_userid = 0;


    public BaseDispatcher(){

        this.log = DLogger.getDLogger(this.getClass());
        try {
            // 处理构造对象
            ra = RequestContextHolder.getRequestAttributes();
            request = ((ServletRequestAttributes)ra).getRequest();
            request.setCharacterEncoding("utf-8");
            session = request.getSession();
            describe();

            String url = request.getRequestURI();
            // 处理业务信息
            if(url.contains(".u.") && !Tools.getString(this.requestjson, "rs").equals("rs")){
                this.dl_userid = Tools.getOperUserIDStrict(this.requestjson);
            }

            // 处理log对象
            this.log.setRequestdata(this.requestjson);
            String logFunc = url.substring(url.lastIndexOf("/") + 1);
            logFunc += "(Method="+request.getMethod()+")";
            this.log.setFunc(logFunc);
            this.log.setUser(dl_userid);

            this.log.start();

        } catch (Exception e) {
            if(e instanceof NoUserException){
                log.warn("缺失用户信息，拒绝当前请求");
            }
            else{
                log.error("初始化Dispatcher", e);
            }
        }
    }



    /**
     * 获取request全部参数
     * @return
     * @throws Exception
     */
    protected void describe() throws Exception
    {
        /**
         *request.getParameterMap();
         *的结果是一个Map集合,该集合封装了客户端提交的所有参数信息,
         *遗憾的是,Map中,关键字对应的值,全部都是字符串数组,也就是说,
         *即使,某个属性页面上只有一个控件,在经过该方法以后,Map中该属性对应的值,
         *也是数组,数组的第一个元素才是我们真正想要的值,所以必须,对该Map进行二次解析
         */
        JSONObject jo = new JSONObject();

        //获取所有客户端提交参数的Map
        Map pars=request.getParameterMap();
        //获取Map中所有实体的集合
        Set<Map.Entry> entitySet=pars.entrySet();
        //遍历集合
        for(Map.Entry<String, String[]> entity:entitySet)
        {
            if(entity.getValue().length==1)
            {
                jo.put(entity.getKey(), (entity.getValue())[0]);
            }
            else
            {
                jo.put(entity.getKey(), entity.getValue());
            }
        }

        log.debug(jo.toJSONString());

        // 去除前端传入的USER_ID
        jo.remove("ORG_USER_ID");

        if(session.getAttribute("org_user") != null){
            jo.put(StaticConstant.ORG_USER_ID, ((JSONObject) session.getAttribute("org_user")).getString(StaticConstant.ORG_USER_ID));
            jo.put(StaticConstant.ORG_USER_NAME, ((JSONObject)session.getAttribute("org_user")).getString(StaticConstant.ORG_USER_NAME));
            jo.put(StaticConstant.ORG_USER_TYPE, ((JSONObject)session.getAttribute("org_user")).getString(StaticConstant.ORG_USER_TYPE));
            jo.put(StaticConstant.ORG_USER_STATE, ((JSONObject)session.getAttribute("org_user")).getString(StaticConstant.ORG_USER_STATE));
        }

        requestjson = jo;
        request.setAttribute("PARAM", jo);

        log.sp("requestjson:"+requestjson);
    }


//    protected void storeLogToMongo(String resultCode, String resultNote){
//        Document syslog = new  Document()
//                .append(SysLogModel.LOG_LEVEL, SysLogModel.LOG_LEVEL_INFO)
//                .append(SysLogModel.LOG_TIME, Tools.formatTime(System.currentTimeMillis()+""))
//                .append(SysLogModel.LOG_FUNC, this.funcname)
//                .append(SysLogModel.LOG_USER, this.userid)
//                .append(SysLogModel.LOG_REQUESTIP, this.ip)
//                .append(SysLogModel.LOG_CLIENTNAME, vercode.split("-")[0])
//                .append(SysLogModel.LOG_CLIENTVERSION, this.vercode)
//                .append(SysLogModel.LOG_CLIENTDEVICE, this.clidevc)
//                .append(SysLogModel.LOG_RESULTCODE, resultCode)
//                .append(SysLogModel.LOG_RESULTNOTE, resultNote)
//                .append(SysLogModel.LOG_REQUESTDATA, this.dto)
//                .append(SysLogModel.LOG_RESPONSETDATA, this.responseJson)
//                .append(SysLogModel.LOG_TIMECOST, (System.currentTimeMillis()-starttime+5))
//                .append(SysLogModel.LOG_TIMESTAMP, System.currentTimeMillis());
//
//        MongoOper.addToMongo(SysLogModel.LOG_TBNAME_INFO, syslog);
//
//        syslog.clear();
//    }

    /**
     * 省哈曾 mng 路径下的 jsp文件访问URL
     * @param url
     * @return
     */
    protected String geneWsJspUrl(String url){
        return "/sites/"+url+".jsp";
    }



}
