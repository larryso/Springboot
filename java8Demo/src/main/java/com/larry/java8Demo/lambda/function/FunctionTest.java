package com.larry.java8Demo.lambda.function;

import java.util.function.Function;

/*
 * Function 接口中最主要的抽象方法为： R apply(T t) ，
 * 根据类型T的参数获取类型R的结果。
   Function 接口中有一个默认的andThen 方法，用来进行组合操作。
 */
public class FunctionTest {
	/**
	 * 将String分割,获得第二个元素,将数据转化为int,int数据加100,再将int转化为String
	 * 
	 * @param str  转化的数据
	 * @param fun1 String -> String
	 * @param fun2 String -> Integer
	 * @param fun3 Integer -> String
	 * @return 最后的String
	 */

	public static String convert(String str, Function<String, String> fun1, Function<String, Integer> fun2,
			Function<Integer, String> fun3) {
		return fun1.andThen(fun2).andThen(fun3).apply(str);

	}

	public static void main(String[] args) {
		String s = "Larry,35";
		s = convert(s, (message) -> {
			return message.split(",")[1];
		}, (message) -> {
			return Integer.valueOf(message) + 100;
		}, (message) -> {
			return String.valueOf(message);
		});
		System.out.println(s);
	}

}
