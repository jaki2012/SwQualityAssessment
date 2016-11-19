package com.tongji409.exception;

/**
 * Created by lijiechu on 16/11/18.
 */
public class ValidateException extends Exception{

    public ValidateException(){
        super();
    }

    public ValidateException(String errmsg){
        super(errmsg);
    }

    public ValidateException(String errmsg, Exception e){
        super(errmsg, e);
    }

}
