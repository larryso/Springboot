package com.larry.service;

import org.springframework.util.StringUtils;

public class JsonHelper {
    private JsonHelper() {
        // Private constructor to prevent instantiation
    }
    public static String getJsonPath(String attrbuteName){
        if(StringUtils.isEmpty(attrbuteName)){
            return "$." + attrbuteName;
        }
        return "$."+ attrbuteName.replaceAll("\\.", "\\\\.");
    }

}
