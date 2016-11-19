package com.tongji409.exception;

/**
 * Created by lijiechu on 16/11/18.
 */
public class NoUserException extends Exception {
    public NoUserException(){
        super("无操作用户信息！");
    }

    public NoUserException(String errmsg){
        super(errmsg);
    }

    public NoUserException(String errmsg, Exception e){
        super(errmsg, e);
    }
}
