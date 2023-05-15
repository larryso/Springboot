# Springboot Application - Logging

## Springboot logging

Spring Boot uses  Appache Commons Logging (which is typically provided by spring framework's spring-jcl module) for all internal logging but leaves the underlying log implementation open.

if you do not provide any logging specific configuration, by default, springboot use Logback to log debug message into condole.

As springboot's internal logging is writen with Appach Commons Logging (spring-jcl module), so it is one and only mandatory dependency, but till springboot 2.x, it is downloaded transitively,
which means any springboot starter such as spring-boot-starter-web which automatically pulls in Logback. and if you do not use spring starter, you need include spring-jcl in your dependency.

In order to use any other logging library other than Logback, you need exclude it from your dependency.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

## Springboot Logging - Logback configuration

The default log output from Spring Boot resembles the following example:
```
2023-04-20T10:07:47.363Z  INFO 18339 --- [           main] o.s.b.d.f.s.MyApplication                : Starting MyApplication using Java 17.0.6 with PID 18339 (/opt/apps/myapp.jar started by myuser in /opt/apps/)
2023-04-20T10:07:47.377Z  INFO 18339 --- [           main] o.s.b.d.f.s.MyApplication                : No active profile set, falling back to 1 default profile: "default"
2023-04-20T10:07:52.007Z  INFO 18339 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-04-20T10:07:52.064Z  INFO 18339 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-04-20T10:07:52.064Z  INFO 18339 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.8]
2023-04-20T10:07:52.577Z  INFO 18339 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-04-20T10:07:52.590Z  INFO 18339 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 5092 ms
2023-04-20T10:07:54.152Z  INFO 18339 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-04-20T10:07:54.164Z  INFO 18339 --- [           main] o.s.b.d.f.s.MyApplication                : Started MyApplication in 8.205 seconds (process running for 9.207)
```

The following items are output:

* Date and Time: Millisecond precision and easily sortable.

* Log Level: ERROR, WARN, INFO, DEBUG, or TRACE.

* Process ID.

* A --- separator to distinguish the start of actual log messages.

* Thread name: Enclosed in square brackets (may be truncated for console output).

* Logger name: This is usually the source class name (often abbreviated).

* The log message.

<b>Logback does not have a FATAL level. It is mapped to ERROR.</b>

When a file in the classpath has one of the following names, Spring Boot will automatically load it over the default configuration:

logback-spring.xml
logback.xml
logback-spring.groovy
logback.groovy





## Reference
[https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)
[https://www.baeldung.com/spring-boot-logging](https://www.baeldung.com/spring-boot-logging)

