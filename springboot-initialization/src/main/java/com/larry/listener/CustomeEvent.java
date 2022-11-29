package com.larry.listener;

import org.springframework.context.ApplicationEvent;

public class CustomeEvent extends ApplicationEvent {
    private String message;
    public CustomeEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
