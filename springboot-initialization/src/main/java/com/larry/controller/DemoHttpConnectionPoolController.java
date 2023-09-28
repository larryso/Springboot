package com.larry.controller;

import com.larry.service.HttpPoolingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/demoHttpPooling")
@Slf4j
public class DemoHttpConnectionPoolController {
    @Autowired
    private HttpPoolingService poolingService;

    @GetMapping("/execute")
    public ResponseEntity execute() throws Exception {
        poolingService.execute();
        return ResponseEntity.ok().build();
    }
}
