package com.larry.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.larry.AppConf;
import com.larry.bean.ElectRecOrderFormBean;
import com.larry.bean.MetalRecOrderFormBean;
import com.larry.bean.PaperRecOrderFormBean;
import com.larry.bean.PhoneRecOrderFormBean;
import com.larry.bean.PlasticRecOrderFormBean;
import com.larry.bean.WeixinUser;
import com.larry.cache.EhCacheUtil;
import com.larry.consts.OrderConst;
import com.larry.entity.OrderItemsPO;
import com.larry.entity.OrderPO;
import com.larry.entity.ProductCatPO;
import com.larry.entity.ProductPO;
import com.larry.entity.UserAddress;
import com.larry.entity.UserPO;
import com.larry.exception.InvalidUserException;
import com.larry.service.AuthUserService;
import com.larry.service.FormNoGenerateService;
import com.larry.service.ManagerService;
import com.larry.service.OrderService;
import com.larry.service.WeixinMessageService;
import com.larry.utils.FormNumTypeEnum;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private WeixinMessageService weixinMessageService;
	@Autowired
	private AppConf appConf;
	@Autowired
	private AuthUserService userService;
	@Autowired
	private FormNoGenerateService formNoGenerator;

	@RequestMapping(value = "paperRecOrderSubmit", method = RequestMethod.POST)
	public ModelAndView paperRecOrderSubmit(HttpServletRequest request, HttpSession session,
			PaperRecOrderFormBean orderFormBean, Model model) throws UnsupportedEncodingException {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		int userID = user.getId();

		String collectingDate = orderFormBean.getCollectingDate();
		System.out.println(request.getParameter("comments"));
		System.out.println(orderFormBean.getComments());
		
		int addressId = orderFormBean.getAddress_id();
		System.out.println(orderFormBean.getCollectingDate());
		OrderPO order = new OrderPO();
		order.setUser_id(userID);
		order.setAddress_id(new Integer(addressId));
		String[] productsID = orderFormBean.getProductId();
		Map<String, ProductPO> productMap = (HashMap<String, ProductPO>) EhCacheUtil
				.get(EhCacheUtil.CACHE_KEY_HASHPROD);
		Set<OrderItemsPO> itemsSet = new HashSet<OrderItemsPO>();
		String rec_type_name = "";
		ProductCatPO productCat = null;
		for (String s : productsID) {
			productCat = productMap.get(s).getProductCat();
			rec_type_name = productMap.get(s).getProductCat().getName();
			String prodName = productMap.get(s).getName();
			OrderItemsPO item = new OrderItemsPO();
			item.setOrder(order);
			item.setProduct_id(new Integer(s).intValue());
			item.setProduct_name(prodName);
			itemsSet.add(item);
		}
		order.setProductCat(productCat);
		order.setOrderItems(itemsSet);
		order.setStatus(OrderConst.NEW_ORDER);
		order.setStart_date(orderFormBean.getCollectingDate());
		// Date now = new Date();
		order.setCreated_date(new java.sql.Date(System.currentTimeMillis()));
		String orderNum = formNoGenerator.generateFormNo(FormNumTypeEnum.REC_ORDER);
		order.setOrder_number(orderNum);
		order.setComments(orderFormBean.getComments());
		order = orderService.save(order);

		//send weixin message to user
//		weixinMessageService.sendTemplateMessages(appConf.getWeixinOrderSubTemp(), weiXinUser.getOpenId(), order,
//				new String(appConf.getWeixinOrderSubMsgHead().getBytes("ISO-8859-1"), "UTF-8"), rec_type_name,
//				order.getStart_date(), null, new String(appConf.getWeixinMsgRemark().getBytes("ISO-8859-1"), "UTF-8"));
//		model.addAttribute("info_msg", "order_submited");
		return new ModelAndView("redirect:/my_order?info_msg=order_submited");
	}
	@RequestMapping(value = "plasticRecOrderSubmit", method = RequestMethod.POST)
	public ModelAndView plasticRecOrderSubmit(HttpServletRequest request, HttpSession session,
			PlasticRecOrderFormBean orderFormBean, Model model) throws UnsupportedEncodingException {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		int userID = user.getId();

		String collectingDate = orderFormBean.getCollectingDate();
		System.out.println(request.getParameter("comments"));
		System.out.println(orderFormBean.getComments());
		
		int addressId = orderFormBean.getAddress_id();
		System.out.println(orderFormBean.getCollectingDate());
		OrderPO order = new OrderPO();
		order.setUser_id(userID);
		order.setAddress_id(new Integer(addressId));
		String[] productsID = orderFormBean.getProductId();
		Map<String, ProductPO> productMap = (HashMap<String, ProductPO>) EhCacheUtil
				.get(EhCacheUtil.CACHE_KEY_HASHPROD);
		Set<OrderItemsPO> itemsSet = new HashSet<OrderItemsPO>();
		String rec_type_name = "";
		ProductCatPO productCat = null;
		for (String s : productsID) {
			productCat = productMap.get(s).getProductCat();
			rec_type_name = productMap.get(s).getProductCat().getName();
			String prodName = productMap.get(s).getName();
			OrderItemsPO item = new OrderItemsPO();
			item.setOrder(order);
			item.setProduct_id(new Integer(s).intValue());
			item.setProduct_name(prodName);
			itemsSet.add(item);
		}
		order.setProductCat(productCat);
		order.setOrderItems(itemsSet);
		order.setStatus(OrderConst.NEW_ORDER);
		order.setStart_date(orderFormBean.getCollectingDate());
		// Date now = new Date();
		order.setCreated_date(new java.sql.Date(System.currentTimeMillis()));
		String orderNum = formNoGenerator.generateFormNo(FormNumTypeEnum.REC_ORDER);
		order.setOrder_number(orderNum);
		order.setComments(orderFormBean.getComments());
		order = orderService.save(order);

		//send weixin message to user
//		weixinMessageService.sendTemplateMessages(appConf.getWeixinOrderSubTemp(), weiXinUser.getOpenId(), order,
//				new String(appConf.getWeixinOrderSubMsgHead().getBytes("ISO-8859-1"), "UTF-8"), rec_type_name,
//				order.getStart_date(), null, new String(appConf.getWeixinMsgRemark().getBytes("ISO-8859-1"), "UTF-8"));
//		model.addAttribute("info_msg", "order_submited");
		return new ModelAndView("redirect:/my_order?info_msg=order_submited");
	}
	
	@RequestMapping(value = "metalRecOrderSubmit", method = RequestMethod.POST)
	public ModelAndView metalRecOrderSubmit(HttpServletRequest request, HttpSession session,
			MetalRecOrderFormBean orderFormBean, Model model) throws UnsupportedEncodingException {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		int userID = user.getId();

		String collectingDate = orderFormBean.getCollectingDate();
		System.out.println(request.getParameter("comments"));
		System.out.println(orderFormBean.getComments());
		
		int addressId = orderFormBean.getAddress_id();
		System.out.println(orderFormBean.getCollectingDate());
		OrderPO order = new OrderPO();
		order.setUser_id(userID);
		order.setAddress_id(new Integer(addressId));
		String[] productsID = orderFormBean.getProductId();
		Map<String, ProductPO> productMap = (HashMap<String, ProductPO>) EhCacheUtil
				.get(EhCacheUtil.CACHE_KEY_HASHPROD);
		Set<OrderItemsPO> itemsSet = new HashSet<OrderItemsPO>();
		String rec_type_name = "";
		ProductCatPO productCat = null;
		for (String s : productsID) {
			productCat = productMap.get(s).getProductCat();
			rec_type_name = productMap.get(s).getProductCat().getName();
			String prodName = productMap.get(s).getName();
			OrderItemsPO item = new OrderItemsPO();
			item.setOrder(order);
			item.setProduct_id(new Integer(s).intValue());
			item.setProduct_name(prodName);
			itemsSet.add(item);
		}
		order.setProductCat(productCat);
		order.setOrderItems(itemsSet);
		order.setStatus(OrderConst.NEW_ORDER);
		order.setStart_date(orderFormBean.getCollectingDate());
		// Date now = new Date();
		order.setCreated_date(new java.sql.Date(System.currentTimeMillis()));
		String orderNum = formNoGenerator.generateFormNo(FormNumTypeEnum.REC_ORDER);
		order.setOrder_number(orderNum);
		order.setComments(orderFormBean.getComments());
		order = orderService.save(order);

		//send weixin message to user
//		weixinMessageService.sendTemplateMessages(appConf.getWeixinOrderSubTemp(), weiXinUser.getOpenId(), order,
//				new String(appConf.getWeixinOrderSubMsgHead().getBytes("ISO-8859-1"), "UTF-8"), rec_type_name,
//				order.getStart_date(), null, new String(appConf.getWeixinMsgRemark().getBytes("ISO-8859-1"), "UTF-8"));
//		model.addAttribute("info_msg", "order_submited");
		return new ModelAndView("redirect:/my_order?info_msg=order_submited");
	}
	
	@RequestMapping(value = "phoneRecOrderSubmit", method = RequestMethod.POST)
	public ModelAndView phoneRecOrderSubmit(HttpServletRequest request, HttpSession session,
			PhoneRecOrderFormBean orderFormBean, Model model) throws UnsupportedEncodingException {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		int userID = user.getId();

		String collectingDate = orderFormBean.getCollectingDate();
		System.out.println(request.getParameter("comments"));
		System.out.println(orderFormBean.getComments());
		
		int addressId = orderFormBean.getAddress_id();
		System.out.println(orderFormBean.getCollectingDate());
		OrderPO order = new OrderPO();
		order.setUser_id(userID);
		order.setAddress_id(new Integer(addressId));
		String[] productsID = orderFormBean.getProductId();
		Map<String, ProductPO> productMap = (HashMap<String, ProductPO>) EhCacheUtil
				.get(EhCacheUtil.CACHE_KEY_HASHPROD);
		Set<OrderItemsPO> itemsSet = new HashSet<OrderItemsPO>();
		String rec_type_name = "";
		ProductCatPO productCat = null;
		for (String s : productsID) {
			productCat = productMap.get(s).getProductCat();
			rec_type_name = productMap.get(s).getProductCat().getName();
			String prodName = productMap.get(s).getName();
			OrderItemsPO item = new OrderItemsPO();
			item.setOrder(order);
			item.setProduct_id(new Integer(s).intValue());
			item.setProduct_name(prodName);
			itemsSet.add(item);
		}
		order.setProductCat(productCat);
		order.setOrderItems(itemsSet);
		order.setStatus(OrderConst.NEW_ORDER);
		order.setStart_date(orderFormBean.getCollectingDate());
		// Date now = new Date();
		order.setCreated_date(new java.sql.Date(System.currentTimeMillis()));
		String orderNum = formNoGenerator.generateFormNo(FormNumTypeEnum.REC_ORDER);
		order.setOrder_number(orderNum);
		order.setComments(orderFormBean.getComments());
		order = orderService.save(order);

		//send weixin message to user
//		weixinMessageService.sendTemplateMessages(appConf.getWeixinOrderSubTemp(), weiXinUser.getOpenId(), order,
//				new String(appConf.getWeixinOrderSubMsgHead().getBytes("ISO-8859-1"), "UTF-8"), rec_type_name,
//				order.getStart_date(), null, new String(appConf.getWeixinMsgRemark().getBytes("ISO-8859-1"), "UTF-8"));
//		model.addAttribute("info_msg", "order_submited");
		return new ModelAndView("redirect:/my_order?info_msg=order_submited");
	}
	
	@RequestMapping(value = "electRecOrderSubmit", method = RequestMethod.POST)
	public ModelAndView electRecOrderSubmit(HttpServletRequest request, HttpSession session,
			ElectRecOrderFormBean orderFormBean, Model model) throws UnsupportedEncodingException {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
		int userID = user.getId();

		String collectingDate = orderFormBean.getCollectingDate();
		
		int addressId = orderFormBean.getAddress_id();
		
		OrderPO order = new OrderPO();
		order.setUser_id(userID);
		order.setAddress_id(new Integer(addressId));
		String[] productsID = orderFormBean.getProductId();
		Map<String, ProductPO> productMap = (HashMap<String, ProductPO>) EhCacheUtil
				.get(EhCacheUtil.CACHE_KEY_HASHPROD);
		Set<OrderItemsPO> itemsSet = new HashSet<OrderItemsPO>();
		String rec_type_name = "";
		ProductCatPO productCat = null;
		for (String s : productsID) {
			productCat = productMap.get(s).getProductCat();
			rec_type_name = productMap.get(s).getProductCat().getName();
			String prodName = productMap.get(s).getName();
			OrderItemsPO item = new OrderItemsPO();
			item.setOrder(order);
			item.setProduct_id(new Integer(s).intValue());
			item.setProduct_name(prodName);
			itemsSet.add(item);
		}
		order.setProductCat(productCat);
		order.setOrderItems(itemsSet);
		order.setStatus(OrderConst.NEW_ORDER);
		order.setStart_date(orderFormBean.getCollectingDate());
		// Date now = new Date();
		order.setCreated_date(new java.sql.Date(System.currentTimeMillis()));
		String orderNum = formNoGenerator.generateFormNo(FormNumTypeEnum.REC_ORDER);
		order.setOrder_number(orderNum);
		order.setComments(orderFormBean.getComments());
		order = orderService.save(order);

		//send weixin message to user
//		weixinMessageService.sendTemplateMessages(appConf.getWeixinOrderSubTemp(), weiXinUser.getOpenId(), order,
//				new String(appConf.getWeixinOrderSubMsgHead().getBytes("ISO-8859-1"), "UTF-8"), rec_type_name,
//				order.getStart_date(), null, new String(appConf.getWeixinMsgRemark().getBytes("ISO-8859-1"), "UTF-8"));
//		model.addAttribute("info_msg", "order_submited");
		return new ModelAndView("redirect:/my_order?info_msg=order_submited");
	}
	@RequestMapping(value = "order_details/show")
	public String orderDetails(HttpServletRequest request, HttpSession session, Model model) {
		WeixinUser weiXinUser = (WeixinUser) session.getAttribute("currentWeixinUser");
		UserPO user = (UserPO) session.getAttribute("authUser");
	
		String orderId = request.getParameter("orderID");
		
		String from = request.getParameter("from");
		System.out.println("%%%%%%%%%%%" + orderId);
		OrderPO order = orderService.getOrderByID(new Integer(orderId).intValue());
		if (OrderConst.CLOSED_ORDER == order.getStatus() || OrderConst.PROCESSING_ORDER == order.getStatus()) {
			model.addAttribute("contactor", managerService.getTaskByOrderId(order.getId()).getManager().getUser_name());
		}
		if (order.getUser_id() == user.getId()) {

		} else {
			if (session.getAttribute("currentManager") != null) {

			} else {
				throw new InvalidUserException("invalid user access!");
			}

		}

		Set<OrderItemsPO> items = order.getOrderItems();
		model.addAttribute("order", order);
		model.addAttribute("items", items);
		model.addAttribute("from", from);
		UserAddress address = userService.getAddressById(order.getAddress_id());
		model.addAttribute("address", address);
		if ("user".equals(from)) {
			return "recycle_home/order_details";
		} else {
			return "recycle_home/order_details";
		}
		
		
	}
	@RequestMapping(value = "cancle_order")
	public ModelAndView cancleOrder(HttpServletRequest request, HttpSession session, Model model) throws UnsupportedEncodingException {
		WeixinUser weiXinUser = (WeixinUser) request.getSession().getAttribute("currentWeixinUser");
		String orderId = request.getParameter("orderID");
		
		OrderPO order = orderService.getOrderByID(new Integer(orderId).intValue());
		//check order status before save
		if(order.getStatus()  == OrderConst.NEW_ORDER) {
			order.setStatus(OrderConst.CANCLED_ORDER);
			orderService.save(order);
			
			return new ModelAndView("redirect:/my_order?info_msg=order_cancled");
			
		}else {
			
			return new ModelAndView("redirect:/my_order?info_msg=order_cancled_failed");
		}
		
////		weixinMessageService.sendTemplateMessages(appConf.getWeixinOrderCanTemp(), weiXinUser.getOpenId(), order,
////				new String(appConf.getWeixinOrderCancMsgHead().getBytes("ISO-8859-1"), "UTF-8"), "FD",
////				order.getStart_date(), new String(appConf.getWeixinmsgCacByUser().getBytes("ISO-8859-1"), "UTF-8"),
//				new String(appConf.getWeixinMsgRemark().getBytes("ISO-8859-1"), "UTF-8"));
		
	}
}
