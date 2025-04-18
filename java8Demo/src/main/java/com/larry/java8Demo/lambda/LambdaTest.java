package com.larry.java8Demo.lambda;

import com.larry.java8Demo.lambda.function.MyFunctionalInterface;

/**
 * 
 * @author larryso 在lambda中我们遵循如下的表达式来编写： 
 * expression = (variable) -> action
 *
 */
public class LambdaTest {
	public static void main(String[] args) {
		Runnable r = () -> System.out.println("Hellow World");
		new Thread(r).run();
		new Thread(()->System.out.println(System.currentTimeMillis())).run();
		show(()->System.out.println("myFunctionalInterface show()"));
	}
	public static void show(MyFunctionalInterface my) {
		my.show();
		my.defaultShow();
	}

}

