package com.larry.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.larry.service.OrderService;
import com.larry.utils.CaptchaUtils;

@WebServlet(urlPatterns = "/captcha.png")
public class CaptchaServlet extends HttpServlet{
	@Autowired
	private CaptchaUtils captchaUtils;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/png");
		String random = captchaUtils.random();
		captchaUtils.outputImage(random, response.getOutputStream());
		request.getSession().setAttribute("res", captchaUtils.getNum());
	}
}
