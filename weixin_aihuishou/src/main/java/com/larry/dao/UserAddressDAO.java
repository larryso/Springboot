package com.larry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.larry.entity.UserAddress;


public interface UserAddressDAO extends JpaRepository<UserAddress, Integer>{
	@Query(value = "select * from t_user_address u where u.auth_user_id =:id", nativeQuery = true)
	public List<UserAddress> findAllAddressByUser(@Param("id")int userId);
	@Query(value = "select * from t_user_address u where u.id =:id", nativeQuery = true)
	public UserAddress findAddressById(@Param("id")int id);
}
