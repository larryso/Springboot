# Intro to the Jackson ObjectMapper
## Setup
Jackson is a JAVA  json library, need below dependecny:
* jackson-core
* jackson-annotation
* jackson-databind

```
implementation group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.8.8'
implementation 'com.fasterxml.jackson.core:jackson-core:2.8.8'
 implementation 'com.fasterxml.jackson.core:jackson-annotation:2.8.8'
```

## Customize the jackson ObjectMapper
Spring Boot uses an ObjectMapper instance to serialize responses and deserialize requests.
By default, the Spring Boot will disable the following:
* MapperFeature.DEFAULT_VIEW_INCLUSION
* DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
* SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
Let’s start with a quick example:
```java
public class Coffee {

    private String name;
    private String brand;
    private LocalDateTime date;

   //getters and setters
}

    @GetMapping("/coffee")
    public Coffee getCoffee(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String name) {
        return new Coffee()
                .setBrand(brand)
                .setDate(FIXED_DATE)
                .setName(name);
    }
```
By default, this will be the response when calling GET http://lolcahost:8080/coffee?brand=Lavazza:
```java
{
"name": null,
"brand": "Lavazza",
"date": "2020-11-16T10:21:35.974"
}
```
We would like to exclude null value and to have a custom date format - dd-MM-yyyy HH:mm, when using Spring Boot, we have below options to customize the default ObjectMapper:

1. Customizing in Application Properties
```java
spring:
  jackson:
    default-property-inclusion: non_null
    date-format: java.text.SimpleDateFormat
```
2. defined your own ObjectMapper Bean, and mark it as Primarry
```java
    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = ObjectMapperUtils.createObjectMapper();
        //objectMapper.deactivateDefaultTyping();
        SimpleModule module = new SimpleModule("initialization", new Version(0,1,0,"", "com.larry", "spring-boot-initialization"));
        module.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        objectMapper.registerModule(module);
        return objectMapper;
    }
```


## Reference 

[Jackson JSON Tutorial](https://www.baeldung.com/jackson)

[Jackson github](https://github.com/FasterXML/jackson)

## Jackson Annotation
![https://www.tutorialspoint.com/jackson_annotations/index.htm](https://www.tutorialspoint.com/jackson_annotations/index.htm)
@JsonValue allows to serialize an entire object using its single method.
```
public enum Gender {
    MALE("1"), FEMALE("0");

    private String sex;
    Gender(String sex){
        this.sex = sex;
    }
    public String sex(){
        return this.sex;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String name;
    private int rollNo;
    private Gender gender;
    private String schoolName;
}

public class AnnotationTest {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Student student = Student.builder().
                gender(Gender.FEMALE).
                name("Larry").
                rollNo(1).schoolName("Middle School").build();
        String studentJson = mapper.writeValueAsString(student);
        System.out.println(studentJson);
    }
}

```
if we do not make change to enum, the output will be like: {"name":"Larry","rollNo":1,"gender":"FEMALE","schoolName":"Middle School"}

we can use @JsonValue to customize what value we want it return

```
public enum Gender {
    MALE("1"), FEMALE("0");

    private String sex;
    Gender(String sex){
        this.sex = sex;
    }
    @JsonValue
    public String sex(){
        return this.sex;
    }
}
```
the output will be like: {"name":"Larry","rollNo":1,"gender":"0","schoolName":"Middle School"}

@JsonIgnore is used at field level to mark a property or list of properties to be ignored.

@JsonCreator is used to fine tune the constructor or factory method used in deserialization.
Note: @JsonCreator 中能用在static 方法 或者 Constructor 上
```

public enum Gender {
    MALE("1"), FEMALE("0");

    private String sex;
    Gender(String sex){
        this.sex = sex;
    }
    public static Gender getBySex(String sex){
        Gender[] values = values();
        for(Gender gender: values){
            if(gender.sex().equals(sex)){
                return gender;
            }
        }
        return null;
    }
    @JsonCreator
    public static Gender fromObject(String sex){
        return Gender.getBySex(sex);
    }
    @JsonValue
    public String sex(){
        return this.sex;
    }
}
```