package com.larry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeixinController {
	@RequestMapping({"MP_verify_zWodGBxdFkCe1k7H.txt"})
	@ResponseBody
	public String indexPage(HttpServletRequest request, HttpSession session, Model model) {
		 return "zWodGBxdFkCe1k7H";
	}
}
