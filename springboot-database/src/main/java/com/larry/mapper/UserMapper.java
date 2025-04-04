package com.larry.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import com.larry.entity.User;

public interface UserMapper {
	@Select("SELECT * FROM USER WHERE NAME = #{name}")
	@Cacheable
	User findByName(@Param("name") String name);

	@Insert("INSERT INTO user (NAME, AGE, id) VALUES(#{name},#{age},#{id})")
	public void createUser(@Param("name") String name, @Param("age") Integer age, @Param("id") Integer id);
}
