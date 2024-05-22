package com.larry.java8Demo.objWaitNotifyTest;

public class Test {
    public static void main(String[] args) throws Exception{
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();
    }
}
