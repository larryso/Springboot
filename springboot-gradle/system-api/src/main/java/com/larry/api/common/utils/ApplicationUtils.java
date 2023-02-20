package com.larry.api.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtils implements ApplicationContextAware {
    private String applicationID;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationID = applicationContext.getId();
    }
    public String getApplicationID(){
        return applicationID;
    }
}
