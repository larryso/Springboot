# Spring boot Annotations should known

## Annotations for Web

### @RequestParram/@RequestBoday/@RequestPart/@PathVariable
Before we get start about this part, let's first know content-type in http header
For more knowledge, please refer to [https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Type](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Type)

The Content-Type representation header is used to indicate the original media type of the resource (prior to any content encoding applied for sending).
In requests, (such as POST or PUT), the client tells the server what type of data is actually sent. server side depends on this to retreive value from http body.

For example, if client side set content-type as application/x-www-form-urlencoded, and parameter as key=value&key2=value2, server side will use & to retrive key and value from form data

for more knowledge, please refer to [https://www.jianshu.com/p/53b5bd0f1d44](https://www.jianshu.com/p/53b5bd0f1d44).

In Springboot application, springboot use below anotations to map http request header-type and handle request body
* @RequestParam - application/x-www-form-urlencoded
* @RequestBody - application/json
* @RequestPart - multipart/form-data

@RequestParam make spring to map request parameters from the Get/Post request to your methord argument.
```java
public Strng getCountryFactory(@RequestParam(value="city") String city,
                               @RequestParam(value="country") String country){
}
```

@RequestBody makes spring to map entire request (json format data) to a model class, parameter values are converted to the declared method argument type by using HttpMessageConverters, this annotation indicates a method parameter should be bound to the body of the web request
```java
public String getCountryFactory(@RequestBody Country country){
}
```

@RequestPart used to associate the part of multipart/form-data request with a method argument, supported argument types including MultipartFile and otehr method arguments that passed through HttpMessageConverter

```java
@RequestMapping(path="employee", method=POST, consumes={MediaType.MULTIPART_FORM_DATE_VALUE})
public String saveEmployee(@RequestPart Employee employee, 
                           @RequestPart MultipartFile, file){
}
```

@PathVariable used to handle variables in the request URL:

```java
@GetMapping("api/employee/{id}/{name}")
public Employee getEmployeeByIdAndName(@PathVariable String id, @PathVariable String name){
}
```
or handle more path variables:

```java
@GetMapping("api/employee/{id}/{name}")
public Employee getEmployeeByIdAndName(@PathVariable Map<String, String> pathMap){
    String id = pathMap.get("id");
    String name = pathMap.get("name");
    ....
}
```
we can use required properties to make it optional:
```java
@GetMapping("api/employee/{id}/{name}", "api/employee/{id}")
public Employee getEmployeeByIdAndName(@PathVariable String id, @PathVariable(required=false) String name){
   
    ....
}
```