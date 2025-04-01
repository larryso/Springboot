package com.larry.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private static Calculator calculator;

    @BeforeAll
    static void beforeTest(){
        calculator = new Calculator();
    }
    @Test
    @Tag("jacocoReport")
    void testAdd(){
        double result = calculator.calculate(1,2,'+');
        Assertions.assertEquals(3, result);
    }
    @Test
    @Tag("jacocoReport")
    void testSubstract(){
        double result = calculator.calculate(10,2,'-');
        Assertions.assertEquals(8, result);
    }
    @Test
    @Tag("jacocoReport")
    void testMultiply(){
        double result = calculator.calculate(10,2,'*');
        Assertions.assertEquals(20, result);
    }
    @Test
    @Tag("jacocoReport")
    void testDivide(){
        double result = calculator.calculate(10,2,'/');
        Assertions.assertEquals(5, result);
    }
}
