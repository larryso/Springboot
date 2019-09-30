package com.larry.bean;

import lombok.Data;

@Data
public class TextMessage extends BaseMessage {
	private String Content;

	private String MsgId;
}
