package com.larry.java8Demo.datetime;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;

public class LocalDateDemo {
    public static void main(String[] args) throws Exception {
        Date d1 = new Date();
        Thread.sleep(6000);
        Date d2 = new Date();
        System.out.println(d2.getTime() - d1.getTime());
        System.out.println(Instant.ofEpochMilli(d2.getTime() - d1.getTime()).toString());
        String diff = Instant.ofEpochMilli(d2.getTime() - d1.getTime()).atZone(ZoneOffset.UTC).toLocalTime().toString();
        System.out.println(diff);
    }
}
