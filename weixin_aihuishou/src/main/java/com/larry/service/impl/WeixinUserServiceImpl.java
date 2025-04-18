package com.larry.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.larry.AppConf;
import com.larry.bean.AccessToken;
import com.larry.bean.WeixinUser;
import com.larry.service.WeixinUserService;
import com.larry.utils.WeixinUtils;

import net.sf.json.JSONObject;

@Service
public class WeixinUserServiceImpl implements WeixinUserService {
	@Autowired
	private AppConf appConf;

	public WeixinUser getUserInfo(String accessToken, String openId) {
		WeixinUser weixinUserInfo = null;
		String requestUrl = appConf.getGetWeixinUserUrl().replace("ACCESS_TOKEN", accessToken).replace("OPENID",
				openId);
		JSONObject jsonObject = WeixinUtils.doGetStr(requestUrl);
		if (null != jsonObject) {
			try {
				weixinUserInfo = new WeixinUser();
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				weixinUserInfo.setCity(jsonObject.getString("city"));
				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return weixinUserInfo;
	}

	public Map<String, String> oauth2GetOpenid(String code) {
		String appid = appConf.getAppID();
		String appsecret = appConf.getAppsecret();
		String requestUrl = appConf.getWebAuthUrl().replace("APPID", appid).replace("SECRET", appsecret).replace("CODE",
				code);
		Map<String, String> result = new HashMap<String, String>();
		try {
			JSONObject OpenidJSONO = WeixinUtils.doGetStr(requestUrl);
			String Openid = String.valueOf(OpenidJSONO.get("openid"));
			String AccessToken = String.valueOf(OpenidJSONO.get("access_token"));
			String Scope = String.valueOf(OpenidJSONO.get("scope"));
			String refresh_token = String.valueOf(OpenidJSONO.get("refresh_token"));
			result.put("Openid", Openid);
			result.put("AccessToken", AccessToken);
			result.put("scope", Scope);
			result.put("refresh_token", refresh_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, String> getAuthInfo(String code) {
		Map<String, String> result = oauth2GetOpenid(code);
		String openId = result.get("Openid");
		return result;
	}

	public AccessToken getAccessToken() {
		AccessToken accessToken = new AccessToken();
		String url = appConf.getAccessTokenUrl().replace("APPID", appConf.getAppID()).replace("APPSECRET",
				appConf.getAppsecret());
		JSONObject jsonObject = WeixinUtils.doGetStr(url);
		if (jsonObject != null) {
			accessToken.setToken(jsonObject.getString("access_token"));
			accessToken.setExpireIn(jsonObject.getInt("expires_in"));
		}
		return accessToken;
	}

}
