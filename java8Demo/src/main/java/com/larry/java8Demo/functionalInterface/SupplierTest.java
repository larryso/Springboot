package com.larry.java8Demo.functionalInterface;

import java.util.Random;
import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<Integer> integerSupplier = () -> new Random().nextInt();

        System.out.println(integerSupplier.get());
        System.out.println(integerSupplier.get());
        System.out.println(integerSupplier.get());
    }
}
