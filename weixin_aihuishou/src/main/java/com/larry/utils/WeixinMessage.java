package com.larry.utils;

public enum WeixinMessage {
	MSGTYPE_EVENT("event"), MESSAGE_SUBSCIBE("subscribe"), MESSAGE_UNSUBSCIBE("unsubscribe"), MESSAGE_TEXT("text"),MESSAGE_CLICK("CLICK");
	private final String messageType;

	WeixinMessage(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageType() {
		return messageType;
	}

}
