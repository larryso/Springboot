package com.larry.service;

import java.util.Map;

import com.larry.bean.AccessToken;
import com.larry.bean.WeixinUser;

public interface WeixinUserService {
	public WeixinUser getUserInfo(String accessToken, String openId);

	public Map<String, String> oauth2GetOpenid(String code);

	public Map<String, String> getAuthInfo(String code);
	
	public AccessToken getAccessToken();

}
