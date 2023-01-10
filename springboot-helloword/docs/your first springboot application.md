# The Easy Way to Build Your First Springboot Application
![](../../images/springboot_logo.svg)

## Prerequisite 
* A favorite text editor or IDE
* JDK 1.8 or later
* Gradle 4+ or Maven 3.2+

## Whate we can Do with Springboot
Spring Boot offers a fast way to build your spring applications. It looks at your classpath and at the beans you have configured, makes reasonable assumptions about what you are missing, and adds those items. With Spring Boot, you can focus more on business features and less on infrastructure. (开箱即用)

The following examples show what Spring Boot can do for you:

* Is Spring MVC on the classpath? There are several specific beans you almost always need, and Spring Boot adds them automatically. A Spring MVC application also needs a servlet container, so Spring Boot automatically configures embedded Tomcat.

* Is Jetty on the classpath? If so, you probably do NOT want Tomcat but instead want embedded Jetty. Spring Boot handles that for you.

* Is Thymeleaf on the classpath? If so, there are a few beans that must always be added to your application context. Spring Boot adds them for you.

These are just a few examples of the automatic configuration Spring Boot provides. At the same time, Spring Boot does not get in your way. For example, if Thymeleaf is on your path, Spring Boot automatically adds a SpringTemplateEngine to your application context. But if you define your own SpringTemplateEngine with your own settings, Spring Boot does not add one. This leaves you in control with little effort on your part.

## Starting with Spring Initializr

1. Navigate to [https://start.spring.io](https://start.spring.io). This service pulls in all the dependencies you need for an application and does most of the setup for you.

2. Choose either Gradle or Maven and the language you want to use. This guide assumes that you chose Java.

3. Click Dependencies and select Spring Web.

4. Click Generate.

5. Download the resulting ZIP file, which is an archive of a web application that is configured with your choices.

or you can download from [springboot-starter-maven](https://github.com/larryso/Springboot/tree/master/springboot-helloword)

## Create a Simple Web Application

``` java
@RestController
@RequestMapping("/api")
@Slf4j
public class HelloWordController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("getMap")
    public HashMap<String, Object> getMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("errorCode", 200);
        result.put("errorMessage", "Success...");
        return result;
    }
    @GetMapping("/index/get/{id}")
    public ResponseEntity<String> indexGet(@PathVariable Long id){
        String strMessage = "Greeting From Springboot " + id;
        return ResponseEntity.status(HttpStatus.OK).body(strMessage);
    }
    @PostMapping("/index/writePost")
    public ResponseEntity<UserDTO> writePost(@RequestBody UserDTO userDTO){
        log.info(String.valueOf(userDTO));
        return ResponseEntity.ok(userDTO);
    }
}
```
The class is flagged as a @RestController, meaning it is ready for use by Spring MVC to handle web requests. @GetMapping maps / to the index() method. When invoked from a browser or by using curl on the command line, the method returns pure text. That is because @RestController combines @Controller and @ResponseBody, two annotations that results in web requests returning data rather than a view.

### @RequestMapping

This annotation is used to map web requests to Spring Controller methods

it could be used in class level (map a request to a controller), and it could also used to method level like:

```  java
@RequestMapping(value = "/ex/foos", method = RequestMethod.GET)
public String getFoosBySimplePath(){
        return "Get some Foos";
}
```
@RequestMapping value parameter specifies the request path, method parameter has no default, if you not specify a value, it's going to map to any HTTP request

@RequestMapping With the headers Attribute
The mapping can be narrowed even further by specifying a header for the request:

``` java
   @RequestMapping(value = "/ex/foos", method = RequestMethod.GET,
            headers = {"key1=val1", "key2=val2"})
    public String getFoosBySimplePath() {
        return "Get some Foos";
    }
```

@RequestMapping Consumes and Produces
Mapping media types produced by a controller method is worth special attention.

We can map a request based on its Accept header via the @RequestMapping headers attribute introduced above:
```  java
@RequestMapping(
  value = "/ex/foos", 
  method = GET, 
  headers = "Accept=application/json")
public String getFoosAsJsonFromBrowser() {
    return "Get some Foos with Header Old";
}
```
Starting with Spring 3.1, the @RequestMapping annotation now has the produces and consumes attributes, specifically for this purpose:

``` java
@RestController
@RequestMapping(value="/api",produces = APPLICATION_JSON_VALUE)
@Slf4j
public class HelloWordController {

    @PostMapping(value="/index/writePost", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> writePost(@RequestBody UserDTO userDTO) {
        log.info(String.valueOf(userDTO));
        return ResponseEntity.ok(userDTO);
    }
}
```

https://spring.io/guides/gs/spring-boot/
