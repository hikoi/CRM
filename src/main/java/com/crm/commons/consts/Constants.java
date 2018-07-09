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
}
