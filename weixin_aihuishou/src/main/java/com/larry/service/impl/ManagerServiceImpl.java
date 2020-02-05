package com.larry.service.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.consts.OrderConst;
import com.larry.dao.ManagerDAO;
import com.larry.dao.ManagerTaskDAO;
import com.larry.dao.OrderDAO;
import com.larry.entity.ManagerPO;
import com.larry.entity.OrderPO;
import com.larry.entity.TaskPO;
import com.larry.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	private ManagerDAO managerDao;
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private ManagerTaskDAO taskDao;

	public ManagerPO save(ManagerPO manager) {
		// TODO Auto-generated method stub
		return managerDao.save(manager);
	}

	public ManagerPO findManagerByName(String username) {
		// TODO Auto-generated method stub
		return managerDao.findManagerByName(username);
	}

	@Transactional(rollbackOn = Exception.class)
	public OrderPO pickOrder(int orderId, ManagerPO manager) {
		// TODO Auto-generated method stub
		OrderPO order = orderDao.getOne(orderId);
		order.setStatus(OrderConst.PROCESSING_ORDER);
		order = orderDao.save(order);
		TaskPO task = new TaskPO();
		task.setManager(manager);
		task.setOrder(order);
		task.setTask_take_date(new Date(new java.util.Date().getTime()));
		taskDao.save(task);
		return order;
	}

	public List<TaskPO> getTasksByManagerId(int managerId) {
		// TODO Auto-generated method stub
		return taskDao.getTasksByManagerId(managerId);
	}

	public TaskPO getTaskByOrderId(int orderID) {
		// TODO Auto-generated method stub
		return taskDao.getTasksByOrderId(orderID);
	}

}
