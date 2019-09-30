package com.larry.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class AccessToken {
	private String token;
	private int expireIn;
}
