# Springboot Runner and Bean Initialization 
![](../../images/logo.webp)

## CommandlineRunner and ApplicationRunner

CommandLineRunner and ApplicationRunner are two differencet functional interface that holds run() method will be called just after

Spring beans and ApplicationContext are created

These interface has run() with different argument

``` java
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            log.info("CommondLineRunner running with arguments: {}", Arrays.toString(args));
        };
    }
    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            log.info("ApplicationRunner running with arguments: {}", args.getOptionNames());
        };
    }
}
```

## Spring Event handling for initialization

[Spring Event handling for initialization](./spring-boot-listener_and_event.md)

## SmartLifeCycle

An extension of the Lifecycle interface for thoses objects that require to be started upon ApplicationContext refresh or shutdown in a particular order

* isAuthoStartup() return value indicates whether this object should be started at the time of a context refresh

* stop(Runnable) method is usefull for objects that have an asynchronous shutdown process

* getPhase() methods return value indicates the phase within which the lifecyle component should be started and stopped