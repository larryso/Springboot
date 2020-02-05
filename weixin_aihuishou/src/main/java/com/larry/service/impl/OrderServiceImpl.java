package com.larry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.bean.PaperRecFormBean;
import com.larry.dao.OrderDAO;
import com.larry.entity.OrderPO;
import com.larry.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDao;

	public PaperRecFormBean preparePaperRecForm() {
		return null;
	}

	public OrderPO save(OrderPO order) {
		return orderDao.save(order);
	}

	public List<OrderPO> getAllOrdersByUserID(int userID) {
		return orderDao.findOrdersByUserID(userID);
	}

	public OrderPO getOrderByID(int orderID) {
		return orderDao.findOrdersByID(orderID);
	}

	public OrderPO updateOrder(OrderPO order) {
		return orderDao.save(order);
	}

	public List<OrderPO> getAllNewOrders() {
		// TODO Auto-generated method stub
		return orderDao.getAllNewOrders();
	}

}
