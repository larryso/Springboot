package com.larry.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.larry.entity.ProductPO;
import com.larry.service.ProductService;
@Component
@Order(value=1)
public class CacheServiceUtil implements CommandLineRunner{
	
	@Autowired
	private ProductService productService;

	public void run(String... args) throws Exception {
		List<ProductPO> productList = productService.loadAllProducts();
//		for(ProductPO p: productList) {
//			System.out.println(p.getName());
//		}
		EhCacheUtil.put(EhCacheUtil.CACHE_KEY_PROD, productList);
	}

}