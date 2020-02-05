package com.larry.bean;

import java.util.Map;

import lombok.Data;
@Data
public class WechatTemplate {
	private String touser;

	private String template_id;

	private String url;

	private Map<String, WeChatTemplateData> data;
}
