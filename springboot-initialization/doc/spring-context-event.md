# What is difference between ContextRefreshedEvent, ContextStartedEvent, ContextStoppedEvent and ContextClosedEvent

The documentation for these built-in events can be found [here](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#context-functionality-events), specifically:

## Spring Framework Events
The Spring Framework comes out of the box with a number of events, and you’re able to extend the event functionality for your own purposes.

## Spring Core Events
### ContextRefreshedEvent
This event is published whenever the Spring Context is started or refreshed.

### ContextStartedEvent
This event is published when the Spring Context is started.

ContextStoppedEvent
This event is published when the Spring Context is stopped. In practice, you will not use this event very often. It can be handy for doing cleanup work, like closing connections.

### ContextClosedEvent
This event is similar to the ContextStoppedEvent, but in this case, the Context cannot be re-started.

## Spring Boot Events
Spring Boot introduces several new events on top of the events available in the core Spring Framework.

### ApplicationStartedEvent
This event is published early in the startup of a Spring Application. The Spring Context is running but may change later in the lifecycle.

### ApplicationEnvironmentPreparedEvent
This event is published when the Spring Boot Application is starting up and is first available for inspection and modification.

### ApplicationPreparedEvent
This event is published when the Spring Context is fully prepared but not refreshed. At this point, the Spring Beans are loaded, configured and ready for use.

### ApplicationFailedEvent
This event is published when the Spring Boot Application fails to start. This event is useful for error logging or alerting.

[code decmo](../src/main/java/com/larry/listener/MyApplicationListener.java)