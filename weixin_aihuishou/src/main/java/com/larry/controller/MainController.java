package com.larry.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.larry.AppConf;
import com.larry.bean.WeixinUser;
import com.larry.cache.EhCacheUtil;
import com.larry.consts.OrderConst;
import com.larry.entity.MyScore;
import com.larry.entity.OrderPO;
import com.larry.entity.ProductPO;
import com.larry.entity.UserAddress;
import com.larry.entity.UserPO;
import com.larry.entity.shop.ProductCategory;
import com.larry.entity.shop.ShopProduct;
import com.larry.exception.InvalidUserException;
import com.larry.service.AuthUserService;
import com.larry.service.OrderService;
import com.larry.service.WeixinUserService;

@Controller
public class MainController {

	private final static Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private AuthUserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AppConf appConf;

	@RequestMapping("/index")
	public String indexPage(HttpServletRequest request, HttpSession session, Model model) {
		String maintenance_flag = appConf.getMaintenance_flag();
		boolean maintenance = new Boolean(maintenance_flag);
		if(maintenance) {
			return "maintenance_mode";
		}else {
			String code = request.getParameter("code");
			logger.info("###################get code from Weixin###########" + code);
			WeixinUser weiXinUser = getWeixinUser(session, code);
			session.setAttribute("currentWeixinUser", weiXinUser);
			String weixinUserOpenId = weiXinUser.getOpenId();
			logger.info("weixin user openID:" + weixinUserOpenId);
			UserPO user = userService.getUser(weixinUserOpenId);
			if (user == null) {
				//throw new InvalidUserException("invalid user!!");
				//save user to db if user scan
				user = new UserPO();
				user.setWeixin_openId(weixinUserOpenId);
				user.setWeixin_nickname(weiXinUser.getNickname());
				user.setProvince(weiXinUser.getProvince());
				user.setCountry(weiXinUser.getCountry());
				//user.setCity(weixinUser.getCity());
				try {
					user = userService.save(user);
				}catch(Exception e) {
					user.setWeixin_openId(weixinUserOpenId);
					user.setWeixin_nickname("emoji");
					user.setProvince(weiXinUser.getProvince());
					user.setCountry(weiXinUser.getCountry());
					user = userService.save(user);
				}
				
			}
			session.setAttribute("authUser", user);
			return "recycle_home/index";
			/*
			String weixinUserOpenId="";
			WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
			if (weiXinUser == null) {
				String code = request.getParameter("code");
				logger.info("###################get code from Weixin###########" + code);
				weiXinUser = getWeixinUser(session, code);
				session.setAttribute("currentWeixinUser", weiXinUser);
			} else {

			}
			weixinUserOpenId = weiXinUser.getOpenId();
			logger.info("weixin user openID:" + weixinUserOpenId);
			session.setAttribute("currentWeixinUser", weiXinUser);
			// 首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
			if (session.getAttribute("authUser") == null) {
				UserPO user = userService.getUser(weixinUserOpenId);
				if (user == null) {
					//throw new InvalidUserException("invalid user!!");
					//save user to db if user scan
					user = new UserPO();
					user.setWeixin_openId(weixinUserOpenId);
					user.setWeixin_nickname(weiXinUser.getNickname());
					user.setProvince(weiXinUser.getProvince());
					user.setCountry(weiXinUser.getCountry());
					//user.setCity(weixinUser.getCity());
					user = userService.save(user);
				}
				session.setAttribute("authUser", user);
			}
			return "recycle_home/index";
			*/

		}
		
	}
	@RequestMapping("/recMain")
	public String recMain(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		if(user == null || weiXinUser == null) {
			throw new InvalidUserException("Invalide access");
		}
		logger.info("###################[/recMain] weixin user openID###########" + user.getWeixin_openId());
		return "recycle_home/index";
	}
	@RequestMapping("/point_shop")
	public String pointShopPage(HttpServletRequest request, HttpSession session, Model model) {
		boolean maintenance = true;
		if(maintenance) {
			return "maintenance_mode";
		}else {
			List<ProductCategory> category = (List<ProductCategory>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD_CAT);
			System.out.println(category.size());
			model.addAttribute("categoryList", category);
			List<ShopProduct> allProds = (List<ShopProduct>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD);
			List<ShopProduct> starProds = new ArrayList<ShopProduct>();
			for (ShopProduct p : allProds) {
				if (p.getStar_flag() == 1) {
					starProds.add(p);
				}
			}
			model.addAttribute("star_products", starProds);
			return "shop_index";
		}
		
	}

	@RequestMapping("/my_order")
	public String orderCenter(HttpServletRequest request, HttpSession session, Model model) {
		
		UserPO user = (UserPO) session.getAttribute("authUser");
		logger.info("###################[/my_order] weixin user openID###########" + user.getWeixin_openId());
		String infoMsg = request.getParameter("info_msg");
		List<OrderPO> orderList = orderService.getAllOrdersByUserID(user.getId());
		List<OrderPO> newOrCancledOrders = new ArrayList<OrderPO>();
		List<OrderPO> processingOrders = new ArrayList<OrderPO>();
		List<OrderPO> closedOrders = new ArrayList<OrderPO>();
		for (OrderPO order : orderList) {
			int status = order.getStatus();
			// if (OrderConst.NEW_ORDER == status || OrderConst.CANCLED_ORDER == status) {
			newOrCancledOrders.add(order);
			// } else if (OrderConst.PROCESSING_ORDER == status) {
			if (OrderConst.PROCESSING_ORDER == status) {
				processingOrders.add(order);
			} else if (OrderConst.CLOSED_ORDER == status) {
				closedOrders.add(order);
			}
		}

		if (newOrCancledOrders.size() > 0) {
			newOrCancledOrders.sort(new Comparator<OrderPO>() {

				public int compare(OrderPO o1, OrderPO o2) {
					return o2.getId() - o1.getId();
				}

			});
		}
		if (processingOrders.size() > 0) {
			processingOrders.sort(new Comparator<OrderPO>() {

				public int compare(OrderPO o1, OrderPO o2) {
					return o2.getId() - o1.getId();
				}

			});
		}
		if (closedOrders.size() > 0) {
			closedOrders.sort(new Comparator<OrderPO>() {

				public int compare(OrderPO o1, OrderPO o2) {
					return o2.getId() - o1.getId();
				}

			});
		}
		model.addAttribute("newOrCancledOrders", newOrCancledOrders);
		model.addAttribute("processingOrders", processingOrders);
		model.addAttribute("closedOrders", closedOrders);
		model.addAttribute("info_msg", infoMsg);
		return "recycle_home/my_orders";

	}

	@RequestMapping("/my_point")
	public String PointCenter(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		logger.info("###################[/my_point] weixin user openID###########" + user.getWeixin_openId());
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());
		return "recycle_home/my_score";
	}

	@RequestMapping("/my_score_manage")
	public String scoreManage(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		logger.info("###################[/my_score_manage] weixin user openID###########" + user.getWeixin_openId());
		Integer payableScore = userService.getPayableScore(user.getId());

		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());
		model.addAttribute("payableScore", payableScore);
		List<MyScore> allPoints = userService.getScoresByUserID(user.getId());
		List<MyScore> displayableScore = new ArrayList<MyScore>();
		for (MyScore s : allPoints) {
			if (s.getScore() > 0) {
				displayableScore.add(s);
			}
		}
		displayableScore.sort(new Comparator<MyScore>() {

			public int compare(MyScore o1, MyScore o2) {
				// TODO Auto-generated method stub
				return o2.getScore_id() - o1.getScore_id();
			}

		});
		model.addAttribute("displayableScore", displayableScore);
		// return "point_manage";
		return "recycle_home/my_score_manage";
	}

	@RequestMapping("/paper")
	public String paperReconciliate(HttpServletRequest request, HttpSession session, Model model) {
		List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
		List<ProductPO> paperProduct = new ArrayList<ProductPO>();
		for (ProductPO p : productList) {
			if (1 == p.getProductCat().getId()) {
				paperProduct.add(p);
			}
		}
		String price_scale = ((ProductPO) paperProduct.get(0)).getProductCat().getPrice();
		model.addAttribute("paperProducts", paperProduct);
		model.addAttribute("price_scale", price_scale);
		List<UserAddress> useraddressList = userService.getAddressByUser((UserPO) session.getAttribute("authUser"));
		useraddressList.sort(new Comparator<UserAddress>() {

			public int compare(UserAddress o1, UserAddress o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}

		});
		if (useraddressList != null) {
			if (useraddressList.size() > 0) {
				boolean setDefaultAddress = false;
				// get defult address
				for (UserAddress addr : useraddressList) {
					if (addr.getSet_defult() == 1) {
						setDefaultAddress = true;
						model.addAttribute("address", addr);
						model.addAttribute("simpleAddressId", addr.getId());

					}
				}
				if (!setDefaultAddress) {
					model.addAttribute("address", useraddressList.get(0));
					model.addAttribute("simpleAddressId", useraddressList.get(0).getId());

				}
//				UserAddress useraddress = useraddressList.get(0);
//				model.addAttribute("tel", useraddress.getTel());
//				model.addAttribute("displayAddress", useraddress.getAddress()+"/"+useraddress.getTel()+"/"+useraddress.getName());
//				model.addAttribute("detailedAddress", useraddress.getAddress());
			} else {
				model.addAttribute("address", null);
			}
		} else {
			model.addAttribute("address", null);
		}

		return "recycle_home/paper_rec";
	}

	@RequestMapping("/contact_info_page")
	public String contactInfoPage(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		String addressId = request.getParameter("addressId");
		String from_url = request.getParameter("from_url");
		if (addressId != null && addressId != "") {
			model.addAttribute("addressId", addressId);
		}
		List<UserAddress> allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", allAddress);
		model.addAttribute("from_url", from_url);
		return "recycle_home/default_contact";
	}

	@RequestMapping(value = "/contactSubmit", method = RequestMethod.POST)
	public String contactSubmit(HttpServletRequest request, HttpSession session, Model model) {
		String fromUrl = request.getParameter("from_url");

		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		String address = request.getParameter("city") + request.getParameter("detail_address");
		String tel = request.getParameter("tel");
		String name = request.getParameter("name");
		UserAddress useraddress = new UserAddress();
		useraddress.setAddress(address);
		useraddress.setName(name);
		useraddress.setTel(tel);
		useraddress.setUser(user);
		String defaultFlag = request.getParameter("default_address");
		if ("true".equals(defaultFlag)) {
			useraddress.setSet_defult(1);
		} else {
			useraddress.setSet_defult(0);
		}
		useraddress = userService.save(useraddress);
		List<UserAddress> allAddress = userService.getAddressByUser(user);
		for (UserAddress addr : allAddress) {
			if (addr.getId() != useraddress.getId()) {
				//addr.setSet_defult(0);
				//userService.save(addr);
				if(addr.getSet_defult()==1) {
					addr.setSet_defult(0);
					userService.save(addr);
				}
			}
		}
		List<UserAddress> _allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", _allAddress);
		model.addAttribute("addressId", useraddress.getId());
		String from_url = request.getParameter("from_url");
		model.addAttribute("from_url", from_url);
		if (fromUrl != null && "address_manage".equals(fromUrl)) {
			return "recycle_home/address_manage";
		}

		return "recycle_home/default_contact";
	}

	@RequestMapping(value = "addNewAddress")
	public String addNewAddress(HttpServletRequest request, HttpSession session, Model model) {
		String from_url = request.getParameter("from_url");
		model.addAttribute("from_url", from_url);

		return "recycle_home/add_new_address";
	}

	@RequestMapping(value = "addressManage")
	public String addressManage(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		List<UserAddress> _allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", _allAddress);
		return "recycle_home/address_manage";
	}

	@RequestMapping(value = "addressDetails")
	public String addressDetails(HttpServletRequest request, HttpSession session, Model model) {
		String addressId = request.getParameter("address_id");
		UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());
		model.addAttribute("useraddress", useraddress);
		return "address_details";
	}

	@RequestMapping(value = "addressUpdate", method = RequestMethod.POST)
	public String addressUpdate(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		String addressId = request.getParameter("address_id");

		UserAddress userAddress = userService.getAddressById(new Integer(addressId).intValue());

		int defaultFlag = userAddress.getSet_defult();
		int tempValue = 0;
		String page_defaultFlag = request.getParameter("default_address");
		if ("true".equals(page_defaultFlag)) {
			tempValue = 1;
		} else {
			tempValue = 0;
		}
		if (defaultFlag != tempValue) {
			userAddress.setSet_defult(tempValue);
		} else {
			List<UserAddress> _allAddress = userService.getAddressByUser(user);
			model.addAttribute("allAddress", _allAddress);
			return "recycle_home/address_manage";
		}
		userService.save(userAddress);
		if(defaultFlag == 0 && tempValue == 1) {
			List<UserAddress> allAddress = userService.getAddressByUser(user);
			for (UserAddress addr : allAddress) {
				if (addr.getId() != userAddress.getId()) {
					//addr.setSet_defult(0);
					//userService.save(addr);
					if(addr.getSet_defult()==1) {
						addr.setSet_defult(0);
						userService.save(addr);
					}
				}
			}
		}

		
		List<UserAddress> _allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", _allAddress);
		model.addAttribute("INFO_MSG", "default_set");
		return "recycle_home/address_manage";
	}

	@RequestMapping(value = "afterSelectedAddr")
	public String afterSelectedAddr(HttpServletRequest request, HttpSession session, Model model) {
		String addressId = request.getParameter("selected_address");
		UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());

		String from_url = request.getParameter("from_url");
		if ("paper_rec".equals(from_url)) {
			List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
			List<ProductPO> paperProduct = new ArrayList<ProductPO>();
			for (ProductPO p : productList) {
				if (1 == p.getProductCat().getId()) {
					paperProduct.add(p);
				}
			}
			String price_scale = ((ProductPO) paperProduct.get(0)).getProductCat().getPrice();
			model.addAttribute("paperProducts", paperProduct);
			model.addAttribute("price_scale", price_scale);
			model.addAttribute("address", useraddress);
			model.addAttribute("simpleAddressId", useraddress.getId());
			return "recycle_home/paper_rec";
		}else if("plastic_rec".equals(from_url)) {
			List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
			List<ProductPO> plasticProduct = new ArrayList<ProductPO>();
			for (ProductPO p : productList) {
				if (2 == p.getProductCat().getId()) {
					plasticProduct.add(p);
				}
			}
			String price_scale = ((ProductPO) plasticProduct.get(0)).getProductCat().getPrice();
			model.addAttribute("plasticProduct", plasticProduct);
			model.addAttribute("price_scale", price_scale);
			model.addAttribute("address", useraddress);
			model.addAttribute("simpleAddressId", useraddress.getId());
			return "recycle_home/plastic_rec";
		}else if("metal_rec".equals(from_url)) {
			List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
			List<ProductPO> metalProduct = new ArrayList<ProductPO>();
			for (ProductPO p : productList) {
				if (3 == p.getProductCat().getId()) {
					metalProduct.add(p);
				}
			}
			String price_scale = ((ProductPO) metalProduct.get(0)).getProductCat().getPrice();
			model.addAttribute("metalProduct", metalProduct);
			model.addAttribute("price_scale", price_scale);
			model.addAttribute("address", useraddress);
			model.addAttribute("simpleAddressId", useraddress.getId());
			return "recycle_home/metal_rec";
		}else if("phone_rec".equals(from_url)) {
			List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
			List<ProductPO> phoneProduct = new ArrayList<ProductPO>();
			for (ProductPO p : productList) {
				if (4 == p.getProductCat().getId()) {
					phoneProduct.add(p);
				}
			}
			String price_scale = ((ProductPO) phoneProduct.get(0)).getProductCat().getPrice();
			model.addAttribute("phoneProduct", phoneProduct);
			model.addAttribute("price_scale", price_scale);
			model.addAttribute("address", useraddress);
			model.addAttribute("simpleAddressId", useraddress.getId());
			return "recycle_home/phone_rec";
		}else if("elect_rec".equals(from_url)) {
			List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
			List<ProductPO> electProduct = new ArrayList<ProductPO>();
			for (ProductPO p : productList) {
				if (5 == p.getProductCat().getId()) {
					electProduct.add(p);
				}
			}
			String price_scale = ((ProductPO) electProduct.get(0)).getProductCat().getPrice();
			model.addAttribute("electProduct", electProduct);
			model.addAttribute("price_scale", price_scale);
			model.addAttribute("address", useraddress);
			model.addAttribute("simpleAddressId", useraddress.getId());
			return "recycle_home/elect_rec";
		}
		else {
			return "";
		}

	}

	@RequestMapping(value = "selectedAddrFromManage")
	public String selectedAddrFromManage(HttpServletRequest request, HttpSession session, Model model) {
		String addressId = request.getParameter("selected_address");
		UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());

		model.addAttribute("address", useraddress);

		return "recycle_home/address_details";

	}

	@RequestMapping("/plastic")
	public String plasticRec(HttpServletRequest request, HttpSession session, Model model) {
		List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
		List<ProductPO> plasticProduct = new ArrayList<ProductPO>();
		for (ProductPO p : productList) {
			if (2 == p.getProductCat().getId()) {
				plasticProduct.add(p);
			}
		}
		String price_scale = ((ProductPO) plasticProduct.get(0)).getProductCat().getPrice();
		model.addAttribute("plasticProduct", plasticProduct);
		model.addAttribute("price_scale", price_scale);
		List<UserAddress> useraddressList = userService.getAddressByUser((UserPO) session.getAttribute("authUser"));
		useraddressList.sort(new Comparator<UserAddress>() {

			public int compare(UserAddress o1, UserAddress o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}

		});
		if (useraddressList != null) {
			if (useraddressList.size() > 0) {
				boolean setDefaultAddress = false;
				// get defult address
				for (UserAddress addr : useraddressList) {
					if (addr.getSet_defult() == 1) {
						setDefaultAddress = true;
						model.addAttribute("address", addr);
						model.addAttribute("simpleAddressId", addr.getId());

					}
				}
				if (!setDefaultAddress) {
					model.addAttribute("address", useraddressList.get(0));
					model.addAttribute("simpleAddressId", useraddressList.get(0).getId());

				}
//				UserAddress useraddress = useraddressList.get(0);
//				model.addAttribute("tel", useraddress.getTel());
//				model.addAttribute("displayAddress", useraddress.getAddress()+"/"+useraddress.getTel()+"/"+useraddress.getName());
//				model.addAttribute("detailedAddress", useraddress.getAddress());
			} else {
				model.addAttribute("address", null);
			}
		} else {
			model.addAttribute("address", null);
		}

		return "recycle_home/plastic_rec";
	}

	@RequestMapping("/metal")
	public String metalRec(HttpServletRequest request, HttpSession session, Model model) {
		List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
		List<ProductPO> metalProduct = new ArrayList<ProductPO>();
		for (ProductPO p : productList) {
			if (3 == p.getProductCat().getId()) {
				metalProduct.add(p);
			}
		}
		String price_scale = ((ProductPO) metalProduct.get(0)).getProductCat().getPrice();
		model.addAttribute("metalProduct", metalProduct);
		model.addAttribute("price_scale", price_scale);
		List<UserAddress> useraddressList = userService.getAddressByUser((UserPO) session.getAttribute("authUser"));
		useraddressList.sort(new Comparator<UserAddress>() {

			public int compare(UserAddress o1, UserAddress o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}

		});
		if (useraddressList != null) {
			if (useraddressList.size() > 0) {
				boolean setDefaultAddress = false;
				// get defult address
				for (UserAddress addr : useraddressList) {
					if (addr.getSet_defult() == 1) {
						setDefaultAddress = true;
						model.addAttribute("address", addr);
						model.addAttribute("simpleAddressId", addr.getId());

					}
				}
				if (!setDefaultAddress) {
					model.addAttribute("address", useraddressList.get(0));
					model.addAttribute("simpleAddressId", useraddressList.get(0).getId());

				}
//				UserAddress useraddress = useraddressList.get(0);
//				model.addAttribute("tel", useraddress.getTel());
//				model.addAttribute("displayAddress", useraddress.getAddress()+"/"+useraddress.getTel()+"/"+useraddress.getName());
//				model.addAttribute("detailedAddress", useraddress.getAddress());
			} else {
				model.addAttribute("address", null);
			}
		} else {
			model.addAttribute("address", null);
		}

		return "recycle_home/metal_rec";
	}

	@RequestMapping("/phone")
	public String phoneRec(HttpServletRequest request, HttpSession session, Model model) {
		List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
		List<ProductPO> phoneProduct = new ArrayList<ProductPO>();
		for (ProductPO p : productList) {
			if (4 == p.getProductCat().getId()) {
				phoneProduct.add(p);
			}
		}
		String price_scale = ((ProductPO) phoneProduct.get(0)).getProductCat().getPrice();
		model.addAttribute("phoneProduct", phoneProduct);
		model.addAttribute("price_scale", price_scale);
		List<UserAddress> useraddressList = userService.getAddressByUser((UserPO) session.getAttribute("authUser"));
		useraddressList.sort(new Comparator<UserAddress>() {

			public int compare(UserAddress o1, UserAddress o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}

		});
		if (useraddressList != null) {
			if (useraddressList.size() > 0) {
				boolean setDefaultAddress = false;
				// get defult address
				for (UserAddress addr : useraddressList) {
					if (addr.getSet_defult() == 1) {
						setDefaultAddress = true;
						model.addAttribute("address", addr);
						model.addAttribute("simpleAddressId", addr.getId());

					}
				}
				if (!setDefaultAddress) {
					model.addAttribute("address", useraddressList.get(0));
					model.addAttribute("simpleAddressId", useraddressList.get(0).getId());

				}
//				UserAddress useraddress = useraddressList.get(0);
//				model.addAttribute("tel", useraddress.getTel());
//				model.addAttribute("displayAddress", useraddress.getAddress()+"/"+useraddress.getTel()+"/"+useraddress.getName());
//				model.addAttribute("detailedAddress", useraddress.getAddress());
			} else {
				model.addAttribute("address", null);
			}
		} else {
			model.addAttribute("address", null);
		}

		return "recycle_home/phone_rec";
	}

	@RequestMapping("/elect")
	public String electRec(HttpServletRequest request, HttpSession session, Model model) {
		List<ProductPO> productList = (List<ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_PROD);
		List<ProductPO> electProduct = new ArrayList<ProductPO>();
		for (ProductPO p : productList) {
			if (5 == p.getProductCat().getId()) {
				electProduct.add(p);
			}
		}
		String price_scale = ((ProductPO) electProduct.get(0)).getProductCat().getPrice();
		model.addAttribute("electProduct", electProduct);
		model.addAttribute("price_scale", price_scale);
		List<UserAddress> useraddressList = userService.getAddressByUser((UserPO) session.getAttribute("authUser"));
		useraddressList.sort(new Comparator<UserAddress>() {

			public int compare(UserAddress o1, UserAddress o2) {
				// TODO Auto-generated method stub
				return o2.getId() - o1.getId();
			}

		});
		if (useraddressList != null) {
			if (useraddressList.size() > 0) {
				boolean setDefaultAddress = false;
				// get defult address
				for (UserAddress addr : useraddressList) {
					if (addr.getSet_defult() == 1) {
						setDefaultAddress = true;
						model.addAttribute("address", addr);
						model.addAttribute("simpleAddressId", addr.getId());

					}
				}
				if (!setDefaultAddress) {
					model.addAttribute("address", useraddressList.get(0));
					model.addAttribute("simpleAddressId", useraddressList.get(0).getId());

				}
//				UserAddress useraddress = useraddressList.get(0);
//				model.addAttribute("tel", useraddress.getTel());
//				model.addAttribute("displayAddress", useraddress.getAddress()+"/"+useraddress.getTel()+"/"+useraddress.getName());
//				model.addAttribute("detailedAddress", useraddress.getAddress());
			} else {
				model.addAttribute("address", null);
			}
		} else {
			model.addAttribute("address", null);
		}

		return "recycle_home/elect_rec";
	}

	@RequestMapping("/login")
	public String ManagerLogin(HttpServletRequest request, HttpSession session, Model model) {
		return "recycle_home/login";
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
