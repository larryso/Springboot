# Functional Interface
![](../../images/java.webp)

A functional interface is an interface that contains only one abstract method. They can have only one functionality to exhibit. From Java 8 onwards, [lambda expressions](./Lambda_Expressions.md) can be used to represent the instance of a functional interface. A functional interface can have any number of default methods. Runnable, ActionListener, Comparable are some of the examples of functional interfaces. 

Functional interfaces are used and executed by representing the interface with an annotation called @FunctionalInterface. 

We can also call Lambda expressions as the instance of functional interface.

``` java

@FunctionalInterface
interface FunctionalInteface1{
    public abstract void method1();

    //public abstract void method2(); // compiured failed as functional interface only allow one abstract method

    default void defaultMethod1(){
        System.out.println("Default method 1....");
    }

    default void defaultMethod2(){
        System.out.println("Default method 2....");
    }
}
public class FunctionalInterfaceTest {
    public static void main(String[] args) {
      FunctionalInteface1 functionalInteface1 = () ->{
          System.out.println("this is functional abstract method implemented.");
      };
      functionalInteface1.method1();
      functionalInteface1.defaultMethod1();
      functionalInteface1.defaultMethod2();
    }
}
```

## Supplier functional interface

Supplier is a functional interface that produces results without accepting any inputs. The results produces each time can be the same or different, the interace contains only one method get()
```java

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<Integer> integerSupplier = () -> new Random().nextInt();

        System.out.println(integerSupplier.get());
        System.out.println(integerSupplier.get());
        System.out.println(integerSupplier.get());
    }
}
```

