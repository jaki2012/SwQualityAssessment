package com.tongji409.util.tools;

import com.adobe.xmp.impl.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.tongji409.exception.NoUserException;
import com.tongji409.util.config.StaticConstant;
import com.tongji409.util.config.SysProperties;

import com.tongji409.util.log.CE;
import com.tongji409.util.validator.Validator;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by lijiechu on 16/11/18.
 */

public class Tools {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    /**
     * 经典MD5加密
     * @param inStr
     * @return
     */
    public static String toMD5_Trodition(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    /**
     * 点啦MD5加密
     * @param string
     * @return
     */
    public static String toMD5_Dianla(String string) {
        try {
            if (isEmpty(string)) {
                return null;
            }
            byte[] bytes = string.getBytes("UTF-8");
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(bytes);
            return toHexString(algorithm.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 判断字符串为空
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return false;
    }
    /**
     * 判断字符串为空
     * @param s
     * @return
     */
    public static boolean notEmpty(String s) {
        return !isEmpty(s);
    }
    public static boolean notNull(String s) {
        return !isEmpty(s);
    }
    /**
     * 获取当前时间
     *
     * @Title: getDateTime
     * @param @return
     * @author: Cogent Cui
     */
    public static String getDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        return df.format(new Date());
    }
    /**
     * 极端当前时间增减后的时间
     * @param minutesafter
     * @return
     */
    public static String getDateTimeAfterSomeMinus(int minutesafter) {
        String time = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date myDate = formatter.parse(formatter.format(new Date())
                    .toString());
            Calendar c = Calendar.getInstance();
            c.setTime(myDate);
            c.add(Calendar.MINUTE, minutesafter);
            myDate = c.getTime();
            time = formatter.format(myDate);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return time;
    }
    /**
     * 计算时间分钟增减后的时间
     * @param reftime 参考时间
     * @param minutesafter  分钟参数
     * @return
     */
    public static String getTimeAfterSomeMinus(String reftime, int minutesafter) {
        String time = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date myDate = formatter.parse(reftime);
            Calendar c = Calendar.getInstance();
            c.setTime(myDate);
            c.add(Calendar.MINUTE, minutesafter);
            myDate = c.getTime();
            time = formatter.format(myDate);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return time;
    }
    /**
     * 通过高德地图获取位置行政区号
     *
     * @param @param  pointer 地理坐标 格式:120.75073242,31.66039512
     * @param @return
     * @param @throws IOException
     * @return String
     * @throws
     * @Title: getLocationADCode
     * @Description:
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年11月13日 下午5:08:52
     */
    public static String getLocationADCode(String pointer) throws IOException {
        return "0" + getLocationCode(pointer).getJSONObject("regeocode").getJSONObject("addressComponent").getString("adcode");
    }
    /**
     * 通过高德地图获取地理逆编码
     *
     * @param @param  pointer 地理坐标 格式:120.75073242,31.66039512
     * @param @return
     * @param @throws IOException
     * @return JSONObject
     * @throws
     * @Title: getLocationCode
     * @Description:
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年11月13日 下午5:05:41
     */
    public static JSONObject getLocationCode(String pointer) throws IOException {
        String url = ("http://restapi.amap.com/v3/geocode/regeo?key=5587abfd131d0afcec5c67dd16a04be7&location=" + pointer.replace(" ", "") + "&poitype=商务写字楼&radius=1000&extensions=all&batch=false&roadlevel=1");
        return requestUrl(url);
    }
    /**
     * 请求url（返回JSONObject）
     *
     * @param @param  urltext
     * @param @return
     * @param @throws IOException
     * @return JSONObject
     * @throws
     * @Title: requestUrl
     * @Description:
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年11月18日 下午12:34:39
     */
    public static JSONObject requestUrl(String urltext) throws IOException {
        URL url = new URL(urltext);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        url.openStream(), "UTF-8"));
        String inputLine = in.readLine();
        in.close();
        CE.sp(inputLine);
        return JSON.parseObject(inputLine);
    }
    /**
     * MD5转换
     *
     * @param string
     * @return String
     * @author Kinglake
     * @date 2016/1/20/15:00
     */
    public static String toMD5(String string, String fieldName) {
        try {
            if (Validator.string_notNull(string, fieldName)) {
                byte[] bytes = string.getBytes("UTF-8");
                MessageDigest algorithm = MessageDigest.getInstance("MD5");
                algorithm.reset();
                algorithm.update(bytes);
                return toHexString(algorithm.digest());
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 十六进制转换
     *
     * @param bytes
     * @return String
     * @author Kinglake
     * @date 2016/1/20/15:00
     */
    private static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();
    }
    /**
     * 从JSONObject中根据key获取String类型的value
     *
     * @Title: getString
     * @param @param jo
     * @param @param key
     * @param @return
     * @author: Cogent Cui
     */
    public static String getString(JSONObject jo, String key,
                                   String defaultifnone) {
        if (jo.containsKey(key)) {
            return jo.getString(key).trim();
        } else {
            return defaultifnone;
        }
    }
    /**
     * 获取客户端标识
     * @param jo
     * @return
     * @author Cogent Cui
     * 2015年5月27日 下午7:48:43
     */
    public static String getClientName(JSONObject jo) {
        return jo.getString("VER_CODE").split("-")[0];
    }
    /**
     * 从JSONObject中根据key获取String类型的value
     *
     * @Title: getString
     * @param @param jo
     * @param @param key
     * @param @return
     * @author: Cogent Cui
     */
    public static String getString(JSONObject jo, String key) {
        return getString(jo, key, "");
    }
    /**
     * 从JSONObject中根据key获取JSONObject
     * @Title: getJSONObject
     * @Description: TODO
     * @param @param jo
     * @param @param key
     * @param @return
     * @return JSONObject
     * @throws
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年9月19日 下午12:16:50
     */
    public static JSONObject getJSONObject(JSONObject jo, String key) throws Exception {
        JSONObject cjo = new JSONObject();;
        try{
            cjo = jo.getJSONObject(key);
        }
        catch(Exception e){
            if(e instanceof JSONException){
                cjo = JSONObject.parseObject(Tools.getString(jo, key));
            }
            else{
                throw e;
            }
        }
        return cjo;
    }
    /**
     * 从JSONObject中根据key获取JSONArray
     * @Title: getJSONOArray
     * @Description: TODO
     * @param @param jo
     * @param @param key
     * @param @return
     * @return JSONArray
     * @throws
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年9月19日 下午12:17:58
     */
    public static JSONArray getJSONOArray(JSONObject jo, String key) throws Exception {
        JSONArray cja = new JSONArray();;
        try{
            cja = jo.getJSONArray(key);
        }
        catch(Exception e){
            if(e instanceof JSONException){
                cja = JSONArray.parseArray(Tools.getString(jo, key));
            }
            else{
                throw e;
            }
        }
        return cja;
    }
    /**
     * 从JSONObject中根据key获取Int类型的value
     *
     * @Title: getInt
     * @param @param jo
     * @param @param key
     * @param @return
     * @author: Cogent Cui
     */
    public static int getInt(JSONObject jo, String key) {
        return getInt(jo, key, -1);
    }

    /**
     * 从JSONObject中获取操作用户信息(严格)
     * @param requestJSON
     * @return
     * @throws NoUserException
     */
    public static int getOperUserIDStrict(JSONObject requestJSON) throws NoUserException {
        int userid = getInt(requestJSON, StaticConstant.ORG_USER_ID);
        if(userid==-1){
            throw new NoUserException();
        }
        return userid;
    }

    /**
     * 从JSONObject中获取操作用户信息
     * @param requestJSON
     * @return
     */
    public static int getOperUserID(JSONObject requestJSON)  {
        int userid = getInt(requestJSON, StaticConstant.ORG_USER_ID);
        return userid;
    }

    /**
     * 从JSONObject中获取操作用户名称
     * @param requestJSON
     * @return
     */
    public static String getOperUserName(JSONObject requestJSON)  {
        String username = getString(requestJSON, StaticConstant.ORG_USER_NAME);
        return username;
    }

    /**
     * 从JSONObject中根据key获取Int类型的value
     *
     * @Title: getInt
     * @param @param jo
     * @param @param key
     * @param @param defaultvalue
     * @param @return
     * @author: Cogent Cui
     */
    public static int getInt(JSONObject jo, String key, int defaultvalueifnull) {
        if (jo.containsKey(key)) {
            return jo.getInteger(key);
        } else {
            return defaultvalueifnull;
        }
    }
    /**
     * 从JSONObject中根据key获取long类型的value
     *
     * @Title: getLong
     * @param @param jo
     * @param @param key
     * @param @return
     * @author: Cogent Cui
     */
    public static long getLong(JSONObject jo, String key) {
        return getLong(jo, key, -1);
    }
    /**
     * 从JSONObject中根据key获取long类型的value
     *
     * @Title: getLong
     * @param @param jo
     * @param @param key
     * @param @param defaultvalueifnull
     * @param @return
     * @author: Cogent Cui
     */
    public static long getLong(JSONObject jo, String key, int defaultvalueifnull) {
        if (jo.containsKey(key)) {
            return Long.parseLong(jo.get(key).toString());
        } else {
            return defaultvalueifnull;
        }
    }
    /**
     * 从JSONObject中根据key获取float类型的value
     *
     * @Title: getFloat
     * @param @param jo
     * @param @param key
     * @param @param defaultifnull
     * @param @return
     * @author: Cogent Cui
     */
    public static float getFloat(JSONObject jo, String key, float defaultifnull) {
        if (jo.containsKey(key)) {
            return Float.parseFloat(jo.get(key).toString());
        } else {
            return defaultifnull;
        }
    }
    /**
     * 从JSONObject中根据key获取float类型的value
     *
     * @Title: getFloat
     * @param @param jo
     * @param @param key
     * @param @return
     * @author: Cogent Cui
     */
    public static float getFloat(JSONObject jo, String key) {
        return getFloat(jo, key, 0);
    }
    /**
     * 从JSONObject中根据key获取double类型的value
     *
     * @Title: getDouble
     * @param @param jo
     * @param @param key
     * @param @return
     * @author: Cogent Cui
     */
    public static double getDouble(JSONObject jo, String key, double ifnull) {
        if (jo.containsKey(key)&&!jo.get(key).toString().equals("")) {
            return Double.parseDouble(jo.get(key).toString().trim());
        } else {
            return ifnull;
        }
    }
    /**
     * 从JSONObject中根据key获取double类型的value
     * @Title: getDouble
     * @Description:
     * @param @param jo
     * @param @param key
     * @param @return
     * @return double
     * @throws
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年11月13日 下午9:50:37
     */
    public static double getDouble(JSONObject jo, String key) {
        return getDouble(jo, key, 0);
    }
    /**
     * 写入网页文件
     * @param filename 文件名
     * @param fullname 文件路径名
     * @param htmlDetial 网页文件详情
     * @return 文件名
     * @throws Exception
     */
    public static String writeHTML(String filename, String fullname,
                                   String htmlDetial) throws Exception {
        // String filename =
        // FileInfoModel.GOODS_HTML_PREFIX+gi.getId()+"-"+Tools.getCompressedDateTime()
        // + FileInfoModel.GOODS_HTML_EXT;
        // String fullname = SystemProperties.SYS_PROJECT_HTMLPATH_GOODS +
        // filename ;
        System.out.println("Tools插入，文件名称" + fullname);
        File folder = new File(SysProperties.FILE_HTML_POST);
        // 检测目录
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 检测文件
        File file = new File(fullname);
        if (!file.exists()) {
            file.createNewFile();
        }
        // 网页内容自适应标签
        htmlDetial = "<meta name='viewport' content='width=device-width, initial-scale=1' />" +
                "\n<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" +
                "\n<meta http-equiv='Access-Control-Allow-Origin' content='*'>" +
                "\n<style type='text/css'>img, object { max-width: 100%;}</style>" +
                "\n"
                + htmlDetial;
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(
                file), "UTF-8");
        BufferedWriter bwriter = new BufferedWriter(write);
        // PrintWriter writer = new PrintWriter(new BufferedWriter(new
        // FileWriter(filePathAndName)));
        // PrintWriter writer = new PrintWriter(new
        // FileWriter(filePathAndName));
        bwriter.write(htmlDetial);
        bwriter.close();
        // FileWriter fw = new FileWriter(file.getAbsoluteFile());
        // BufferedWriter bw = new BufferedWriter(fw);
        // bw.write(new String(gi.getGOODS_URLCONTENT().getBytes("GBK"),
        // "GBK"));
        // bw.close();
        return filename;
    }
    /**
     * 读取网页内容
     * @param filefullname
     * @return
     * @throws Exception
     */
    public static String readHTML(String filefullname)
            throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filefullname), "UTF-8"));
        String line = "";
        StringBuffer buffer = new StringBuffer();
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        return buffer
                .toString()
                .replace(
                        "<meta name='viewport' content='width=device-width, initial-scale=1' />",
                        "")
                .replace(
                        "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />",
                        "")
                .replace(
                        "<meta http-equiv='Access-Control-Allow-Origin' content='*'>",
                        "")
                .replace(
                        "<style type='text/css'>img, object { max-width: 100%;}</style>",
                        "");
    }
    /**
     * 格式化时间
     * @param time
     * @return
     */
    public static String formatTime(String time){
        Date date =new Date(Long.parseLong(time));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str_date = sdf.format(date);
        return str_date;
    }


    /**
     * 英文日期格式化
     * @param en_time
     * @return
     * @throws Exception
     */
    public static String formateTimeFromEnglish(String en_time) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);//MMM dd hh:mm:ss Z yyyy
        Date date = sdf.parse(en_time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (dateFormat.format(date));
    }

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
                System.out.println("++++++++++"+strUrl);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    /**
     * 请求百度apistore接口
     * @param httpUrl
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        CE.sp("httpurl:"+httpUrl);
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",SysProperties.BAIDU_APISTORE_KEY);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Base64编码
     * @param str
     * @return
     */
    public static String base64(String str){
        return Base64.encode(str).replace("=", "");
    }


    /**
     * Base64解码
     * @param base64Str
     * @return
     */
    public static String unBase64(String base64Str) throws Exception{
        return Base64.decode(base64Str);
    }


    /**
     * 获取信息加密
     * @param s
     * @return
     */
    public static String getMessageDigest(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] buffer = s.getBytes("UTF-8");
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdTemp.update(buffer);
            //获得密文
            byte[] md = mdTemp.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 计算两个日期之间相差的天数
     * @param sdate 较小的时间
     * @param edate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int calcDaysBetween(Date sdate, Date edate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        sdate =sdf.parse(sdf.format(sdate));
        edate =sdf.parse(sdf.format(edate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(edate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     *字符串的日期格式的计算
     */
    public static int calcDaysBetween(String sdate, String edate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(sdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(edate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

}