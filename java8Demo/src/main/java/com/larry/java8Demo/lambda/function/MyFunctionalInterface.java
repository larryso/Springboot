package com.larry.java8Demo.lambda.function;
/**
 * 
 * @author larryso
 *
 */
@FunctionalInterface
public interface MyFunctionalInterface{
	public void show();
	default public void defaultShow() {
		System.out.println("Helow FunctionalInterface");
	}
}
