package com.crm.commons.security.handler;

import com.crm.commons.security.exception.DuplicateException;
import com.crm.commons.security.exception.QueueServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wah.doraemon.security.consts.ResponseCode;
import org.wah.doraemon.security.exception.AuthenticationException;
import org.wah.doraemon.security.exception.BrowserNotSupportException;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.response.Responsed;

@ControllerAdvice
public class ExceptionAdvice{

    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Responsed exception(Exception e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }

    /**
     * 消息队列异常
     */
    @ExceptionHandler(value = QueueServiceException.class)
    public void queueService(QueueServiceException e){
        logger.error(e.getMessage(), e);
    }

    /**
     * 持久层异常
     */
    @ExceptionHandler(value = DataAccessException.class)
    @ResponseBody
    public Responsed dataAccess(DataAccessException e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }

    /**
     * 账户认证异常
     */
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public Responsed authentication(AuthenticationException e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.AUTHENTICATION_FAIL, false);
    }

    /**
     * 记录重复异常
     */
    @ExceptionHandler(value = DuplicateException.class)
    @ResponseBody
    public Responsed duplicate(DuplicateException e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.DUPLICATE_DATA, false);
    }

    /**
     * 不支持的客户端类型
     */
    @ExceptionHandler(value = BrowserNotSupportException.class)
    public ModelAndView browserNotSupport(BrowserNotSupportException e){
        logger.error(e.getMessage(), e);

        return new ModelAndView("browserNotSupport");
    }
}
