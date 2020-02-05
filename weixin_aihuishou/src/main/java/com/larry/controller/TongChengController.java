package com.larry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.larry.bean.BuyerCart;
import com.larry.bean.BuyerItem;
import com.larry.bean.WeixinUser;
import com.larry.entity.UserPO;
import com.larry.exception.InvalidUserException;
import com.larry.service.AuthUserService;
import com.larry.service.WeixinUserService;

@Controller
@RequestMapping("/tongcheng")
public class TongChengController {
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private AuthUserService userService;

	@RequestMapping("/deliver_main")
	public String rec_main(HttpServletRequest request, HttpSession session, Model model) {

		String code = request.getParameter("code");
		WeixinUser weiXinUser = getWeixinUser(session, code);
		session.setAttribute("currentWeixinUser", weiXinUser);
		String weixinUserOpenId = weiXinUser.getOpenId();
		UserPO user = userService.getUser(weixinUserOpenId);
		if (user == null) {
			// throw new InvalidUserException("invalid user!!");
			// save user to db if user scan
			user = new UserPO();
			user.setWeixin_openId(weixinUserOpenId);
			user.setWeixin_nickname(weiXinUser.getNickname());
			user.setProvince(weiXinUser.getProvince());
			user.setCountry(weiXinUser.getCountry());
			// user.setCity(weixinUser.getCity());
			try {
				user = userService.save(user);
			} catch (Exception e) {
				user.setWeixin_openId(weixinUserOpenId);
				user.setWeixin_nickname("emoji");
				user.setProvince(weiXinUser.getProvince());
				user.setCountry(weiXinUser.getCountry());
				user = userService.save(user);
			}

		}
		session.setAttribute("authUser", user);
		return "deliver_home/deliver_main";
	}

	@RequestMapping("/getFromAddress")
	public String getFromAddress(HttpServletRequest request, HttpSession session, Model model) {
		return "deliver_home/from_address";
	}

	@RequestMapping("/getToAddress")
	public String getToAddress(HttpServletRequest request, HttpSession session, Model model) {
		String fromaddress = request.getParameter("address_details");
		String fromaddressStr = request.getParameter("address_str");
		String fromaddress_lat = request.getParameter("from_lan");
		String fromaddress_lng = request.getParameter("from_lng");
		String deliver_name = request.getParameter("deliver_name");
		String deliver_tel = request.getParameter("deliver_tel");
		System.out.println(fromaddress);
		System.out.println(fromaddressStr);
		System.out.println(fromaddress_lat);
		System.out.println(fromaddress_lng);
		System.out.println(deliver_name);
		System.out.println(deliver_tel);
		model.addAttribute("fromaddress", fromaddress);
		model.addAttribute("fromaddressStr", fromaddressStr);
		model.addAttribute("fromaddress_lat", fromaddress_lat);
		model.addAttribute("fromaddress_lng", fromaddress_lng);
		model.addAttribute("deliver_name", deliver_name);
		model.addAttribute("deliver_tel", deliver_tel);

		return "deliver_home/to_address";
	}

	@RequestMapping("/confirmFromAddress")
	public String confirmFromAddress(HttpServletRequest request, HttpSession session, Model model) {
		String fromAddress = request.getParameter("from_address_name");
		String fromAddress_lan = request.getParameter("from_address_lan");
		String fromAddress_lng = request.getParameter("from_address_lng");
		String detailAddress = request.getParameter("details_address");
		String deliverName = request.getParameter("deliver_name");
		String deliverTel = request.getParameter("deliver_tel");
		System.out.println(fromAddress);
		System.out.println(fromAddress_lan);
		System.out.println(detailAddress);
		System.out.println(deliverName);
		System.out.println(deliverTel);
		String fromAddressStr = fromAddress + "/";
		String addressStr = fromAddress + detailAddress;
		if (detailAddress != null && !("".equals(detailAddress))) {
			fromAddressStr = fromAddressStr + detailAddress + "/";
		}
		fromAddressStr = fromAddressStr + deliverName + "/" + deliverTel;

		model.addAttribute("fromAddress", fromAddressStr);
		model.addAttribute("address", addressStr);
		model.addAttribute("fromAddress_lan", fromAddress_lan);
		model.addAttribute("fromAddress_lng", fromAddress_lng);
		model.addAttribute("detailAddress", detailAddress);
		model.addAttribute("deliverName", deliverName);
		model.addAttribute("deliverTel", deliverTel);
		return "deliver_home/deliver_main_afterFrom";
	}

	@RequestMapping("/confirmToAddress")
	public String confirmToAddress(HttpServletRequest request, HttpSession session, Model model) {
		String toAddress = request.getParameter("to_address_name");
		String toAddress_lan = request.getParameter("to_address_lan");
		String toAddress_lng = request.getParameter("to_address_lng");
		String detailAddress = request.getParameter("details_address");
		String receiverName = request.getParameter("receiver_name");
		String receiverTel = request.getParameter("receiver_tel");

		String fromAddress = request.getParameter("from_address_name");
		String fromAddressStr = request.getParameter("from_address_str");
		String fromAddressLan = request.getParameter("from_address_lan");
		String fromAddressLng = request.getParameter("from_address_lng");
		String deliverName = request.getParameter("deliver_name");
		String deliverTel = request.getParameter("deliver_tel");

		System.out.println(toAddress);
		System.out.println(toAddress_lan);
		System.out.println(detailAddress);
		System.out.println(receiverName);
		System.out.println(receiverTel);
		String toAddressStr = toAddress + "/";
		if (detailAddress != null && !("".equals(detailAddress))) {
			toAddressStr = toAddressStr + detailAddress + "/";
		}
		toAddressStr = toAddressStr + receiverName + "/" + receiverTel;

		model.addAttribute("toAddressStr", toAddressStr);
		model.addAttribute("toAddress_lan", toAddress_lan);
		model.addAttribute("toAddress_lng", toAddress_lng);
		model.addAttribute("detailAddress", detailAddress);
		model.addAttribute("receiverName", receiverName);
		model.addAttribute("receiverTel", receiverTel);

		model.addAttribute("fromAddress", fromAddress);
		model.addAttribute("fromAddressStr", fromAddressStr);
		model.addAttribute("fromAddressLan", fromAddressLan);
		model.addAttribute("fromAddressLng", fromAddressLng);
		model.addAttribute("deliverName", deliverName);
		model.addAttribute("deliverTel", deliverTel);
		return "deliver_home/deliver_main_afterTo";
	}

	@ResponseBody
	@RequestMapping("/calc_deliver_fee")
	public Map<String, Object> calcdeliverFeeFromAjax(@RequestBody Map<String, Object> params, HttpSession session) {
		Double PI = Math.PI;

		Double PK = 180 / PI;

		String from_lan_s = (String) params.get("from_lan");
		String from_lng_s = (String) params.get("fron_lng");
		String to_lan_s = (String) params.get("to_lan");
		String to_lng_s = (String) params.get("to_lng");
		System.out.println(from_lan_s);
		System.out.println(from_lng_s);
		System.out.println(to_lan_s);
		System.out.println(to_lng_s);
		
		double from_lan = new Double(from_lan_s);
		double from_lng=new Double(from_lng_s);
		double to_lan = new Double(to_lan_s);
		double to_lng = new Double(to_lng_s);
		int totalPrice = 0;

		double t1 = Math.cos(from_lan / PK) * Math.cos(from_lng / PK) * Math.cos(to_lan / PK) * Math.cos(to_lng / PK);
		double t2 = Math.cos(from_lan / PK) * Math.sin(from_lng / PK) * Math.cos(to_lan / PK) * Math.sin(to_lng / PK);
		double t3 = Math.sin(from_lan / PK) * Math.sin(to_lan / PK);

		double tt = Math.acos(t1 + t2 + t3);
		String res = (6366000 * tt) + "";
		int dis= new Integer(res.substring(0, res.indexOf("."))).intValue()/1000;
		Map<String, Object> result = new HashMap<String, Object>();
		totalPrice = 3*dis;
		result.put("statusCode", "200");
		result.put("dis", dis);
		result.put("total_price", totalPrice);
		return result;
	}

	private WeixinUser getWeixinUser(HttpSession session, String code) {
		Map<String, String> authInfo = new HashMap<String, String>();
		String openId = "";
		if (code != null) {
			authInfo = weixinUserService.getAuthInfo(code);
			openId = authInfo.get("Openid");
		}
		String accessToken = weixinUserService.getAccessToken().getToken();
		WeixinUser userinfo = weixinUserService.getUserInfo(accessToken, openId);
		return userinfo;
	}

}
