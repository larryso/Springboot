package com.larry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.dao.UserDao;
import com.larry.entity.User;
import com.larry.service.UserService;

@Service("jpaService")
public class UserServiceImpl2 implements UserService{
	@Autowired
	private UserDao dao;

	public void createUser(String name, Integer age) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		dao.save(user);
		
	}

}
