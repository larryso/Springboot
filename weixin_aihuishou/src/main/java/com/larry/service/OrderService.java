package com.larry.service;

import java.util.List;

import com.larry.bean.PaperRecFormBean;
import com.larry.entity.OrderPO;
import com.larry.entity.UserPO;

public interface OrderService {
	public PaperRecFormBean preparePaperRecForm();
	public OrderPO save(OrderPO order);
	public List<OrderPO> getAllOrdersByUserID(int userID);
}
