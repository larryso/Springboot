package com.larry.entity.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_shop_product")
public class ShopProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(targetEntity = ProductCategory.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
	private ProductCategory category;
	@Column
	private String product_name;
	@Column
	private String point_price;
	@Column
	private String product_image;
	@Column
	private String product_details1;
	@Column
	private String product_details2;
	@Column
	private String product_details3;
	@Column
	private String proudct_brand;
	@Column
	private String product_model;
	@Column
	private String product_specification;
	@Column
	private String product_material;
	@Column
	private String produce_location;
	@Column
	private int star_flag;
}
