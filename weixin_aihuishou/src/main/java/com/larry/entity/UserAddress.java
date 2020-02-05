package com.larry.entity;

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
@Table(name = "T_USER_ADDRESS")
public class UserAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(targetEntity = UserPO.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "auth_user_id", nullable = false, referencedColumnName = "id")
	private UserPO user;
	@Column(length = 100)
	private String address;
	@Column
	private String tel;
	@Column
	private String name;
	@Column
	private int set_defult;

}
