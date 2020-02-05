package com.larry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.larry.entity.OrderPO;
import com.larry.entity.ProductPO;
import com.larry.entity.UserPO;

public interface OrderDAO extends JpaRepository<OrderPO, Integer>{
	
	@Query(value = "select * from t_order o where o.user_id=:id", nativeQuery = true)
	public List<OrderPO> findOrdersByUserID(@Param("id")int userId);
	@Query(value = "select * from t_order o where o.id=:id", nativeQuery = true)
	public OrderPO findOrdersByID(@Param("id")int id);
	@Query(value = "select * from t_order o where o.status=0", nativeQuery = true)
	public List<OrderPO> getAllNewOrders();
	
}
