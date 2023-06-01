package com.larry.service;

import com.larry.audit.AuditAction;
import com.larry.audit.Audited;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class DummyServiceImpl implements DummyService{

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(2000))
    public void demoRetryableService() {
        try{
            log.info("Do something ....{}", LocalDateTime.now());
            int i = 1/0;
        }catch (Exception e){
            throw e;
        }
    }

    @Audited(action = AuditAction.UPLOAD_DOCUMENT)
    public String auditedService(){
        System.out.println("Document upload service....");
        return "Document upload...";
    }
}
