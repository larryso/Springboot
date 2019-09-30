package com.larry.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.larry.exception.MyException;

@ControllerAdvice
public class MyExceptionHandler {
	@ResponseBody
	@ExceptionHandler(value = MyException.class)
	public Map<String, Object> exceptionHandler(Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1001);
		map.put("mag", ex.getMessage());
		return map;
	}
}
