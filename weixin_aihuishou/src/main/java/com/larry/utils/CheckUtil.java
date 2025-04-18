package com.larry.utils;

import java.util.Arrays;

public class CheckUtil {
	private static final String weixin_token = "aihuishou";
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = {weixin_token, timestamp, nonce };
		Arrays.sort(arr);
		StringBuffer str = new StringBuffer();
		for(String s: arr) {
			str.append(s);
		}
		String shahexStr = SecuritySHA1Util.sha1Encoding(str.toString());
		return shahexStr.equals(signature);
	}
}
