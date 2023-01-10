package com.larry.java8Demo.volatileTest;

public class VolatileTest {
    private static int counter = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            int localCounter = counter;
            while(localCounter < 10){
                if(localCounter != counter){
                    System.out.println("[T1] localCounter changed..");
                    localCounter = counter;
                }
            }
        });
        Thread t2 = new Thread(()->{
            int localCounter = counter;
        });

    }
}
