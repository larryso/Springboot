package com.larry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class AppConf {
	@Value("${weixin.user.url}")
	private String getWeixinUserUrl;
	@Value("${page.users.url}")
	private String pageUsersUrl;
	@Value("${webauth.url}")
	private String webAuthUrl;
	@Value("${weixinpage.code}")
	private String weixinpageCode;
	@Value("${access.token.url}")
	private String accessTokenUrl;
	@Value("${weixin.appID}")
	private String appID;
	@Value("${weixin.appsecret}")
	private String appsecret;
	@Value("${weixin.subscribe.msg}")
	private String weixinSubscribMsg;
}