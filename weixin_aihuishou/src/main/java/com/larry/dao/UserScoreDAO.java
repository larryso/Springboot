package com.larry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.larry.entity.MyScore;

public interface UserScoreDAO extends JpaRepository<MyScore, Integer>{
	@Query(value = "select * from t_user_scores s where s.user_id =:id", nativeQuery = true)
	public List<MyScore>getAllScoreByUserId(@Param("id")Integer userID);

}
