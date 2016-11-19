package com.tongji409.util.validator;

/**
 * Created by lijiechu on 16/11/18.
 */
import java.util.regex.Pattern;
import com.tongji409.exception.ValidateException;

//~--- classes ----------------------------------------------------------------

/**
 * @author Cogent   (brilliantree@126.com)
 * @version v1.0.0, 2016-01-19
 * @title Validator.java
 * @desc 校验器
 */
public class Validator {

    /**
     * @param str       String
     * @param filedname String
     * @return boolean
     * @throws ValidateException
     * @title string_notNull
     * @descrb 校验字符全不能为空
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean string_notNull(String str, String filedname) throws ValidateException {
        return valiString(str, filedname, 0, 99999);
    }

    /**
     * @param str      String
     * @param fileName String
     * @return boolean
     * @throws ValidateException
     * @title valiInt
     * @descrb 校验字符串是否为数字
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean valiInt(String str, String fileName) throws ValidateException {
        return string_isMatch(str, fileName, "^[0-9]*$");
    }

    /**
     * @param str      String
     * @param fileName String
     * @return boolean
     * @throws ValidateException
     * @title valiFloatWith2Decimals
     * @descrb 校验字符串是否为 大于0的两位数以内小数
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean valiFloatWith2Decimals(String str, String fileName) throws ValidateException {
        return string_isMatch(str, fileName, "^[0-9]+(.[0-9]{0,2})?$");
    }

    /**
     * @param str      String
     * @param fileName String
     * @return boolean
     * @throws ValidateException
     * @title valiTel
     * @descrb 校验字符串是否为电话号码
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean valiTel(String str, String fileName) throws ValidateException {
        return string_isMatch(str, fileName, "^1[3|4|5|7|8]\\d{9}$");
    }

    /**
     * @param str      String
     * @param fileName String
     * @return boolean
     * @throws ValidateException
     * @title valiPassword
     * @descrb 校验密码格式是否正确 大写、小写、数字、6-16位
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean valiPassword(String str, String fileName) throws ValidateException {
        return string_isMatch(str, fileName, "^[a-zA-Z0-9]\\w{5,17}$");
    }

    /**
     * @param str      String
     * @param fileName String
     * @param regex    String
     * @return boolean
     * @throws ValidateException
     * @title string_isMatch
     * @descrb 校验字符串格式
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean string_isMatch(String str, String fileName, String regex) throws ValidateException {
        if (string_notNull(str, fileName)) {
            return str.matches(regex);
        }

        return false;
    }

    /**
     * @param str       String
     * @param fieldName String
     * @param maxlength int
     * @param minlength int
     * @return boolean
     * @throws ValidateException
     * @title valiString
     * @descrb 校验String长度
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean valiString(String str, String fieldName, int minlength, int maxlength)
            throws ValidateException {
        if (str == null) {
            throw new ValidateException(fieldName + " 字段未传入！");
        } else if ((minlength == 0) && (maxlength == 99999) && (str.trim().length() == 0)) {
            throw new ValidateException(fieldName + " 字段不能为空！");
        } else if ((str.trim().length() < minlength) || (str.trim().length() > maxlength)) {
            throw new ValidateException(fieldName + " 字段应在" + minlength + "到" + maxlength + "字符之间！");
        }

        return true;
    }

    /**
     * @param i         int
     * @param fieldName String
     * @param minvalue  int
     * @return boolean
     * @throws ValidateException
     * @title int_notLessThan
     * @descrb 校验数字最小值
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean int_notLessThan(int i, String fieldName, int minvalue) throws ValidateException {
        return valiInt(i, fieldName, minvalue, Integer.MAX_VALUE);
    }

    /**
     * @param i         int
     * @param fieldName String
     * @param maxvalue  int
     * @return boolean
     * @throws ValidateException
     * @title int_notGreaterThan
     * @descrb 校验数字最大值
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean int_notGreaterThan(int i, String fieldName, int maxvalue) throws ValidateException {
        return valiInt(i, fieldName, Integer.MIN_VALUE, maxvalue);
    }

    /**
     * @param i         int
     * @param fieldName String
     * @param minvalue  int
     * @param maxvalue  int
     * @return boolean
     * @throws ValidateException
     * @title valiInt
     * @descrb 校验数字大小
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean valiInt(int i, String fieldName, int minvalue, int maxvalue) throws ValidateException {
        if ((i < minvalue) || (i > maxvalue)) {
            if (minvalue == Double.MIN_VALUE) {
                throw new ValidateException(fieldName + " 数值不可大于" + maxvalue + "！");
            } else if (maxvalue == Double.MAX_VALUE) {
                throw new ValidateException(fieldName + " 数值不可小于" + minvalue + "！");
            } else {
                throw new ValidateException(fieldName + " 数值应在" + minvalue + "到" + maxvalue + "之间！");
            }
        }

        return true;
    }

    /**
     * @param db        double
     * @param fieldName String
     * @param mindb     double
     * @return boolean
     * @throws ValidateException
     * @title double_notLessThan
     * @descrb 校验小数最小值
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean double_notLessThan(double db, String fieldName, double mindb) throws ValidateException {
        return valiDouble(db, fieldName, mindb, Double.MAX_VALUE);
    }

    /**
     * @param db        double
     * @param fieldName String
     * @param maxdb     double
     * @return boolean
     * @throws ValidateException
     * @title double_notGreaterThan
     * @descrb 校验小数最大值
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean double_notGreaterThan(double db, String fieldName, double maxdb) throws ValidateException {
        return valiDouble(db, fieldName, Double.MIN_VALUE, maxdb);
    }

    /**
     * @param db        double
     * @param fieldName String
     * @param minvalue  double
     * @param maxvalue  double
     * @return boolean
     * @throws ValidateException
     * @title valiDouble
     * @descrb 校验小数
     * @author Cogent (brilliantree@126.com)
     * @version v1.0.0, 2016-01-19
     */
    public static boolean valiDouble(double db, String fieldName, double minvalue, double maxvalue)
            throws ValidateException {
        if ((db < minvalue) || (db > maxvalue)) {
            if (minvalue == Double.MIN_VALUE) {
                throw new ValidateException(fieldName + " 数值不可大于" + maxvalue + "！");
            } else if (maxvalue == Double.MAX_VALUE) {
                throw new ValidateException(fieldName + " 数值不可小于" + minvalue + "！");
            } else {
                throw new ValidateException(fieldName + " 数值应在" + minvalue + "到" + maxvalue + "之间！");
            }
        }

        return true;
    }


    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

//    /**
//     * 正则表达式：验证URL
//     */
//    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
//    public static boolean isUrl(String url) {
//        return Pattern.matches(REGEX_URL, url);
//    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
}
