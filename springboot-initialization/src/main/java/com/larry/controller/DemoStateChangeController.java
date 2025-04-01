package com.larry.controller;

import com.larry.statemachine.event.Events;
import com.larry.statemachine.state.States;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/st/v1")
public class DemoStateChangeController {
    @Resource(name = "demo-sm")
    private StateMachine<States, Events> demoStatemachine;
    @GetMapping("/e1")
    public ResponseEntity<String> triggerE1(){
        demoStatemachine.sendEvent(Events.E1);
        return ResponseEntity.ok("Event1 triggered");
    }

    @GetMapping("/e2")
    public ResponseEntity<String> triggerE2(){
        demoStatemachine.sendEvent(Events.E2);
        return ResponseEntity.ok("Event2 triggered");
    }
    @GetMapping("/e3")
    public ResponseEntity<String> triggerE3(){
        demoStatemachine.sendEvent(Events.E3);
        return ResponseEntity.ok("Event2 triggered");
    }
}
