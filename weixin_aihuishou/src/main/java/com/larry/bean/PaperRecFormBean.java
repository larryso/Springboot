package com.larry.bean;

import java.util.List;

import lombok.Data;
@Data
public class PaperRecFormBean {
	private String priceRange;
	private List<String> categray;
	private String weight;
	private String collectingTime;
	private String address;

}
