package com.larry.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larry.dto.DocumentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SerializationTest {
    @Test
    public void jsonPropertiesTest() throws JsonProcessingException {
        DocumentDTO documentDTO = DocumentDTO.builder().
                documentPK(2324L).
                EBOID("fileChage").
                fileName("TestFileName").
                build();
        String result = new ObjectMapper().writeValueAsString(documentDTO);
        System.out.println(result);
    }
}
