package com.larry.java8Demo.lambda.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate 接口中包含一个抽象方法： boolean test(T t) 。用于条件判断的场景 默认方法: default Predicate
 * and(Predicate<? super T> other) : 将两个Predicate 条件使用“与”逻辑连接起来实现“并且”的效果 default
 * Predicate or(Predicate<? super T> other) : or 实现逻辑关系中的“或” default Predicate
 * negate() : 取反
 * 
 * @author larryso
 *
 */
public class PredicateTest {
	/*
	 * 检查数组中的元素是否符合要求,满足要求加入List中并返回
	 * 
	 * @param arr 需要判断的数组
	 * 
	 * @param pre1 判断接口1,判断性别是否为女
	 * 
	 * @param pre2 判断接口2,判断姓名长度是否大于2
	 * 
	 * @return 集合
	 */
	public static List<String> checkStar(String[] arr, Predicate<String> pre1, Predicate<String> pre2) {
		List<String> list = new ArrayList<String>();
		for (String s : arr) {
			if (pre1.and(pre2).test(s)) {
				list.add(s);
			}
		}

		return list;
	}

	public static void main(String[] args) {
		String[] s = { "Jack, male", "Soon, female", "Larry, male" };
		List<String> list = checkStar(s, (message) -> {
			return "female".equals(message.split(",")[1].trim());
		}, (message) -> {
			return message.split(",")[0].length()>2;
		});
		for(String ss: list) {
			System.out.println(ss);
		}
	}

}
