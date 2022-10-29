package com.larry.java8Demo.objWaitNotifyTest;

public class Data {
    private String packet;
    //if true, receiver should wait
    //if false, sender should wait
    private boolean transfer;

    public synchronized String receive() throws InterruptedException {
        while(transfer){
            this.wait();
        }
        transfer = true;
        String returnPacket = packet;
        this.notifyAll();
        return returnPacket;
    }
    public synchronized void send(String packet) throws InterruptedException {
        while(!transfer){
            this.wait();
        }
        this.packet = packet;
        transfer = false;
        this.notifyAll();
    }
}
