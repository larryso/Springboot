package com.larry.api.common.utils;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.UUID;

@Component
@Singleton
public class ServerUtils {
    private String instanceId;

    private ServerUtils(){
        this.instanceId = UUID.randomUUID().toString();
    }

    public String getInstanceId(){
        return this.instanceId;
    }
}
