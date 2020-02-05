package com.larry.entity.shop;

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

import com.larry.entity.UserAddress;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_shop_order")
public class ShopOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int Id;
	@Column
	private String order_number;
	@Column
	private int user_id;
	@Column
	private int address_id;
	@Column
	private int status; //0: payed 1:not payed 2: delivering 3:confirmed reciption
	@Column
	private int total_price_score;
	@Column
	private String start_date;
	@Column
	private Date end_date;
	@Column
	private Date created_date;
	@OneToMany(mappedBy = "order", targetEntity = ShopOrderItem.class, cascade = CascadeType.ALL)
	private Set<ShopOrderItem> orderItems = new HashSet<ShopOrderItem>();
}
