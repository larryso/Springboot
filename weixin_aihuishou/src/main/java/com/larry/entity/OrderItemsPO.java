package com.larry.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Entity
@Table(name = "t_order_item")
public class OrderItemsPO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 @Column
	 private int id;
	 @ManyToOne(targetEntity=OrderPO.class,fetch = FetchType.EAGER)
	 @JoinColumn(name="order_id",nullable=false,referencedColumnName="id")
	 private OrderPO order;
	 @Column
	 private int product_id;
	 @Column
	 private String product_name;
	 @Column
	 private BigDecimal price;
	 @Column
	 private String description;
}
