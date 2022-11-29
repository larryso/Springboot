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

