package com.larry.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Entity
@Table(name = "t_order")
public class OrderPO implements Serializable{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 @Column
	 private int id;
	 @Column
	 private String order_number ;
	 @Column
	 private int user_id;
	 @Column
	 private int address_id ;
	// @Column
	// private int rec_cat_id ;
	 @OneToOne(targetEntity=ProductCatPO.class, fetch = FetchType.EAGER)
	 @JoinColumn(name = "rec_cat_id", nullable = false, referencedColumnName = "id")
	 private ProductCatPO productCat ;
	 @Column
	 private String comments ;
	 @Column
	 private String rejected_reason ;
	 @Column
	 private int status;
	 @Column
	 private BigDecimal total_price;
	 @Column
	 private String start_date;
	 @Column
	 private Date end_date;
	 @Column
	 private Date created_date;
	 @OneToMany(mappedBy="order",targetEntity=OrderItemsPO.class,cascade=CascadeType.ALL)
	 private Set<OrderItemsPO> orderItems = new HashSet<OrderItemsPO>();
	 
}
