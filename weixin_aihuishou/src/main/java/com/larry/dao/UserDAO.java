package com.larry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.larry.entity.UserPO;

public interface UserDAO extends JpaRepository<UserPO, Integer>{
	@Query(value = "select * from auth_users u where u.weixin_open_id =:id", nativeQuery = true)

	public List<UserPO> findAuthUserByWeixinOpenID(@Param("id")String weixinOpenId);
}
