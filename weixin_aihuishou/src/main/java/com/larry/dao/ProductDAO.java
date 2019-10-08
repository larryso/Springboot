package com.larry.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.larry.entity.ProductPO;
import com.larry.entity.UserPO;

public interface ProductDAO extends JpaRepository<ProductPO, Integer>{

}
