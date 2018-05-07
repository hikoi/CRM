package com.crm.commons.security.exception;

import java.text.MessageFormat;

public class TicketAuthenticationException extends RuntimeException{

    public TicketAuthenticationException(){

    }

    public TicketAuthenticationException(Throwable cause){
        super(cause);
    }

    public TicketAuthenticationException(String message, Throwable cause){
        super(message, cause);
    }

    public TicketAuthenticationException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public TicketAuthenticationException(Enum clazz, Throwable cause, Object... args){
        this(MessageFormat.format(clazz.toString(), args), cause);
    }

    public TicketAuthenticationException(String message){
        super(message);
    }

    public TicketAuthenticationException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
