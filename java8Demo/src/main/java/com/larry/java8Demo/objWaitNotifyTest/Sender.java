package com.larry.java8Demo.objWaitNotifyTest;

import lombok.SneakyThrows;

public class Sender implements Runnable {
    private Data data;

    public Sender(Data data){
        this.data = data;
    }
    @SneakyThrows
    @Override
    public void run() {
        String[] packets = {
                "data1",
                "data2",
                "data3",
                "data4",
                "end"
        };
        for(String str: packets){
            System.out.println("Sender send: "+str);
            data.send(str);
            Thread.sleep(1000);
        }
    }
}
