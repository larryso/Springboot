package com.larry.service;

import java.util.List;

import com.larry.entity.ManagerPO;
import com.larry.entity.OrderPO;
import com.larry.entity.TaskPO;

public interface ManagerService {
	public ManagerPO save(ManagerPO manager);
	public ManagerPO findManagerByName(String username);
	public OrderPO pickOrder(int orderId, ManagerPO manager);
	public List<TaskPO> getTasksByManagerId(int managerId);
	public TaskPO getTaskByOrderId(int orderID);
}
