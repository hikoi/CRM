package com.crm.commons.consts;

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

    public static final String IM_PRIVATE_KEY = "classpath:/key/IMPrivateKey.pem";

    public static final String IM_PUBLIC_KEY  = "classpath:/key/IMPublicKey.pem";
}
