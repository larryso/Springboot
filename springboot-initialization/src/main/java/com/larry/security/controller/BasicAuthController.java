package com.larry.security.controller;

import com.larry.security.dto.SignupRequestDTO;
import com.larry.security.dto.UserDTO;
import com.larry.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public/api")
public class BasicAuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody SignupRequestDTO requestDTO){
        UserDTO userDTO = userService.signup(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
    @GetMapping("/test")
    public String test(){
        return "Success";
    }
    @PostMapping("/testPost")
    public String testPost(){
        return "Test Post Success";
    }
}
