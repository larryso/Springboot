package com.larry.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, ProductPO> productMap = new HashMap<String, ProductPO>();
		
		for(ProductPO p: productList) {
			String prodID = Integer.toString(p.getId());
			productMap.put(prodID, p);
		}
		EhCacheUtil.put(EhCacheUtil.CACHE_KEY_PROD, productList);
		EhCacheUtil.put(EhCacheUtil.CACHE_KEY_HASHPROD, productMap);
	}

}
