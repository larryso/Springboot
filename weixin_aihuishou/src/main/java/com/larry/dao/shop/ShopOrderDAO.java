package com.larry.dao.shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.larry.entity.shop.ShopOrder;


public interface ShopOrderDAO extends JpaRepository<ShopOrder, Integer>{
	@Query(value = "select * from t_shop_order o where o.user_id=:user_id", nativeQuery = true)
	public List<ShopOrder> getAllOrdersByUserId(@Param("user_id")int userId);
}
