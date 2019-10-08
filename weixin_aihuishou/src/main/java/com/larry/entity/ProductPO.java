package com.larry.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "t_rec")
public class ProductPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 @Column
	 private int id;
	 @ManyToOne(targetEntity=ProductCatPO.class,fetch = FetchType.EAGER)
	 @JoinColumn(name="cat_id",nullable=false,referencedColumnName="id")
	 private ProductCatPO productCat;
	 @Column
	 private String name;
	 @Column
	 private String description;
	 @Column
	 private String price;
	 
	 

}
