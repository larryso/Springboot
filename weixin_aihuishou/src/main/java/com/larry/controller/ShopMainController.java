package com.larry.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.larry.bean.BuyerCart;
import com.larry.bean.BuyerItem;
import com.larry.bean.WeixinUser;
import com.larry.cache.EhCacheUtil;
import com.larry.entity.MyScore;
import com.larry.entity.ProductPO;
import com.larry.entity.UserAddress;
import com.larry.entity.UserPO;
import com.larry.entity.shop.ProductCategory;
import com.larry.entity.shop.ShopOrder;
import com.larry.entity.shop.ShopOrderItem;
import com.larry.entity.shop.ShopProduct;
import com.larry.exception.InvalidUserException;
import com.larry.service.AuthUserService;
import com.larry.service.FormNoGenerateService;
import com.larry.service.WeixinUserService;
import com.larry.utils.FormNumTypeEnum;
import com.larry.utils.RedisUtil;

@Controller
public class ShopMainController {
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private AuthUserService userService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private FormNoGenerateService formNoGenerator;
	private final static Logger logger = LoggerFactory.getLogger(ShopMainController.class);
	@RequestMapping("/shop_main")
	public String shopMain(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		if (weiXinUser == null) {
			String code = request.getParameter("code");
			logger.info("###################get code from Weixin###########" + code);
			weiXinUser = getWeixinUser(session, code);
			session.setAttribute("currentWeixinUser", weiXinUser);
		} else {
			if (weiXinUser.getOpenId() == null) {
				String code = request.getParameter("code");
				logger.info("###################get code from Weixin###########" + code);
				weiXinUser = getWeixinUser(session, code);
				session.setAttribute("currentWeixinUser", weiXinUser);
			}
		}
		logger.info("weixin user openID:" + weiXinUser.getOpenId());
		session.setAttribute("currentWeixinUser", weiXinUser);
		// 首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
		if (session.getAttribute("authUser") == null) {
			UserPO user = userService.getUser(weiXinUser.getOpenId());
			if (user == null) {
				//throw new InvalidUserException("invalid user!!");
				//save user to db if user scan
				user = new UserPO();
				user.setWeixin_openId(weiXinUser.getOpenId());
				user.setWeixin_nickname(weiXinUser.getNickname());
				user.setProvince(weiXinUser.getProvince());
				user.setCountry(weiXinUser.getCountry());
				//user.setCity(weixinUser.getCity());
				user = userService.save(user);
			}
			session.setAttribute("authUser", user);
		}
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());
		List<ProductCategory> category = (List<ProductCategory>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD_CAT);
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

	@RequestMapping("/search_products")
	public String searchProducts(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");

		return "New_address2";
	}

	@RequestMapping("/category")
	public String category(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());

		List<ProductCategory> category = (List<ProductCategory>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD_CAT);
		List<ShopProduct> allProds = (List<ShopProduct>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD);
		model.addAttribute("categoryList", category);
		model.addAttribute("allProds", allProds);
		return "AssorTment";
	}

	@RequestMapping("/Shopping_Cart")
	public String shoppingCart(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
		if (cart != null && cart.getItems().size() > 0) {
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());
			model.addAttribute("display_cart", true);
		} else {
			model.addAttribute("display_cart", false);
		}
		return "Shopping_Cart";
	}

	@RequestMapping("/fill_order")
	public String fillOrder(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
		if (cart != null) {
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());

			List<UserAddress> allAddress = userService.getAddressByUser(user);
			if (allAddress.size() > 0) {
				model.addAttribute("hasAddress", true);
				model.addAttribute("userAddress", allAddress.get(0));
			} else {
				model.addAttribute("hasAddress", false);
			}

		}
		return "Fill_order";
	}

	@RequestMapping("/submit_payment")
	@Transactional(rollbackOn = Exception.class)
	public String submitPayment(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		// check score payable
		Integer payableScore = userService.getPayableScore(user.getId());
		BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
		List<ShopProduct> allProds = (List<ShopProduct>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD);
		List<ShopProduct> starProds = new ArrayList<ShopProduct>();
		for (ShopProduct p : allProds) {
			if (p.getStar_flag() == 1) {
				starProds.add(p);
			}
		}
		model.addAttribute("star_products", starProds);
		if (cart.getTotalPrice() > payableScore) {
			model.addAttribute("failed_msg", "FAILED PAY");
			model.addAttribute("payableScore", payableScore);
			return "Payment_fail";
		} else {
			MyScore score = new MyScore();
			score.setCreate_time(new java.sql.Date(System.currentTimeMillis()));
			score.setScore(cart.getTotalPrice());
			score.setScore_type(1);
			score.setUser_id(user.getId());
			userService.updateScore(score);
			redisUtil.del(user.getId().toString());
			// save order
			ShopOrder shopOrder = new ShopOrder();
			String addressId = request.getParameter("address_id");
			if (addressId != null) {
				shopOrder.setAddress_id(new Integer(addressId));
			}
			shopOrder.setCreated_date(new java.sql.Date(System.currentTimeMillis()));
			String orderNum = formNoGenerator.generateFormNo(FormNumTypeEnum.FK_ORDER);
			shopOrder.setOrder_number(orderNum);
			shopOrder.setStatus(0);
			shopOrder.setTotal_price_score(score.getScore());
			shopOrder.setUser_id(user.getId());
			List<BuyerItem> items = cart.getItems();
			Set<ShopOrderItem> orderItems = new HashSet<ShopOrderItem>();
			for (BuyerItem i : items) {
				ShopOrderItem orderItem = new ShopOrderItem();
				orderItem.setDescription(0);
				orderItem.setNumber(i.getAmmount());
				orderItem.setOrder(shopOrder);
				orderItem.setPrice_score(new Integer(i.getProduct().getPoint_price()));
				orderItem.setProduct(i.getProduct());
				orderItems.add(orderItem);
			}
			shopOrder.setOrderItems(orderItems);
			userService.saveShopOrder(shopOrder);
			;
			// shopOrder.setAddress_id(user.);
			model.addAttribute("total_price", cart.getTotalPrice());
			model.addAttribute("order_id", shopOrder.getId());
			return "Payment_success";
		}

	}

	@RequestMapping("/submit_payment2")
	@Transactional(rollbackOn = Exception.class)
	public String submitPayment2(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		// check score payable
		Integer payableScore = userService.getPayableScore(user.getId());
		BuyerCart cart = (BuyerCart) session.getAttribute("temp_cart_" + user.getId().toString());
		if (cart.getTotalPrice() > payableScore) {
			model.addAttribute("failed_msg", "FAILED PAY");
			return "Payment_fail";
		} else {
			MyScore score = new MyScore();
			score.setCreate_time(new java.sql.Date(System.currentTimeMillis()));
			score.setScore(cart.getTotalPrice());
			score.setScore_type(1);
			score.setUser_id(user.getId());
			userService.updateScore(score);
			session.removeAttribute("temp_cart_" + user.getId().toString());
			// save order
			ShopOrder shopOrder = new ShopOrder();
			String addressId = request.getParameter("address_id");
			if (addressId != null) {
				shopOrder.setAddress_id(new Integer(addressId));
			}
			shopOrder.setCreated_date(new java.sql.Date(System.currentTimeMillis()));
			String orderNum = formNoGenerator.generateFormNo(FormNumTypeEnum.FK_ORDER);
			System.out.println("$$$$$$$$$$$$$$=>" + orderNum);
			shopOrder.setOrder_number(orderNum);
			shopOrder.setStatus(0);
			shopOrder.setTotal_price_score(score.getScore());
			shopOrder.setUser_id(user.getId());
			List<BuyerItem> items = cart.getItems();
			Set<ShopOrderItem> orderItems = new HashSet<ShopOrderItem>();
			for (BuyerItem i : items) {
				ShopOrderItem orderItem = new ShopOrderItem();
				orderItem.setDescription(0);
				orderItem.setNumber(i.getAmmount());
				orderItem.setOrder(shopOrder);
				orderItem.setPrice_score(new Integer(i.getProduct().getPoint_price()));
				orderItem.setProduct(i.getProduct());
				orderItems.add(orderItem);
			}
			shopOrder.setOrderItems(orderItems);
			userService.saveShopOrder(shopOrder);
			;
			// shopOrder.setAddress_id(user.);
			model.addAttribute("total_price", cart.getTotalPrice());
			return "Payment_success";
		}

	}

	@RequestMapping("/receipt_info")
	public String receiptInfo(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		List<UserAddress> allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", allAddress);
		return "Receipt_info";
	}

	@RequestMapping("/receipt_info2")
	public String receiptInfo2(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		List<UserAddress> allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", allAddress);
		return "Receipt_info2";
	}

	@RequestMapping("/receipt_info3")
	public String receiptInfo3(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		List<UserAddress> allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", allAddress);
		return "Receipt_manage";
	}

	@RequestMapping("/productBuySubmit")
	public String productBuySubmit(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		String productId = request.getParameter("productId");
		String categoryId = request.getParameter("categoryId");
		String operation = request.getParameter("operation");
		Map<String, ShopProduct> map = (Map<String, ShopProduct>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD_MAP);
		
		// get cart from redis
					BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
					if (cart == null) {
						cart = new BuyerCart();
						BuyerItem item = new BuyerItem();
						item.setAmmount(1);
						item.setProduct(map.get(productId));
						cart.addIterm(item);
						redisUtil.set(user.getId().toString(), cart);
					} else {
						BuyerItem item = new BuyerItem();
						item.setAmmount(1);
						item.setProduct(map.get(productId));
						cart.addIterm(item);
						redisUtil.set(user.getId().toString(), cart);
					}
					model.addAttribute("cart", cart);
					model.addAttribute("items", cart.getItems());
					model.addAttribute("totalPrice", cart.getTotalPrice());
					model.addAttribute("display_cart", true);
					return "Shopping_Cart";

		/*
		if ("add_to_cart".equals(operation)) {
			// get cart from redis
			BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
			if (cart == null) {
				cart = new BuyerCart();
				BuyerItem item = new BuyerItem();
				item.setAmmount(1);
				item.setProduct(map.get(productId));
				cart.addIterm(item);
				redisUtil.set(user.getId().toString(), cart);
			} else {
				BuyerItem item = new BuyerItem();
				item.setAmmount(1);
				item.setProduct(map.get(productId));
				cart.addIterm(item);
				redisUtil.set(user.getId().toString(), cart);
			}
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());
			model.addAttribute("display_cart", true);
			return "Shopping_Cart";
		} else if ("submit_to_buy".equals(operation)) {
			BuyerCart cart = new BuyerCart();
			BuyerItem item = new BuyerItem();
			item.setAmmount(1);
			item.setProduct(map.get(productId));
			cart.addIterm(item);
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());
			session.setAttribute("temp_cart_" + user.getId(), cart);
			List<UserAddress> allAddress = userService.getAddressByUser(user);
			if (allAddress.size() > 0) {
				model.addAttribute("hasAddress", true);
				model.addAttribute("userAddress", allAddress.get(0));
			} else {
				model.addAttribute("hasAddress", false);
			}
			return "Fill_order2";
		}

		return "";
		*/
	}

	@RequestMapping("/shopping_cart/update")
	public String updateShoppingCart(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		String productId = request.getParameter("product_id");

		Map<String, ShopProduct> map = (Map<String, ShopProduct>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD_MAP);

		ShopProduct product = map.get(productId);
		BuyerItem item = new BuyerItem();
		item.setProduct(product);
		UserPO user = (UserPO) session.getAttribute("authUser");
		BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
		cart.removeItem(item);
		redisUtil.set(user.getId().toString(), cart);

		if (cart.getItems().size() > 0) {
			model.addAttribute("display_cart", true);
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());
		} else {
			model.addAttribute("display_cart", false);
		}
//		model.addAttribute("cart", cart);
//		model.addAttribute("items", cart.getItems());
//		model.addAttribute("totalPrice", cart.getTotalPrice());
		return "Shopping_Cart";
	}

	@RequestMapping("/My_info")
	public String myInfo(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());

		return "My_info";
	}

	@RequestMapping("/My_shop_order")
	public String myShoppingOrder(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());
		String actionType = request.getParameter("action_type");
		UserPO user = (UserPO) session.getAttribute("authUser");
		List<ShopOrder> allOrders = userService.getAllShopOrdersByUserId(user.getId());
		if("all_orders".equals(actionType)) {
			model.addAttribute("allOrders", allOrders);
		}else if("".equals(actionType)) {
			
		}else if("".equals(actionType)) {
			
		}

		return "My_order";
	}

	@RequestMapping("/My_shop_receipt")
	public String myShopReceipt(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());

		return "Receipt_info";
	}

	@RequestMapping("/account_recharge")
	public String accountRecharge(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());

		return "Account_recharge";
	}

	@RequestMapping("/balance_transfer")
	public String balanceTransfer(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());

		return "Balance_transfer";
	}

	@RequestMapping("/newAddress")
	public String newAddress(HttpServletRequest request, HttpSession session, Model model) {

		return "New_address";
	}

	@RequestMapping("/newAddress3")
	public String newAddress3(HttpServletRequest request, HttpSession session, Model model) {

		return "New_address2";
	}

	@RequestMapping("/newAddress2")
	public String newAddress2(HttpServletRequest request, HttpSession session, Model model) {

		return "New_address2";
	}

	@RequestMapping("/updateAddress")
	public String updateAddress(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		String addressId = (String) request.getParameter("address_id");
		UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());
		model.addAttribute("useraddress", useraddress);
		return "Update_address";
	}

	@RequestMapping("/updateAddress2")
	public String updateAddress2(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		String addressId = (String) request.getParameter("address_id");
		UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());
		model.addAttribute("useraddress", useraddress);
		return "Update_address2";
	}
	@RequestMapping("/add_new_receipt")
	public String saveNewReceipt(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
		if (cart != null) {
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());
		}
		String address = request.getParameter("city") + request.getParameter("detail_address");
		String tel = request.getParameter("tel");
		String name = request.getParameter("name");
		// System.out.println(address+tel+name);
		UserAddress useraddress = new UserAddress();
		useraddress.setAddress(address);
		useraddress.setName(name);
		useraddress.setTel(tel);
		useraddress.setUser(user);
		useraddress = userService.save(useraddress);
		model.addAttribute("hasAddress", true);
		model.addAttribute("userAddress", useraddress);
		return "Fill_order";
	}

	@RequestMapping("/add_new_receipt2")
	public String saveNewReceipt2(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		
		String address = request.getParameter("city") + request.getParameter("detail_address");
		String tel = request.getParameter("tel");
		String name = request.getParameter("name");
		// System.out.println(address+tel+name);
		UserAddress useraddress = new UserAddress();
		useraddress.setAddress(address);
		useraddress.setName(name);
		useraddress.setTel(tel);
		useraddress.setUser(user);
		useraddress = userService.save(useraddress);
		List<UserAddress> allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", allAddress);
		return "Receipt_manage";
	}

	@RequestMapping("/update_receipt")
	public String updateReceipt(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
		if (cart != null) {
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());
		}
		String address = request.getParameter("city") + request.getParameter("detail_address");
		String tel = request.getParameter("tel");
		String name = request.getParameter("name");
		String addressId = request.getParameter("address_id");
		// System.out.println(address+tel+name);
		UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());
		useraddress.setAddress(address);
		useraddress.setName(name);
		useraddress.setTel(tel);
		useraddress.setUser(user);
		useraddress = userService.save(useraddress);
		model.addAttribute("hasAddress", true);
		model.addAttribute("userAddress", useraddress);
		return "Fill_order";
	}

	@RequestMapping("/update_receipt2")
	public String updateReceipt2(HttpServletRequest request, HttpSession session, Model model) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		String action_type = request.getParameter("action_type");
		String address = request.getParameter("city") + request.getParameter("detail_address");
		String tel = request.getParameter("tel");
		String name = request.getParameter("name");
		String addressId = request.getParameter("address_id");
		if("delete".equals(action_type)) {
			userService.deleteAddressByID(new Integer(addressId).intValue());
		
		}else {
			UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());
			useraddress.setAddress(address);
			useraddress.setName(name);
			useraddress.setTel(tel);
			useraddress.setUser(user);
			useraddress = userService.save(useraddress);
		}
	
		
		
		List<UserAddress> allAddress = userService.getAddressByUser(user);
		model.addAttribute("allAddress", allAddress);
		return "Receipt_manage";
	}

	@RequestMapping("/category_landing")
	public String categoryLanding(HttpServletRequest request, HttpSession session, Model model) {
		String categoryId = request.getParameter("categoryId");
		List<ShopProduct> allProds = (List<ShopProduct>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD);
		List<ShopProduct> basketProdList = new ArrayList<ShopProduct>();
		for (ShopProduct p : allProds) {

			if (new Integer(categoryId).intValue() == p.getCategory().getId()) {
				basketProdList.add(p);
			}
		}

		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("basketProdList", basketProdList);
		return "Inner_page";
	}

	@RequestMapping("/product_details")
	public String productDetails(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());
		String productIdStr = request.getParameter("productId");
		Map<String, ShopProduct> map = (Map<String, ShopProduct>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD_MAP);
		ShopProduct prod = map.get(productIdStr);
		model.addAttribute("prod", prod);
		String categoryId = request.getParameter("categoryId");
		model.addAttribute("categoryId", categoryId);
		return "Details_zoom";
	}

	@RequestMapping("/chooseAddress")
	public String chooseAddress(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		String addressId = (String) request.getParameter("address_id");
		UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
		if (cart != null) {
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());
		}
		model.addAttribute("hasAddress", true);
		model.addAttribute("userAddress", useraddress);
		return "Fill_order";
	}

	@RequestMapping("/chooseAddress2")
	public String chooseAddress2(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		String addressId = (String) request.getParameter("address_id");
		UserAddress useraddress = userService.getAddressById(new Integer(addressId).intValue());
		UserPO user = (UserPO) session.getAttribute("authUser");
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		BuyerCart cart = (BuyerCart) session.getAttribute("temp_cart_" + user.getId().toString());
		if (cart != null) {
			model.addAttribute("cart", cart);
			model.addAttribute("items", cart.getItems());
			model.addAttribute("totalPrice", cart.getTotalPrice());
		}
		model.addAttribute("hasAddress", true);
		model.addAttribute("userAddress", useraddress);
		return "Fill_order2";
	}

	@RequestMapping("/order_details")
	public String orderDetails(HttpServletRequest request, HttpSession session, Model model) {
		String order_id = request.getParameter("order_id");
		UserPO user = (UserPO) session.getAttribute("authUser");
		ShopOrder order = userService.getShopOrderById(new Integer(order_id));
		int addressId = order.getAddress_id();
		UserAddress address = userService.getAddressById(addressId);
		model.addAttribute("address", address);
		model.addAttribute("order", order);
		return "shop_order_details";
	}
	@RequestMapping("/find_products")
	public String findStarProduct(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentUser");
		model.addAttribute("headImg", weiXinUser.getHeadImgUrl());
		model.addAttribute("nickName", weiXinUser.getNickname());
		String operation = request.getParameter("operation");
		List<ShopProduct> allProds = (List<ShopProduct>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_SHOP_PROD);
		if("find_star".equals(operation)) {
			
			List<ShopProduct> starProds = new ArrayList<ShopProduct>();
			for (ShopProduct p : allProds) {
				if (p.getStar_flag() == 1) {
					starProds.add(p);
				}
			}
			model.addAttribute("products", starProds);
		}else if("find_prods_byCategory".equals(operation)) {
			String categoryId = request.getParameter("categoryId");
			List<ShopProduct> products = new ArrayList<ShopProduct>();
			for (ShopProduct p : allProds) {
				if (p.getCategory().getId().intValue() == new Integer(categoryId).intValue()) {
					products.add(p);
				}
			}
			model.addAttribute("products", products);
		}

		return "find";
	}

	@ResponseBody
	@RequestMapping("/updateCartFromAjax")
	public Map<String, Object> updateCartFromAjax(@RequestBody Map<String, Object> params,HttpSession session) {
		UserPO user = (UserPO) session.getAttribute("authUser");
		
		if (user == null) {
			throw new InvalidUserException("Invalide access");
		}
		
		BuyerCart cart = (BuyerCart) redisUtil.get(user.getId().toString());
		String productId = (String) params.get("prod_id");
		System.out.println(productId);
		String operation = (String) params.get("operation");
		System.out.println(operation);
		List<BuyerItem> items = cart.getItems();
		if("minus".equals(operation)) {
			for(BuyerItem i: items) {
				
				if(i.getProduct().getId() == new Integer(productId).intValue()) {
					if(i.getAmmount()>1) {
						i.setAmmount(i.getAmmount()-1);
					}else {
						
					}
				}
			}
		}else if("plus".equals(operation)) {
			System.out.println("plus");
			for(BuyerItem i: items) {
				System.out.println(i.getProduct().getId());
				if(i.getProduct().getId() == new Integer(productId).intValue()) {
					i.setAmmount(i.getAmmount()+1);
				}
			}
		}
		redisUtil.set(user.getId().toString(), cart);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("statusCode", "200");
		 result.put("total_price", cart.getTotalPrice());
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
