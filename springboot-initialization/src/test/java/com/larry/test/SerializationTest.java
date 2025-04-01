package com.larry.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larry.dto.DocumentDTO;
import com.larry.dto.StorageDTO;
import com.larry.dto.enumeration.DocumentStatus;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class SerializationTest {
    @Test
    public void jsonPropertiesTest() throws JsonProcessingException {

        StorageDTO storage = StorageDTO.builder().
                antivirusStatus("Success").
        bucket("Test Bucket").fileSize(12000L).build();

        DocumentDTO documentDTO = DocumentDTO.builder().
                documentPK(2324L).
                EBOID("fileChage").
                fileName("TestFileName").
                documentStatus(DocumentStatus.DOCUMENT_COMPLETED).
                storage(storage).uploadDate(new Date()).
                build();
        String result = new ObjectMapper().writeValueAsString(documentDTO);
        System.out.println(result);
    }
}
