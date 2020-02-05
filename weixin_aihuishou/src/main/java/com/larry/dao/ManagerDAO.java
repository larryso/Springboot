package com.larry.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.larry.entity.ManagerPO;

public interface ManagerDAO extends JpaRepository<ManagerPO, Integer>{
	@Query(value = "select * from t_manager m where m.user_name=:user_name", nativeQuery = true)
	public ManagerPO findManagerByName(@Param("user_name")String userName);
}
