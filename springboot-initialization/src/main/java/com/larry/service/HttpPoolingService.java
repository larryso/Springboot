package com.larry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

import static com.larry.configure.HttpConnectionPoolConfig.CONCURRENCY;

@Service
@Slf4j
public class HttpPoolingService {

    private static final int SIZE = 10000;
    private RestTemplate restTemplate;

    public HttpPoolingService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public void execute() throws Exception {
        log.info("Starting.....");
        AtomicInteger counter = new AtomicInteger(-1);
        Long[] time = new Long[SIZE];
        Runnable runnableTask = ()->{
            try{
                ThreadLocal<Long> startTime = new ThreadLocal<>();
                startTime.set(System.currentTimeMillis());
                String responseString = this.restTemplate.getForObject("https://reqres.in/api/users?page=4", String.class);
                time[counter.incrementAndGet()] = System.currentTimeMillis() - startTime.get();
                log.info("Thread - id: {}, Thread - name: {}, elapsedTime: {}, Response String: {}",
                        Thread.currentThread().getId(), Thread.currentThread().getName(), time[counter.get()], responseString);
            }catch (Exception e){
                log.error(e.getMessage());
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(CONCURRENCY);
        LongStream.range(0, SIZE).forEach(e ->{
            try{
                //submit task to thread pool
                executorService.execute(runnableTask);
            }catch (Exception e1){
                throw new RuntimeException(e1);
            }
        });
        //in general the ExecutorService will not be automatically destroyed when there is no taks to process, it will stay alive and wait for new tasks
        // showdown method prevents client send more taks to executor service and existing tasks are still running
        executorService.shutdown();

        while(!executorService.isTerminated()){
            Thread.sleep(1000);
        }

        log.info("Completed!");
    }
    private void print(Long[] time){
        Arrays.stream(time).forEach(t->{
            log.info(t.toString());
        });
    }
}
