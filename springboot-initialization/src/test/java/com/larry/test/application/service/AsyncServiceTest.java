package com.larry.test.application.service;

import com.larry.service.DummyService;
import com.larry.service.AsyncServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AsyncServiceTest {
//    @Mock
//    private DummyService dummyService;

    @Mock
    private AsyncServiceImpl asyncService;

    @Test
    void testAsyncService() throws Exception {
        // This is a placeholder for the actual test logic
        // You can use Mockito to mock dependencies and verify interactions
        asyncService.dummyAsyncSrvice();
        // Add assertions or verifications as needed
    }
}
