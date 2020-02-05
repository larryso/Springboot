package com.larry.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.larry.AppConf;
import com.larry.cache.EhCacheUtil;
import com.larry.consts.ManagerConst;
import com.larry.consts.OrderConst;
import com.larry.entity.ManagerPO;
import com.larry.entity.MyScore;
import com.larry.entity.OrderItemsPO;
import com.larry.entity.OrderPO;
import com.larry.entity.ProductCatPO;
import com.larry.entity.ProductPO;
import com.larry.entity.TaskPO;
import com.larry.entity.UserAddress;
import com.larry.entity.UserPO;
import com.larry.exception.InvalidUserException;
import com.larry.service.AuthUserService;
import com.larry.service.ManagerService;
import com.larry.service.OrderService;
import com.larry.service.WeixinMessageService;
import com.larry.utils.MD5Utils;
import com.larry.utils.RedisUtil;

@Controller
@Transactional(rollbackOn=Exception.class)
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AuthUserService userService;
	@Autowired
	private AppConf appConf;
	@Autowired
    private RedisUtil redisutil;
	@Autowired
	private WeixinMessageService weixinMessageService;

	@RequestMapping(value = "login_submit", method = RequestMethod.POST)
	public String ManagerLogin(HttpServletRequest request, HttpSession session, Model model)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String loginName = request.getParameter("login_name");
		String password = request.getParameter("password");
		String vCode = request.getParameter("vcode");
		System.out.println("loginName:" + loginName);
		System.out.println("password:" + password);
		System.out.println(session.getAttribute("res"));
		String vCodeSession = ((Integer)session.getAttribute("res")).toString();
		if(!vCode.equals(vCodeSession)) {
			model.addAttribute("errorMsg", "invalid vCode");
			return "recycle_home/login";
		}
		ManagerPO manager = managerService.findManagerByName(loginName);
		// authenticate username
		if (manager == null) {
			model.addAttribute("errorMsg", "invalid username");
			return "recycle_home/login";
		} else {
			System.out.println(manager.getPassword());
			if (MD5Utils.validPassword(password, manager.getPassword())) {
				session.setAttribute("currentManager", manager);
				List<OrderPO> newOrders = orderService.getAllNewOrders();
				newOrders.sort(new Comparator<OrderPO>() {

					public int compare(OrderPO o1, OrderPO o2) {
						// TODO Auto-generated method stub
						return o2.getId() - o1.getId();
					}

				});
				model.addAttribute("newOrders", newOrders);
				//load tasks
				/*
				List<TaskPO>tasks = managerService.getTasksByManagerId(manager.getId());
				List<OrderPO> pendingOrders = new ArrayList<OrderPO>();
				List<OrderPO> completedOrders = new ArrayList<OrderPO>();
				for(TaskPO task: tasks) {
					OrderPO order = task.getOrder();
					int orderStatus = order.getStatus();
					if(OrderConst.PROCESSING_ORDER == orderStatus) {
						pendingOrders.add(order);
					}else if(OrderConst.CLOSED_ORDER == orderStatus) {
						completedOrders.add(order);
					}
				}
				model.addAttribute("pendingOrders", pendingOrders);
				model.addAttribute("completedOrders", completedOrders);
				*/
			} else {
				model.addAttribute("errorMsg", "invalid password");
				return "recycle_home/login";
			}
		}

		return "recycle_home/manager_main";
	}
	@RequestMapping(value = "manager_main")
	public String manager_main(HttpServletRequest request, HttpSession session, Model model) {
		if(session.getAttribute("currentManager")!= null) {
			
		}else {
			throw new InvalidUserException("invalid user access!");
		}
		List<OrderPO> newOrders = orderService.getAllNewOrders();
		newOrders.sort(new Comparator<OrderPO>() {

			public int compare(OrderPO o1, OrderPO o2) {
				// TODO Auto-generated method stub
				return o2.getStart_date().compareTo(o1.getStart_date()) ;
			}

		});
		model.addAttribute("newOrders", newOrders);
		return "recycle_home/manager_main";
	}

	@RequestMapping(value = "manager_order_details/show")
	public String ManagerOrderDetails(HttpServletRequest request, HttpSession session, Model model) {
		if(session.getAttribute("currentManager")!= null) {
			
		}else {
			throw new InvalidUserException("invalid user access!");
		}
		String orderId = request.getParameter("orderID");
		String from = request.getParameter("from");
		System.out.println("%%%%%%%%%%%"+orderId);
		OrderPO order = orderService.getOrderByID(new Integer(orderId).intValue());
		Set<OrderItemsPO> items = order.getOrderItems();
		model.addAttribute("order", order);
		model.addAttribute("items", items);
		int addressId = order.getAddress_id();
		
		UserAddress address = userService.getAddressById(addressId);
		model.addAttribute("address", address);
		Map<String, ProductPO> productMap = (Map<String, ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_HASHPROD);
		Map<Integer, String> productPriceMap = new HashMap<Integer, String>();
		for(OrderItemsPO item: items) {
			String productId = new Integer(item.getProduct_id()).toString();
			productPriceMap.put(new Integer(item.getProduct_id()), productMap.get(productId).getPrice());
		}
		return "recycle_home/manager_order_details";
	}

	@RequestMapping(value = "pick_order")
	public String pickOrder(HttpServletRequest request, HttpSession session, Model model) {
		
		ManagerPO manager = (ManagerPO) session.getAttribute("currentManager");
		if(manager== null) {
			throw new InvalidUserException("manager null in session");
		}
		String orderIdStr = request.getParameter("orderId");
		
		OrderPO pickedOrder = orderService.getOrderByID(new Integer(orderIdStr).intValue());
		
		//before pick order, need check order status
		if(pickedOrder.getStatus()  != OrderConst.NEW_ORDER) {
			
			
			model.addAttribute("errorMsg", "order_taken");
		
			Set<OrderItemsPO> items = pickedOrder.getOrderItems();
			model.addAttribute("order", pickedOrder);
			model.addAttribute("items", items);
			int addressId = pickedOrder.getAddress_id();
			
			UserAddress address = userService.getAddressById(addressId);
			model.addAttribute("address", address);
			return "recycle_home/manager_order_details";
			
		}else {
			//secure and make sure one thread pick by using redis
			String redisOrderKey = "PICK_ORDER"+orderIdStr;
			long redisInc = redisutil.incr(redisOrderKey, 1);
			if(redisInc >1) {
				
				Set<OrderItemsPO> items = pickedOrder.getOrderItems();
				model.addAttribute("order", pickedOrder);
				model.addAttribute("items", items);
				int addressId = pickedOrder.getAddress_id();
				
				UserAddress address = userService.getAddressById(addressId);
				model.addAttribute("address", address);
				model.addAttribute("errorMsg", "order_taken");
				return "recycle_home/manager_order_details";
			}
			//secure end
			managerService.pickOrder(new Integer(orderIdStr).intValue(), manager);
			List<OrderPO> newOrdersAfterPick = orderService.getAllNewOrders();
			newOrdersAfterPick.sort(new Comparator<OrderPO>() {

				public int compare(OrderPO o1, OrderPO o2) {
					// TODO Auto-generated method stub
					return o2.getId() - o1.getId();
				}

			});
			model.addAttribute("newOrders", newOrdersAfterPick);
			model.addAttribute("INFO_MSG", "pick_success");
			//remove redis key
			redisutil.del(redisOrderKey);
			//end 
			int userId = pickedOrder.getUser_id();
			UserPO user = userService.getUserById(userId);
			
			String rec_type_name = pickedOrder.getProductCat().getName();
			try {
				weixinMessageService.sendTemplateMessages(appConf.getWeixinOrderTakeTemp(), user.getWeixin_openId(), pickedOrder,
						new String((appConf.getWeixinOrderTakeMsgHead()+manager.getUser_name()).getBytes("ISO-8859-1"), "UTF-8"), pickedOrder.getOrder_number(),
						rec_type_name, pickedOrder.getStart_date(), new String(appConf.getWeixinOrderTakeRemark().getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "recycle_home/manager_main";
		}
		
	}
	
	@RequestMapping(value = "handle_order", method = RequestMethod.POST)
	public String HandleOrder(HttpServletRequest request, HttpSession session, Model model) {
		String actionType = request.getParameter("action_type");
		System.out.println("&&&&&&&&&&&"+actionType);
		if("calculate".equals(actionType)) {
			Enumeration parameterNames = request.getParameterNames();
			BigDecimal totalPrice = new BigDecimal("0");
			Map<Integer, String> productsWeight = new HashMap<Integer, String>();
			while(parameterNames.hasMoreElements()) {
				String parameterName = (String) parameterNames.nextElement();
				System.out.println(parameterName+ "->"+request.getParameter(parameterName));
				/**
				 * * orderId->12
				   * 1->2
				   * 3->6
				   * action_type->calculate
				 */
				if("orderId".equals(parameterName)){
					
				}else if("action_type".equals(parameterName)) {
					
				}else {
					int productId = new Integer(parameterName);
					Map<String, ProductPO> productMap = (Map<String, ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_HASHPROD);
					ProductPO product = (ProductPO) productMap.get(new Integer(productId).toString());
					String priceStr = product.getPrice();
					BigDecimal price = new BigDecimal(priceStr.substring(0,priceStr.indexOf("/")-1));
					String weightStr = request.getParameter(parameterName);
					BigDecimal weight = new BigDecimal(weightStr);
					totalPrice = totalPrice.add(price.multiply(weight));
					productsWeight.put(productId, weightStr);
					
				}
			}
			String orderId = request.getParameter("orderId");
			System.out.println("%%%%%%%%%%%"+orderId);
			OrderPO order = orderService.getOrderByID(new Integer(orderId).intValue());
			Set<OrderItemsPO> items = order.getOrderItems();
			model.addAttribute("order", order);
			model.addAttribute("items", items);
			model.addAttribute("from","manager");
			Map<String, ProductPO> productMap = (Map<String, ProductPO>) EhCacheUtil.get(EhCacheUtil.CACHE_KEY_HASHPROD);
			Map<Integer, String> productPriceMap = new HashMap<Integer, String>();
			for(OrderItemsPO item: items) {
				String productId = new Integer(item.getProduct_id()).toString();
				productPriceMap.put(new Integer(item.getProduct_id()), productMap.get(productId).getPrice());
			}
			model.addAttribute("productPriceMap",productPriceMap);
			model.addAttribute("totalPrice", totalPrice.toString());
			model.addAttribute("productsWeight", productsWeight);
			return"manager_order_details";
			       
		}else if("reject_order".equals(actionType)) {
			String orderId = request.getParameter("orderId");
			String rejectedReason = request.getParameter("order_rejected_reason");
			OrderPO order = orderService.getOrderByID(new Integer(orderId).intValue());
			order.setStatus(OrderConst.REJECTED_ORDER);
			order.setRejected_reason(rejectedReason);
			java.util.Date currentUtilDate = new java.util.Date();
			order.setEnd_date(new java.sql.Date(currentUtilDate.getTime()));
			order = orderService.save(order);
			int addressId = order.getAddress_id();
			Set<OrderItemsPO> items = order.getOrderItems();
			model.addAttribute("order", order);
			model.addAttribute("items", items);
			model.addAttribute("from","manager");
			UserAddress address = userService.getAddressById(addressId);
			model.addAttribute("address", address);
			model.addAttribute("INFO_MSG", "reject_order");
			int userId = order.getUser_id();
			UserPO user = userService.getUserById(userId);
			
			String rec_type_name = order.getProductCat().getName();
			
			try {
				weixinMessageService.sendCancOrderTemplateMessages(appConf.getWeixinOrderCanTemp(), user.getWeixin_openId(), order,
						new String((appConf.getWeixinOrderCancMsgHead()).getBytes("ISO-8859-1"), "UTF-8"), order.getOrder_number(),
						rec_type_name, address.getName()+"/"+address.getTel(), new String(appConf.getWeixinMsgRemark().getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return"recycle_home/manager_task_details";
		}else if("close_order".equals(actionType)) {
			String orderId = request.getParameter("orderId");
			System.out.println("%%%%%%%%%%%"+orderId);
			OrderPO order = orderService.getOrderByID(new Integer(orderId).intValue());
			order.setStatus(OrderConst.CLOSED_ORDER);
			Set<OrderItemsPO> orderItems = order.getOrderItems();
			Enumeration parameterNames = request.getParameterNames();
			BigDecimal totalPrice = new BigDecimal("0");
			Map<Integer, BigDecimal> itemPriceMap = new HashMap<Integer, BigDecimal>();
			while(parameterNames.hasMoreElements()) {
				String parameterName = (String) parameterNames.nextElement();
				System.out.println(parameterName+ "->"+request.getParameter(parameterName));
				/**
				 * * orderId->12
				   * 1->2
				   * 3->6
				   * action_type->calculate
				 */
				if("orderId".equals(parameterName)){
					
				}else if("action_type".equals(parameterName)) {
					
				}else if("order_rejected_reason".equals(parameterName)) {
					
				}
				else {
					int itemId = new Integer(parameterName);
					String priceStr = request.getParameter(parameterName);
					
					BigDecimal priceNum = new BigDecimal(priceStr);
					totalPrice = totalPrice.add(priceNum);
					itemPriceMap.put(itemId, priceNum);
					
				}
			}
			
			order.setTotal_price(totalPrice);
			for(OrderItemsPO i:orderItems) {
				
				i.setPrice(itemPriceMap.get(i.getId()));
			}
			java.util.Date currentUtilDate = new java.util.Date();
			order.setEnd_date(new java.sql.Date(currentUtilDate.getTime()));
			order = orderService.save(order);
			Set<OrderItemsPO> items = order.getOrderItems();
			model.addAttribute("order", order);
			model.addAttribute("items", items);
			UserAddress address = userService.getAddressById(order.getAddress_id());
			model.addAttribute("address", address);
			model.addAttribute("INFO_MSG", "close_order");
			if(totalPrice.compareTo(new BigDecimal(0))>0) {
				BigDecimal scoreRate = new BigDecimal(appConf.getScoreRate());
				int score_int = totalPrice.divide(scoreRate,0,BigDecimal.ROUND_DOWN).intValue();
				MyScore myScore = new MyScore();
				myScore.setCreate_time(new java.sql.Date(System.currentTimeMillis()));
				myScore.setScore(score_int);
				myScore.setScore_type(0);
				myScore.setUser_id(order.getUser_id());
				userService.updateScore(myScore);
			
			}
			int userId = order.getUser_id();
			UserPO user = userService.getUserById(userId);
			
			String rec_type_name = order.getProductCat().getName();
			try {
				weixinMessageService.sendCloseOrderTemplateMessages(appConf.getWeixinOrdercompTemp(), user.getWeixin_openId(), order,
						new String((appConf.getWeixinOrderCompleteMsgHead()).getBytes("ISO-8859-1"), "UTF-8"), order.getOrder_number(),
						rec_type_name, order.getEnd_date().toString(), new String(appConf.getWeixinMsgRemark().getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return"recycle_home/manager_task_details";
		}else {
			return "";
		}
	}

	@RequestMapping("/manager_create")
	public ManagerPO addManager() {
		ManagerPO manager = new ManagerPO();
		try {

			manager.setUser_name("13156311875");
			manager.setPassword(MD5Utils.getEncryptedPwd("12345678"));
			manager.setAccount_status(ManagerConst.ACCOUNT_STATUS_ACTIVE);
			manager.setCreated_date(new Date(new java.util.Date().getTime()));
			managerService.save(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manager;

	}
	@RequestMapping("manager/my_center")
	public String managerCenter(HttpServletRequest request, HttpSession session, Model model) {
		
		return "recycle_home/manager_center";

	}
	@RequestMapping(value = "manager/mytask")
	public String mytask(HttpServletRequest request, HttpSession session, Model model) {
		ManagerPO manager = (ManagerPO) session.getAttribute("currentManager");
		if(manager== null) {
			throw new InvalidUserException("manager null in session");
		}
		List<TaskPO>tasks = managerService.getTasksByManagerId(manager.getId());
		List<OrderPO> pendingOrders = new ArrayList<OrderPO>();
		List<OrderPO> completedOrders = new ArrayList<OrderPO>();
		for(TaskPO task: tasks) {
			OrderPO order = task.getOrder();
			int orderStatus = order.getStatus();
			if(OrderConst.PROCESSING_ORDER == orderStatus) {
				pendingOrders.add(order);
			}else if(OrderConst.CLOSED_ORDER == orderStatus|| OrderConst.REJECTED_ORDER== orderStatus) {
				completedOrders.add(order);
			}
		}
		model.addAttribute("pendingOrders", pendingOrders);
		model.addAttribute("completedOrders", completedOrders);
		return "recycle_home/manager_mytask";
	}
	@RequestMapping(value = "manager/passwordManage")
	public String passwordManage(HttpServletRequest request, HttpSession session, Model model) {
		ManagerPO manager = (ManagerPO) session.getAttribute("currentManager");
		if(manager== null) {
			throw new InvalidUserException("manager null in session");
		}
		
		return "recycle_home/manager_password";
	}
	@RequestMapping(value = "passwordChange", method = RequestMethod.POST)
	public String passwordChange(HttpServletRequest request, HttpSession session, Model model) {
		ManagerPO manager = (ManagerPO) session.getAttribute("currentManager");
		if(manager== null) {
			throw new InvalidUserException("manager null in session");
		}
		String oldPasswd = request.getParameter("old_pass");
		String newPasswd = request.getParameter("new_pass");
		String cNewPasswed = request.getParameter("new_pass2");
		try {
			if (MD5Utils.validPassword(oldPasswd, manager.getPassword())) {
				manager.setPassword(MD5Utils.getEncryptedPwd(newPasswd));
				managerService.save(manager);
				model.addAttribute("info_msg", "passwd_change_success");
				model.addAttribute("oldPasswd", oldPasswd);
				model.addAttribute("newPasswd", newPasswd);
				model.addAttribute("cNewPasswed", cNewPasswed);
				
			}else {
				model.addAttribute("info_msg", "old_pass_err");
				model.addAttribute("oldPasswd", oldPasswd);
				model.addAttribute("newPasswd", newPasswd);
				model.addAttribute("cNewPasswed", cNewPasswed);
			}
		} catch (Exception e) {
			model.addAttribute("info_msg", "pass_change_err");
			model.addAttribute("oldPasswd", oldPasswd);
			model.addAttribute("newPasswd", newPasswd);
			model.addAttribute("cNewPasswed", cNewPasswed);
			e.printStackTrace();
		}finally {
			return "recycle_home/manager_password";
		}
		
	}
	@RequestMapping(value = "manager/taskView")
	public String taskView(HttpServletRequest request, HttpSession session, Model model) {
		ManagerPO manager = (ManagerPO) session.getAttribute("currentManager");
		if(manager== null) {
			throw new InvalidUserException("manager null in session");
		}
		String orderId = request.getParameter("orderID");
		String from = request.getParameter("from");
		System.out.println("%%%%%%%%%%%"+orderId);
		OrderPO order = orderService.getOrderByID(new Integer(orderId).intValue());
		Set<OrderItemsPO> items = order.getOrderItems();
		model.addAttribute("order", order);
		model.addAttribute("items", items);
		int addressId = order.getAddress_id();
		
		UserAddress address = userService.getAddressById(addressId);
		model.addAttribute("address", address);
		if("view_new_task".equals(from)) {
			return "recycle_home/manager_taskView";
		}else if("view_closed_task".equals(from)) {
			return "recycle_home/manager_task_details";
		}else {
			return "";
		}
		
	}
	
}
