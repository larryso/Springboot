package com.larry.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.larry.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
