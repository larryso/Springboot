package com.larry.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.larry.entity.ProductPO;
import com.larry.entity.shop.ProductCategory;
import com.larry.entity.shop.ShopProduct;
import com.larry.service.ProductService;
import com.larry.service.ShopService;
@Component
@Order(value=1)
public class CacheServiceUtil implements CommandLineRunner{
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ShopService shopService;

	public void run(String... args) throws Exception {
		List<ProductPO> productList = productService.loadAllProducts();
		Map<String, ProductPO> productMap = new HashMap<String, ProductPO>();
		
		for(ProductPO p: productList) {
			String prodID = Integer.toString(p.getId());
			productMap.put(prodID, p);
		}
		EhCacheUtil.put(EhCacheUtil.CACHE_KEY_PROD, productList);
		EhCacheUtil.put(EhCacheUtil.CACHE_KEY_HASHPROD, productMap);
		List<ProductCategory> productCategory = shopService.loadAllCategory();
		EhCacheUtil.put(EhCacheUtil.CACHE_KEY_SHOP_PROD_CAT, productCategory);
		List<ShopProduct> shopProdList = shopService.loadAllProducts();
		EhCacheUtil.put(EhCacheUtil.CACHE_KEY_SHOP_PROD, shopProdList);
		Map<String, ShopProduct> prodMap = new HashMap<String, ShopProduct>();
		for(ShopProduct p : shopProdList) {
			prodMap.put(p.getId().toString(), p);
		}
		EhCacheUtil.put(EhCacheUtil.CACHE_KEY_SHOP_PROD_MAP, prodMap);
	}

}
