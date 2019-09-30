package com.larry.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class SecuritySHA1Util {
	public static String sha1Encoding(String str) {

		if (StringUtils.isEmpty(str)) {

			return null;

		} else {

			return DigestUtils.shaHex(str);

		}

	}
}
