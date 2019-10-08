package com.larry.service.impl;


import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.dao.UserDAO;
import com.larry.entity.UserPO;
import com.larry.service.AuthUserService;
@Service
public class AuthUserServiceImpl implements AuthUserService{
	@Autowired
	private UserDAO userDao;

	public UserPO save(UserPO user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	public UserPO getUser(String weixinOpendID) {
		// TODO Auto-generated method stub
		UserPO user = new UserPO();
		user.setWeixin_openId(weixinOpendID);
		List<UserPO> userList = (List<UserPO>) userDao.findAuthUserByWeixinOpenID(weixinOpendID);
		userList.sort(new Comparator<UserPO>() {

			public int compare(UserPO o1, UserPO o2) {
				// TODO Auto-generated method stub
				return o1.getId()-o2.getId();
			}
			
		});
		return userList.get(0);
	}

}
