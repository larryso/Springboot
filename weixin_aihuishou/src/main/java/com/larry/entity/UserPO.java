package com.larry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="AUTH_USERS")
public class UserPO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(length=100)
	private String weixin_openId;
	@Column(length=100)
	private int weixin_subscribe;
	@Column
	private String weixin_subscribeTime;
	@Column
	private String weixin_nickname;
	@Column
	private int sex;
	@Column
	private String country;
	@Column
	private String province;
	@Column
	private String city;
	@Column
	private String language;
}
