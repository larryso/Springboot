package com.larry.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larry.rest.ObjectMapperUtils;
import com.larry.security.dto.UserDTO;
import com.larry.security.persistence.domain.User;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CoreMapper extends ConfigurableMapper {
    private final ObjectMapper jsonMapper = new ObjectMapper().
            deactivateDefaultTyping().
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @Override
    protected void configure(MapperFactory factory){
        factory.registerClassMap(
                factory.classMap(User.class, UserDTO.class)
                .field("userId", "userId")
                        .field("name", "userName")
                        .field("email", "email")
                .toClassMap());
    }
}
