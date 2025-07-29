# Mastering Spring Boot Testing with Junit and Mokito

Junit and Mockito are popular libraries for writing unit and integration tests in Java applications.

In this article, we'll explore how to effectively testing Spring Boot application using Junit and Mockito.

## Dependencies setting up

in gradle project

`testImplementation 'org.springframework.boot:spring-boot-starter-test'`

This includes JUnit, Mockito, AssertJ, and other testing libraries.

## Basic Unit Testing
simple junit test
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleTest {
    
    @Test
    void testAddition() {
        assertEquals(4, 2 + 2);
    }
}
```

##  Testing Spring Components
Service Layer Testing with Mockito
```java 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void getUserById_ShouldReturnUser() {
        // Arrange
        User mockUser = new User(1L, "test@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        
        // Act
        User result = userService.getUserById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).findById(1L);
    }
    
    @Test
    void getUserById_ShouldThrowExceptionWhenNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });
    }
}
```
## 4. Spring Boot Integration Testing
Testing REST Controllers
```java 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void getUser_ShouldReturnUser() throws Exception {
        User mockUser = new User(1L, "test@example.com");
        when(userService.getUserById(1L)).thenReturn(mockUser);
        
        mockMvc.perform(get("/api/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.email").value("test@example.com"));
    }
    
    @Test
    void getUser_ShouldReturn404WhenNotFound() throws Exception {
        when(userService.getUserById(1L)).thenThrow(new UserNotFoundException());
        
        mockMvc.perform(get("/api/users/1"))
               .andExpect(status().isNotFound());
    }
}
```
@AutoConfigureMockMvc is a Spring Boot test annotation that automatically configures a MockMvc instance for testing web controllers in a mock HTTP environment (without starting a real server). It's commonly used in @SpringBootTest integration tests.
@SpringBootTest is a Spring Boot annotation used for integration testing. It loads the full application context, including all beans, configurations, and dependencies, simulating a real application environment.

@DataJpaTest - Testing JPA repositories

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void findByEmail_ShouldReturnUser() {
        User user = new User("test@example.com");
        userRepository.save(user);
        
        User found = userRepository.findByEmail("test@example.com");
        
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }
}
```

## Mocking Best Practices
Use @Mock for dependencies - Mock external services, repositories, etc.

Use @InjectMocks for the class under test - Automatically injects mocks

Verify interactions - Use verify() to check mock interactions

Use argument matchers - any(), eq(), etc. for flexible argument matching

Keep tests focused - Each test should verify one specific behavior

## Assertions
Prefer AssertJ for fluent assertions:
```java
import static org.assertj.core.api.Assertions.*;

@Test
void assertJExample() {
    List<String> names = Arrays.asList("Alice", "Bob");
    
    assertThat(names)
        .hasSize(2)
        .contains("Alice")
        .doesNotContain("Charlie");
}
```