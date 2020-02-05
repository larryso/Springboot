package com.larry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.larry.entity.TaskPO;

public interface ManagerTaskDAO  extends JpaRepository<TaskPO, Integer>{
	@Query(value = "select * from t_manager_tasks t where t.manager_id=:manager_id", nativeQuery = true)
	public List<TaskPO> getTasksByManagerId(@Param("manager_id")int manager_id);
	@Query(value = "select * from t_manager_tasks t where t.order_id=:order_id", nativeQuery = true)
	public TaskPO getTasksByOrderId(@Param("order_id")int order_id);
}
