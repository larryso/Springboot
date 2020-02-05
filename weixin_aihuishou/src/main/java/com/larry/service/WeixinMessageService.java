package com.larry.service;

import com.larry.entity.OrderPO;

public interface WeixinMessageService {
	public String subscribeForText(String toUserName, String fromUserName, String msgContent);

	public String unsubscribe(String toUserName, String fromUserName);

	public String sendTextMsg(String toUserName, String fromUserName, String msgContent);

	public String sendPicMsgWithoutUrl(String toUserName, String fromUserName, String msgContent);

	public String direct2helpDesk(String toUserName, String fromUserName, String msgContent);

	public String sendTemplateMessages(String templateID, String openId, OrderPO order, String header, String msg1,
			String msg2, String msg3, String msg4);

	public String sendCancOrderTemplateMessages(String templateID, String openId, OrderPO order, String header,
			String msg1, String msg2, String msg3, String msg4);

	public String sendCloseOrderTemplateMessages(String templateID, String openId, OrderPO order, String header,
			String msg1, String msg2, String msg3, String msg4);
}
