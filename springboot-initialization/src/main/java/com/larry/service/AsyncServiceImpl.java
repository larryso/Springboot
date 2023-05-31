package com.larry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private DummyService dummyService;

    @Async
    @Override
    public void dummyAsyncSrvice() throws Exception {
        log.info("@@@@@@ Async invoke dummy service......");
        try{
            dummyService.demoRetryableService();
        }catch (Exception e){
            e.printStackTrace();
        }
        Thread.sleep(10000);
        log.info("Async completed........");
    }
}
