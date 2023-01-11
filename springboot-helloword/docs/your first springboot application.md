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

#### @RequestMapping

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

## Create an Application class

``` java
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx){
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

}
```
@SpringBootApplication is a convenience annotation that adds all of the following:

@Configuration: Tags the class as a source of bean definitions for the application context.

@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

@ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.

The main() method uses Spring Boot’s SpringApplication.run() method to launch an application. Did you notice that there was not a single line of XML? There is no web.xml file, either. This web application is 100% pure Java and you did not have to deal with configuring any plumbing or infrastructure.

There is also a CommandLineRunner method marked as a @Bean, and this runs on start up. It retrieves all the beans that were created by your application or that were automatically added by Spring Boot. It sorts them and prints them out.

## Unit Tests
You will want to add a test for the endpoint you added, and Spring Test provides some machinery for that.

If you use Gradle, add the following dependency to your build.gradle file:

testImplementation('org.springframework.boot:spring-boot-starter-test')COPY
If you use Maven, add the following to your pom.xml file:

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>COPY
Now write a simple unit test that mocks the servlet request and response through your endpoint, as the following listing (from src/test/java/com/example/springboot/HelloControllerTest.java) shows:

``` java
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
	}
}
```

MockMvc comes from Spring Test and lets you, through a set of convenient builder classes, send HTTP requests into the DispatcherServlet and make assertions about the result. Note the use of @AutoConfigureMockMvc and @SpringBootTest to inject a MockMvc instance. Having used @SpringBootTest, we are asking for the whole application context to be created. An alternative would be to ask Spring Boot to create only the web layers of the context by using @WebMvcTest. In either case, Spring Boot automatically tries to locate the main application class of your application, but you can override it or narrow it down if you want to build something different.

As well as mocking the HTTP request cycle, you can also use Spring Boot to write a simple full-stack integration test. For example, instead of (or as well as) the mock test shown earlier, we could create the following test (from src/test/java/com/example/springboot/HelloControllerIT.java):

``` java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

	@Autowired
	private TestRestTemplate template;

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }
}
```
The embedded server starts on a random port because of webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, and the actual port is configured automatically in the base URL for the TestRestTemplate.

## Refrence
[https://spring.io/guides/gs/spring-boot/](https://spring.io/guides/gs/spring-boot/)
