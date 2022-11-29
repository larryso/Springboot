package com.larry.java8Demo.functionalInterface;

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
