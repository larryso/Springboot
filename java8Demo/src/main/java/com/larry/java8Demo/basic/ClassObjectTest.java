package com.larry.java8Demo.basic;

public class ClassObjectTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //Class.forName("com.larry.java8Demo.basic.Demo");

        Class.forName(Demo.class.getName(), true, Thread.currentThread().getContextClassLoader());
    }
}

class Demo{
    static{
        System.out.println("#############Initial ################");
    }
}
