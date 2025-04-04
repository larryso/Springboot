package com.larry.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.larry.pojo.Product;

@RestController
public class ProductServiceController {
	private static Map<String, Product> productRepo = new HashMap<String, Product>();
	static {
		Product honey = new Product();
	      honey.setId("1");
	      honey.setName("Honey");
	      productRepo.put(honey.getId(), honey);      
	      Product almond = new Product();
	      almond.setId("2");
	      almond.setName("Almond");
	      productRepo.put(almond.getId(), almond);

	}
	@RequestMapping(value="/products")
	public ResponseEntity<Object> getProducts(){
		System.out.println("display products...");
		return new ResponseEntity<Object>(productRepo.values(), HttpStatus.OK);
	}

	@RequestMapping(value="/index")
	public String index(){
		System.out.println("display products...");
		return "index";
	}
}
