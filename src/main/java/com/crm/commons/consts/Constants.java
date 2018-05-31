package com.crm.commons.consts;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

@Component
public class Constants{

    public static final String PRIVATE_KEY = "classpath:/key/PrivateKey.pem";

    public static final String PUBLIC_KEY  = "classpath:/key/PublicKey.pem";

    public static final String PROJECT_ROOT = "com.crm";

    public static final List<Class<? extends Annotation>> API_ANNOTATIONS = Arrays.asList(Controller.class, RestController.class);

    public static String SSO_SERVER;

    @Value("${sso.server.url}")
    public void setSSO_SERVER(String server){
        if(StringUtils.isBlank(SSO_SERVER)){
            SSO_SERVER = server;
        }
    }
}
