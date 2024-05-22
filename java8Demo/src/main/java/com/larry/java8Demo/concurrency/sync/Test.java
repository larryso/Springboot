package com.larry.java8Demo.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        SynchronizedMethods summation = new SynchronizedMethods();
        IntStream.range(0, 1000).forEach(count -> service.submit(summation::synchronizedCalculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        System.out.println(summation.getSum());
    }
}
