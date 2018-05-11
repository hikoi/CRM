package com.crm.commons.utils;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
public class CharUtils{

    public static String toUnicode(String content){
        if(StringUtils.isBlank(content)){
            return content;
        }

        StringBuffer sb = new StringBuffer();
        for(char c : content.toCharArray()){
            sb.append("\\u").append(Integer.toHexString(c));
        }

        return sb.toString();
    }

    public static String toChar(String content){
        if(StringUtils.isBlank(content)){
            return content;
        }

        String[] chars = content.split("\\\\u");
        StringBuffer sb = new StringBuffer();
        for(int i = 1; i < chars.length; i++){
            sb.append((char) Integer.parseInt(chars[i], 16));
        }

        return sb.toString();
    }
}
