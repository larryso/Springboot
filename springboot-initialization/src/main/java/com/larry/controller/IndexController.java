package com.larry.controller;

import com.larry.listener.CustomSpeingEventPublisher;
import com.larry.rest.dto.Coffee;
import com.larry.service.AsyncService;
import com.larry.service.DummyService;
import com.larry.service.DummyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/index")
@RequiredArgsConstructor
public class IndexController {
    private final CustomSpeingEventPublisher customSpeingEventPublisher;
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private DummyService dummyService;

    @GetMapping("/hellow")
    public ResponseEntity<String> index(){
        customSpeingEventPublisher.publishCustomEvent("");
        log.info("Helow Word");
        return ResponseEntity.ok("Hello World!");
    }
    @GetMapping("/getCoffee")
    public Coffee getCoffee(@RequestParam String name, @RequestParam(required = false) String brand){
        return Coffee.builder().name(name).brand(brand).date(LocalDateTime.now()).producedDate(new Date()).build();
    }
    @GetMapping("/dummyTest")
    public String dummyTest(){
       try{
           asyncService.dummyAsyncSrvice();
           log.info("########## controller ####################");
       }catch (Exception e){

       }
        return "OK";
    }
    @GetMapping("/auditTest")
    public String auditTest(){
        dummyService.auditedService();
        return "OK";
    }
}
