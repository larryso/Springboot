package com.larry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.dao.shop.ProductCategoryDAO;
import com.larry.dao.shop.ShopProductDAO;
import com.larry.entity.shop.ProductCategory;
import com.larry.entity.shop.ShopProduct;
import com.larry.service.ShopService;
@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopProductDAO shopProductDao;
	@Autowired
	private ProductCategoryDAO productCategoryDao;

	public List<ProductCategory> loadAllCategory() {
		// TODO Auto-generated method stub
		return productCategoryDao.findAll();
	}

	public List<ShopProduct> loadAllProducts() {
		// TODO Auto-generated method stub
		return shopProductDao.findAll();
	}

}
