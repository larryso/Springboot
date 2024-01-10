package com.larry.controller;

import com.larry.scheduler.quartz.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quartz")
public class QuartzController {
    @Autowired
    private QuartzService quartzService;

    @GetMapping(value = "/simpleJob")
    public ResponseEntity<String> fireSimpleJob(){
        quartzService.fireSimpleJob();
        return ResponseEntity.ok("SimpleJob Fired!");
    }
}
