package com.larry.java8Demo.objWaitNotifyTest;

import lombok.SneakyThrows;

public class Receiver implements Runnable {
    private Data data;
    public Receiver(Data data){
        this.data = data;
    }
    @SneakyThrows
    @Override
    public void run() {
        for(String receivedPacket = data.receive();!"end".equalsIgnoreCase(receivedPacket);receivedPacket = data.receive()){
            System.out.println("Receiver received: "+receivedPacket);
            Thread.sleep(1000);
        }
    }
}
