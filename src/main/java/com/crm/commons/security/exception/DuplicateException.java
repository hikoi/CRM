package com.crm.commons.security.exception;

import java.text.MessageFormat;

public class DuplicateException extends RuntimeException{

    public DuplicateException(){

    }

    public DuplicateException(Throwable cause){
        super(cause);
    }

    public DuplicateException(String message, Throwable cause){
        super(message, cause);
    }

    public DuplicateException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public DuplicateException(Enum clazz, Throwable cause, Object... args){
        this(MessageFormat.format(clazz.toString(), args), cause);
    }

    public DuplicateException(String message){
        super(message);
    }

    public DuplicateException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
