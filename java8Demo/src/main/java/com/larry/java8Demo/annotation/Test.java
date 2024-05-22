package com.larry.java8Demo.annotation;

import com.larry.java8Demo.annotation.entity.User;

public class Test {
    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.init();
        User user = (User)beanFactory.getBean("user");
        System.out.println(user.getName());
    }
}
