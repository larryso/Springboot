package com.larry.service;

public interface WeixinMessageService {
	public String subscribeForText(String toUserName, String fromUserName,String msgContent);

	public String unsubscribe(String toUserName, String fromUserName);
	
	public String sendTextMsg(String toUserName, String fromUserName, String msgContent);
}