package com.larry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.dao.ProductDAO;
import com.larry.entity.ProductPO;
import com.larry.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDao;

	public List<ProductPO> loadAllProducts() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

}
