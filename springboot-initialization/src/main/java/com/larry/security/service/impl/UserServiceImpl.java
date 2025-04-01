package com.larry.security.service.impl;

import com.larry.mapper.CoreMapper;
import com.larry.security.dto.SignupRequestDTO;
import com.larry.security.dto.UserDTO;
import com.larry.security.persistence.domain.User;
import com.larry.security.persistence.repository.UserRepository;
import com.larry.security.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CoreMapper mapper;
    @Override
    public UserDTO signup(SignupRequestDTO requestDTO) {
        Optional<User> existuser = Optional.ofNullable(userRepository.findByEmail(requestDTO.getEmail()));
        if(existuser.isPresent()){
            log.error("User with email {} exists!", requestDTO.getEmail());
        }
        User user = User.builder().
                name(requestDTO.getName()).
                email(requestDTO.getEmail()).
                password(requestDTO.getPassword()).
                userId(UUID.randomUUID().toString()).
                build();
        return mapper.map(userRepository.save(user), UserDTO.class);
    }
}
