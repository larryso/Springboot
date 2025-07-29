package com.larry.test.application.service;

import com.larry.service.DummyService;
import com.larry.service.DummyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DummyServiceTest {
    @InjectMocks
    private DummyServiceImpl dummyService;

    @Test
    void testDemoRetryableService() {
        // This is a placeholder for the actual test logic
        // You can use Mockito to mock dependencies and verify interactions
        dummyService.demoRetryableService();
    }

}
