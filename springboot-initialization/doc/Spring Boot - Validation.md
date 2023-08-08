# how to validate domain objects in Spring Boot

When it comes to validating user input, Springboot provide strong support for this.

indluding basic validation and customered validator and Hibernate Validator.

## Dependency
```
<dependency> 
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-validation</artifactId> 
</dependency>
```
However, if we have also included the web starter, the validation starter comes for free:

implementation('org.springframework.boot:spring-boot-starter-web')

Note that the validation starter does no more than adding a dependency to a compatible version of hibernate validator, which is the most widely used implementation of the Bean Validation specification.


## Basic Bean validation
Some of the most common validation annotations are:

@NotNull: to say that a field must not be null.
@NotEmpty: to say that a list field must not empty.
@NotBlank: to say that a string field must not be the empty string (i.e. it must have at least one character).
@Min and @Max: to say that a numerical field is only valid when it’s value is above or below a certain value.
@Pattern: to say that a string field is only valid when it matches a certain regular expression.
@Email: to say that a string field must be a valid email address.

Bean class:

```java
class Customer {
       
         @Email
         private String email;
       
         @NotBlank
         private String name;
         
         // ...
       }
```
To validate if an object is valid, we pass it into a Validator which checks if the constraints are satisfied:
```java
Set<ConstraintViolation<Input>> violations = validator.validate(customer);
if (!violations.isEmpty()) {
  throw new ConstraintViolationException(violations);
}
```
In many cases, however, Spring does the validation for us. We don’t even need to create a validator object ourselves. 
To validate the request body of an incoming HTTP request, we annotate the request body with the @Valid annotation in a REST controller:
```java
@RestController
class ValidateRequestBodyController {

  @PostMapping("/validateBody")
  ResponseEntity<String> validateBody(@Valid @RequestBody Input input) {
    return ResponseEntity.ok("valid");
  }

}
```
We simply have added the @Valid annotation to the Input parameter, which is also annotated with @RequestBody to mark that it should be read from the request body. By doing this, we’re telling Spring to pass the object to a Validator before doing anything else.
If the validation fails, it will trigger a MethodArgumentNotValidException. By default, Spring will translate this exception to a HTTP status 400 (Bad Request).
Later we will see [how to return a structured error response]()

## Validating Path Variables and Request Parameters
Instead of annotating a class field like above, we’re adding a constraint annotation (in this case @Min) directly to the method parameter in the Spring controller:
```java
@RestController
@Validated
class ValidateParametersController {

  @GetMapping("/validatePathVariable/{id}")
  ResponseEntity<String> validatePathVariable(
      @PathVariable("id") @Min(5) int id) {
    return ResponseEntity.ok("valid");
  }
  
  @GetMapping("/validateRequestParameter")
  ResponseEntity<String> validateRequestParameter(
      @RequestParam("param") @Min(5) int param) { 
    return ResponseEntity.ok("valid");
  }
}
```
Note that we have to add Spring’s @Validated annotation to the controller at class level to tell Spring to evaluate the constraint annotations on method parameters.

The @Validated annotation is only evaluated on class level in this case, even though it’s allowed to be used on methods (we’ll learn why it’s allowed on method level when discussing [validation groups]() later).

In contrast to request body validation a failed validation will trigger a ConstraintViolationException instead of a MethodArgumentNotValidException. Spring does not register a default exception handler for this exception, so it will by default cause a response with HTTP status 500 (Internal Server Error).

If we want to return a HTTP status 400 instead (which makes sense, since the client provided an invalid parameter, making it a bad request), we can add a custom exception handler to our contoller:
```java
@RestController
@Validated
class ValidateParametersController {

  // request mapping method omitted
  
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
    return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
```

## Validating Input to a Spring Service Method
Instead of (or additionally to) validating input on the controller level, we can also validate the input to any Spring components. In order to to this, we use a combination of the @Validated and @Valid annotations:
```java
@Service
@Validated
class ValidatingService{

    void validateInput(@Valid Input input){
      // do something
    }

}
```
Again, the @Validated annotation is only evaluated on class level, so don’t put it on a method in this use case.

## Validating JPA Entities
Let’s say want to store objects of our Input class to the database. First, we add the necessary JPA annotation @Entity and add an ID field:
```java
@Entity
public class Input {

  @Id
  @GeneratedValue
  private Long id;

  @Min(1)
  @Max(10)
  private int numberBetweenOneAndTen;

  @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
  private String ipAddress;
  
  // ...
  
}
```
Then, we create a Spring Data repository that provides us with methods to persist and query for Input objects:
`
public interface ValidatingRepository extends CrudRepository<Input, Long> {}
`
By default, any time we use the repository to store an Input object whose constraint annotations are violated, we’ll get a ConstraintViolationException if validation failed.
Note that Bean Validation is only triggered by Hibernate once the EntityManager is flushed.
If for any reason we want to disable Bean Validation in our Spring Data repositories, we can set the Spring Boot property spring.jpa.properties.javax.persistence.validation.mode to none.

## Custom Validator with Spring Boot
If the available constraint annotations do not suffice for our use cases, we might want to create one ourselves.
First, we create the custom constraint annotation IpAddress:
```java
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
@Documented
public @interface IpAddress {

  String message() default "{IpAddress.invalid}";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

}
```
* the parameter message, pointing to a property key in ValidationMessages.properties, which is used to resolve a message in case of violation,
* the parameter groups, allowing to define under which circumstances this validation is to be triggered (we’re going to talk about validation groups later),
* the parameter payload, allowing to define a payload to be passed with this validation (since this is a rarely used feature, we’ll not cover it in this tutorial), and
* a @Constraint annotation pointing to an implementation of the ConstraintValidator interface.
The validator implementation looks like this:
```java
class IpAddressValidator implements ConstraintValidator<IpAddress, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Pattern pattern = 
      Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$");
    Matcher matcher = pattern.matcher(value);
    try {
      if (!matcher.matches()) {
        return false;
      } else {
        for (int i = 1; i <= 4; i++) {
          int octet = Integer.valueOf(matcher.group(i));
          if (octet > 255) {
            return false;
          }
        }
        return true;
      }
    } catch (Exception e) {
      return false;
    }
  }
}
```

## Validating Programmatically

```java
@Service
class ProgrammaticallyValidatingService {

  private Validator validator;

  ProgrammaticallyValidatingService(Validator validator) {
    this.validator = validator;
  }

  void validateInputWithInjectedValidator(Input input) {
    Set<ConstraintViolation<Input>> violations = validator.validate(input);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
```
## Using Validation Groups to Validate Objects Differently for Different Use Cases
Often, certain objects are shared between different use cases.

Let’s take the typical CRUD operations, for example: the “Create” use case and the “Update” use case will most probably both take the same object type as input. However, there may be validations that should be triggered under different circumstances:

only in the “Create” use case,
only in the “Update” use case, or
in both use cases.

The Bean Validation feature that allows us to implement validation rules like this is called “Validation Groups”.

We have already seen that all constraint annotations must have a groups field. This can be used to pass any classes that each define a certain validation group that should be triggered.
For our CRUD example, we simply define two marker interfaces OnCreate and OnUpdate:
```java
interface OnCreate {}

interface OnUpdate {}
```
We can then use these marker interfaces with any constraint annotation like this:
```java
class InputWithGroups {

  @Null(groups = OnCreate.class)
  @NotNull(groups = OnUpdate.class)
  private Long id;
  
  // ...
  
}
```
This will make sure that the ID is empty in our “Create” use case and that it’s not empty in our “Update” use case.

Spring supports validation groups with the @Validated annotation:
```java
@Service
@Validated
class ValidatingServiceWithGroups {

    @Validated(OnCreate.class)
    void validateForCreate(@Valid InputWithGroups input){
      // do something
    }

    @Validated(OnUpdate.class)
    void validateForUpdate(@Valid InputWithGroups input){
      // do something
    }

}
```
Note that the @Validated annotation must again be applied to the whole class. To define which validation group should be active, it must also be applied at method level.
## Handling Validation Errors
First, we need to define that data structure. 
```java
public class ValidationErrorResponse {

  private List<Violation> violations = new ArrayList<>();

  // ...
}

public class Violation {

  private final String fieldName;

  private final String message;

  // ...
}
```
Then, we create a global ControllerAdvice that handles all ConstraintViolationExceptions that bubble up to the controller level. In order to catch validation errors for request bodies as well, we will also handle MethodArgumentNotValidExceptions:
```java
@ControllerAdvice
class ErrorHandlingControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ValidationErrorResponse onConstraintValidationException(
      ConstraintViolationException e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      error.getViolations().add(
        new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ValidationErrorResponse onMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error.getViolations().add(
        new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    return error;
  }

}
```
## Reference

[https://reflectoring.io/bean-validation-with-spring-boot/](https://reflectoring.io/bean-validation-with-spring-boot/)
[https://reflectoring.io/bean-validation-with-spring-boot/#validating-programmatically](https://reflectoring.io/bean-validation-with-spring-boot/#validating-programmatically)
