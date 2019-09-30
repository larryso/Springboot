package com.larry.service.impl;

import org.springframework.stereotype.Component;

import com.larry.service.WeixinMessageService;
import com.larry.utils.WeixinUtils;
@Component
public class WeixinMessageServiceImp implements WeixinMessageService{

	public String subscribeForText(String toUserName, String fromUserName, String msgContent) {
		
		return WeixinUtils.textMsg(toUserName, fromUserName, msgContent);
	}

	public String unsubscribe(String toUserName, String fromUserName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendTextMsg(String toUserName, String fromUserName, String msgContent) {
		// TODO Auto-generated method stub
		return WeixinUtils.textMsg(toUserName, fromUserName, msgContent);
	}

}
