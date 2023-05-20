# Spring Async 

## Overview

In Spring, we use @Async anotating a method and make it execute in a separate thread

## Enable Async support
```java
@Configuration
@EnableAsync(proxyTargetClass=true)
public class SpringAsyncConfig{}

```
@EnableAsync anotation detects Spring's @Async annotation and 

## Referenc
[https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/EnableAsync.html](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/annotation/EnableAsync.html)
[https://www.baeldung.com/spring-async](https://www.baeldung.com/spring-async)
