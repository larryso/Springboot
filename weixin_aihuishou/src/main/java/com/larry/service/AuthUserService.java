package com.larry.service;

import com.larry.entity.UserPO;

public interface AuthUserService {
	public UserPO save(UserPO user);
	public UserPO getUser(String weixinOpendID);

}
