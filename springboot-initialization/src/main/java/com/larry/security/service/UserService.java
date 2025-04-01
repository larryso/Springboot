package com.larry.security.service;

import com.larry.security.dto.SignupRequestDTO;
import com.larry.security.dto.UserDTO;

public interface UserService {
    UserDTO signup(SignupRequestDTO requestDTO);
}
