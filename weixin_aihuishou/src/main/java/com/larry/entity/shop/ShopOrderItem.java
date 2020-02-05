package com.larry.entity.shop;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_shop_order_item")
public class ShopOrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id ;
	@ManyToOne(targetEntity = ShopOrder.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", nullable = false, referencedColumnName = "Id")
	private ShopOrder order;
	@OneToOne(targetEntity = ShopProduct.class, fetch = FetchType.EAGER)
	@JoinColumn(name="product_id",nullable = false,referencedColumnName="Id")
	private ShopProduct product;
	@Column
	private int price_score ;
	@Column
	private int number ;
	@Column
	private int description  ;
}
