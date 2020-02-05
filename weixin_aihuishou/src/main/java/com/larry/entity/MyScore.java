package com.larry.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_user_scores")
public class MyScore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer score_id;
	@Column
	private Integer user_id;
	@Column
	private Integer score;
	@Column
	private Integer score_type; //0:income 1:outcome
	@Column
	private Date create_time ;
	
}
