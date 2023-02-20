# Spring Boot: Customize the Jackson ObjectMapper

## Overview
When using JSON format, Spring Boot will use an ObjectMapper instance to serialize responses and deserialize requests.

In this tutorial, we'll take a look at the most common ways to configure the serialization and deserialization options.

## Default Configuration
By default, the Spring Boot configuration will disable the following:

* MapperFeature.DEFAULT_VIEW_INCLUSION
* DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
* SerializationFeature.WRITE_DATES_AS_TIMESTAMPS

Let's start with a quick example:

Let's say we want a cup of Coffee:

``` java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private String name;
    private String brand;
    private LocalDateTime date;
}
```

We define a simple REST controller to demonstrate the serialization:

```java
   @GetMapping("/getCoffee")
    public Coffee getCoffee(@RequestParam String name, @RequestParam(required = false) String brand){
        return Coffee.builder().name(name).brand(brand).date(LocalDateTime.now()).build();
    }
```

By default, below json response we will get when calling: http://localhost:8080/api/index/getCoffee?name=Natin

```json
{
"brand": null,
"date": {
	"chronology": {
		"calendarType": "iso8601",
		"id": "ISO"
	},
	"dayOfMonth": 13,
	"dayOfWeek": "MONDAY",
	"dayOfYear": 44,
	"hour": 22,
	"minute": 11,
	"month": "FEBRUARY",
	"monthValue": 2,
	"nano": 591000000,
	"second": 39,
	"year": 2023
},
"name": "Natin"
}
```
We would like to exclude null values and to have a custom date format (dd-MM-yyyy HH:mm). 
When using Spring Boot, we have the option to customize the default ObjectMapper or to override it.

### When using Spring Boot, we have the option to customize the default ObjectMapper or to override it.

#### The simplest way to configure the mapper is via application properties.
Here's the general structure of the configuration:

spring.jackson.<category_name>.<feature_name>=true,false

As an example, here's what we'll add to disable SerializationFeature.WRITE_DATES_AS_TIMESTAMPS:

spring.jackson.serialization.write-dates-as-timestamps=false

The downside of this approach is that we can't customize advanced options like having a custom date format for LocalDateTime.(Speingboot does not provide configuration for formating LocalDateTime and LocalDate)

#### To achieve our goal, we can register our customized Serializer and Deserializer like below:

```java
    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = ObjectMapperUtils.createObjectMapper();
        objectMapper.deactivateDefaultTyping();
        SimpleModule module = new SimpleModule("");
        module.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        objectMapper.registerModule(module);
        return objectMapper;
    }
```

#### we can also use Jackson2ObjectMapperBuilderCustomizer interface to customize our Serializer and Deserializer:

``` java
@Configuration
public class JacksonConfiguration{
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
      .serializers(new XXXSerilizer); // customize our own Serializer and Deserializer, we will introduce this latter 
}
}

```

#### Another clean approach is to define a Jackson2ObjectMapperBuilder bean.

Spring Boot actually uses this builder by default when building the ObjectMapper and will automatically pick up the defined one:

``` java
@Bean
public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
    return new Jackson2ObjectMapperBuilder().serializers(LOCAL_DATETIME_SERIALIZER)
      .serializationInclusion(JsonInclude.Include.NON_NULL);
}
```

## Reference 

[Jackson JSON Tutorial](https://www.baeldung.com/jackson)

[Jackson github](https://github.com/FasterXML/jackson)
