## lombok Annotation

### @Accessors
The @Accessors annotation is used to configure how lombok generate and looks for getters setters

@Accessors(fluent = true) 省略给对象赋值和取值时候得set、get前缀    看示例：
```java
@Data
@Accessors(fluent = true)   //不用带set和get前缀   user.name("aaa").age(18)
public class User implements Serializable {
 
 
    private String id;
    private String name;
    private int age;
 
 
    public static void main(String[] args) {
        User user = new User();
        user.id("124").age(19).name("小丽");
        System.out.println(user);
    }
 
}
```
[https://projectlombok.org/features/experimental/Accessors](https://projectlombok.org/features/experimental/Accessors)

### @UtiltiClass
A utility class is just a namespace for functions, no instance of the class exists, all functions are static

```java
import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilityClassExample {
　　private final int CONSTANT = 5;

　　public int addSomething(int in) {
　　　　return in + CONSTANT;
　　}
}
```
will compiled to: 
``` java
public final class UtilityClassExample {
　　private static final int CONSTANT = 5;

　　private UtilityClassExample() {
　　　　throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
　　}

　　public static int addSomething(int in) {
　　　　return in + CONSTANT;
　　}
}
```

### @UtiltiClass
@Value is the immutable variant of @Data; all fields are made private and final by default, and setters are not generated. The class itself is also made final by default, because immutability is not something that can be forced onto a subclass. Like @Data, useful toString(), equals() and hashCode() methods are also generated, each field gets a getter method, and a constructor that covers every argument (except final fields that are initialized in the field declaration) is also generated
```java
@Value(staticConstructor = "newInstance")
public class Angelababy {
    private String name;
    
    private static Integer age;
}


public final class Angelababy {
      private final String name;
      
      private static Integer age;
    
      private Angelababy(String name) {
        this.name = name;
    }
    
    public static Angelababy newInstance(String name) {
        return new Angelababy(name);
    }
    
      public String getName() {
          return this.name;
      }
}
```