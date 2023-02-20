# Spring Boot Auto Configuration
![](../../images/springboot_logo.svg)

## Auto Configuration

Spring Boot auto-configuration allows us to automatically configure our spring application base on the jar dependencies that we have added.

We can enable the auto-configuration feature by using below anotation:
@Configuration
@EnableAutoConfiguration - enable springboot to auto-configure the application context, therefore, it automatically create and 
                           registers beans and components the defined by us
@ComponentScan(basePackages = "com.larry") - use this anotation to tell springboot where to look for managed components

``` java
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.larry")
public class App {
    @Inject
    private ServerUtils serverUtils;

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(new Class[]{App.class,
                        Class.forName(PatternLayoutLoggingContext.class.getName(), true, Thread.currentThread().getContextClassLoader())},
                args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        log.info("Application initializing ..... InstanceId is: {}", serverUtils.getInstanceId());
        return args ->{
            // do some initialization hear
        };
    }
    @PreDestroy
    public void onExit(){

    }
}
```

But above three anotations were not recommend to use, we use @SpringBootApplication instead of above three, 

``` java
@Slf4j
@SpringBootApplication
public class App {
    @Inject
    private ServerUtils serverUtils;

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(new Class[]{App.class,
                        Class.forName(PatternLayoutLoggingContext.class.getName(), true, Thread.currentThread().getContextClassLoader())},
                args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        log.info("Application initializing ..... InstanceId is: {}", serverUtils.getInstanceId());
        return args ->{
            // do some initialization hear
        };
    }
    @PreDestroy
    public void onExit(){

    }
}
```

## Springboot - SpringApplication Class

SpringApplication class is used to bootstrap and launch a Spring application from a java main method, this class automatically creates the ApplicationContext from the classpath, scan the configuration classes and launch the application, the class is very helpfull in launching SpringMVC or Spring REST application.

By default, SpringApplication scans the configuration class package and all it's subpackages, so if our SpringBootApplication class is in com.larry.spring.main package, then it won't scan com.larry.spring.controller package, we can fix this by using SpringBootApplication scanBasePackage property.

``` java
@SpringBootApplication(scanBasePackages="com.larr")
public calss SpringbootApplication{

}
```
Since Spring Boot provides auto-configuration, there are a lot of beans getting configured by it, we can get a list of thoses beans by using :

``` java
ApplicationContext ctx = SpringApplication.run(SpringbootApplication.class, args)
String[] beans = ctx.getBeanDefinitionNames()
```

if you want to exclude one class from auto-configuration, you can do it like:

``` java
@SpringBootApplication(scanBasePackages="com.larr", exclude={xxx.class, xxxx.class})
public calss SpringbootApplication{

}
```

We can also use SpringApplicationBuilder to customize SpringApplication startup behavior 

``` java
public static void main(String[] args) {
        startTime = System.currentTimeMillis();

        //SpringApplication.run(LucdmsLdoAntivirApplication.class, args);
        //by default, a springboot application is set to run in headless mode, meaning that it can be run on a server there is no
        //graphical interface, wihout donging this .headless(false), any attemp to instantiate AWT GUI element will cause a HeadlessException
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(LucdmsLdoAntivirApplication.class)
                .headless(false).run(args);
    }
```

## The ApplicationContext Interface

One of the main feature of the Spring framework is the IoC (Inversion of Control) container, the IOC container is responsible for manageing Objects of an application, it uses dependency injection to achieve inverion of control

the interfaces BeanFactory and ApplcationContext represent the Spring IoC container.

BeanFactory is the root interface for accessing the Spring Container, it provides basic functionalities for manage beans

ApplicationContext is a sub-interface of the BeanFactory, 

To access the ApplicationContext, we can autowired ApplicationContext interface or implement ApplicaitonContextAware

``` java
@Component
public class ApplicationUtils implements ApplicationContextAware {
    private String applicationID;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationID = applicationContext.getId();
    }
    public String getApplicationID(){
        return applicationID;
    }
}
```


