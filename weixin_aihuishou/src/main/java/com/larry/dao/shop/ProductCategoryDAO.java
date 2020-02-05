package com.larry.dao.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import com.larry.entity.shop.ProductCategory;

public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Integer>{

}
