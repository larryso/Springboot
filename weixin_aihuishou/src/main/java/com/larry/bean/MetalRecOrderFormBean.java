package com.larry.bean;

import lombok.Data;

@Data
public class MetalRecOrderFormBean {
	private String[] productId;
	private String collectingDate;
	//private String city;
	//private String detail_address;
	//private String tel;
	private int address_id;
	private String comments;
}
