package com.larry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.larry.dao.UserDao;
import com.larry.service.UserService;
@Service
@Primary
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void createUser(String name, Integer age) {
		// TODO Auto-generated method stub
		System.out.println("create user...");
		jdbcTemplate.update("insert into user values (?,?)", name, age);
	}
//	public void 

}
