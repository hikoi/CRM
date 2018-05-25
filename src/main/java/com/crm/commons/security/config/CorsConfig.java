package com.crm.commons.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig{

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguartion = new CorsConfiguration();
        //所有域名
        corsConfiguartion.addAllowedOrigin("*");
        //所有请求头
        corsConfiguartion.addAllowedHeader("*");
        //所有请求类型
        corsConfiguartion.addAllowedMethod("*");

        return corsConfiguartion;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
