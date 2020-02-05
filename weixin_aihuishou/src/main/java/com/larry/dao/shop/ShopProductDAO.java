package com.larry.dao.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import com.larry.entity.shop.ShopProduct;

public interface ShopProductDAO extends JpaRepository<ShopProduct, Integer>{

}
