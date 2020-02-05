package com.larry.service;

import java.util.List;

import com.larry.entity.shop.ProductCategory;
import com.larry.entity.shop.ShopProduct;

public interface ShopService {
	public List<ProductCategory> loadAllCategory();
	public List<ShopProduct> loadAllProducts();
}
