package com.larry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.larry.mapper.UserMapper;
import com.larry.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	@Qualifier("jpaService")
	UserService jpaService;

	@RequestMapping("/createUser")
	public String createUser() {
		userService.createUser("Larry", 23);
		return "Success";
	}

	@ResponseBody
	@RequestMapping("/insert")
	public String createUserJPA(String name, Integer age) {
		jpaService.createUser(name, age);
		return "Create user success with JPA";
	}
	
	@ResponseBody
	@RequestMapping("/createUserMybatis")
	public String createUserMybatis(Integer id, String name, Integer age) {
		System.out.println(id+"->"+name+"->"+name+"->"+age);
		userMapper.createUser(name, age,id);
		return "Create user success with Mybatis";
	}
}
