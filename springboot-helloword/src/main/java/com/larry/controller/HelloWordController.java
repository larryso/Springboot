package com.larry.controller;

import java.util.HashMap;

import com.larry.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api",produces = APPLICATION_JSON_VALUE)
@Slf4j
public class HelloWordController {
    @RequestMapping(value = "/ex/foos", method = RequestMethod.GET,
            headers = {"key1=val1", "key2=val2"})
    public String getFoosBySimplePath() {
        return "Get some Foos";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("getMap")
    public HashMap<String, Object> getMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("errorCode", 200);
        result.put("errorMessage", "Success...");
        return result;
    }

    @GetMapping("/index/get/{id}")
    public ResponseEntity<String> indexGet(@PathVariable Long id) {
        String strMessage = "Greeting From Springboot " + id;
        return ResponseEntity.status(HttpStatus.OK).body(strMessage);
    }

    @PostMapping(value="/index/writePost", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> writePost(@RequestBody UserDTO userDTO) {
        log.info(String.valueOf(userDTO));
        return ResponseEntity.ok(userDTO);
    }
}
