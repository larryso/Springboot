package com.larry.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String price;
	private String description;

}