package com.larry.controller;

import com.larry.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        UserDTO user = UserDTO.builder().
                id(1L).
                firstName("Larry").
                lastName("Song").
                email("larry@133.com").
                build();
       List<UserDTO> users = new ArrayList<>();
       users.add(user);
       return users;
    }
}
