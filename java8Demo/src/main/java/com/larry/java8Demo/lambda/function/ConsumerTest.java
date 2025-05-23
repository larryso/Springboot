package com.larry.java8Demo.lambda.function;

import java.util.function.Consumer;

/**
 * 
 * @author larryso 这个方法是用来消费数据的,如何消费,消费规则自己定义. java.util.function.Supplier
 *         接口仅包含一个无参的方法： T get() 。用来获取一个泛型参数指定类型的对
 *         象数据。由于这是一个函数式接口，这也就意味着对应的Lambda表达式需要“对外提供”一个符合泛型类型的对象 数据。
 */
public class ConsumerTest {
	public static void printinfo(String[] args, Consumer<String> colu1, Consumer<String> colu2) {
		for (String s : args) {
			colu1.andThen(colu2).accept(s);
		}
	}

	public static void main(String[] args) {
		String[] s = { "Jack, male", "Soon, femail", "Larry, male" };
		printinfo(s, (message) -> {
			System.out.println(message.split(",")[0]);
		}, (message) -> {
			System.out.println(message.split(",")[1]);
		});
	}

}
