package com.larry.jackson.controller;

import com.larry.jackson.dto.Coffee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/jacksonTest")
public class JacksonTestController {
    @GetMapping("/getCoffee")
    public Coffee getCoffee(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String brand){
        return Coffee.builder()
                .name(name)
                .brand(brand)
                .date(LocalDateTime.now())
                .build();
    }
}
