package com.larry.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GloableExceptionHandler {
	@ExceptionHandler(value= {java.lang.Exception.class})
	public ModelAndView handleSessionTimeOut(Exception e) {
		e.printStackTrace();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("404");
		return mv;
	}
}
