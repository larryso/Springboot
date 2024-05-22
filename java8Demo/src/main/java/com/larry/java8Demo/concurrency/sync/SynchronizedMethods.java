package com.larry.java8Demo.concurrency.sync;

public class SynchronizedMethods {
    private int sum = 0;

    public void calculate(){
        setSum(getSum()+1);
    }
    public synchronized void synchronizedCalculate(){
        setSum(getSum()+1);
    }
    public void performSynchronizedTask(){
        synchronized (this){
            setSum(getSum()+1);
        }
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        System.out.println("--------------->"+sum);
        this.sum = sum;
    }
}
