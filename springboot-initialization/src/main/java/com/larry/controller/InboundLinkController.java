package com.larry.controller;

import com.larry.dto.InboundLinkDTO;
import com.larry.service.InboundLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class InboundLinkController {
    @Autowired
    private InboundLinkService inboundLinkService;

    @PostMapping(value = "/newLink")
    public InboundLinkDTO createInboundLink(@Valid @RequestBody InboundLinkDTO inboundLinkDTO){
        return inboundLinkService.create(inboundLinkDTO);
    }
}
