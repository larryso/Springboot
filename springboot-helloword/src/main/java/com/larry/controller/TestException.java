package com.larry.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class TestException {
	@RequestMapping("/{id}")
	public String testException(@PathVariable Integer id) {
		return 1/id +"";
	}
}
