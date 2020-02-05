package com.larry.cache;

import java.math.BigDecimal;

public class TestCache {
	public static void main(String[] args) {
		String s = "0.6元/公斤";
		BigDecimal price = new BigDecimal(s.substring(0,s.indexOf("/")-1));
		BigDecimal weight = new BigDecimal("1.3");
		
		System.out.println(price.multiply(weight));
	}

}
