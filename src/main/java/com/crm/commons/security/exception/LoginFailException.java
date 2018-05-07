package com.crm.commons.security.exception;

import java.text.MessageFormat;

/**
 * 登录失败异常
 */
public class LoginFailException extends RuntimeException{

    public LoginFailException(){

    }

    public LoginFailException(Throwable cause){
        super(cause);
    }

    public LoginFailException(String message, Throwable cause){
        super(message, cause);
    }

    public LoginFailException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public LoginFailException(Enum clazz, Throwable cause, Object... args){
        this(MessageFormat.format(clazz.toString(), args), cause);
    }

    public LoginFailException(String message){
        super(message);
    }

    public LoginFailException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
