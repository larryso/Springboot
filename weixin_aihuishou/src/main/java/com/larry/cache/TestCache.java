package com.larry.cache;

public class TestCache {
	public static void main(String[] args) {
		EhCacheUtil.put("test", "larry");
		System.out.println(EhCacheUtil.get("test"));
		EhCacheUtil.shutdown();
	}

}
