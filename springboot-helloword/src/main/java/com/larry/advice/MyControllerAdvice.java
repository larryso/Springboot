package com.larry.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // 不指定包默认加了@Controller和@RestController都能控制
//@ControllerAdvice(basePackages ="com.example.demo.controller")
public class MyControllerAdvice {
	/**
	 * 全局异常处理，反正异常返回统一格式的map
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = RuntimeException.class)
	public Map<String, Object> exceptionHandler(Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1001);
		map.put("mag", ex.getMessage());
		// 发生异常进行日志记录，写入数据库或者其他处理，此处省略
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		return map;
	}
}
