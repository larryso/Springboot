package com.larry.entity;

import java.sql.Date;

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
@Table(name = "t_manager_tasks")
public class TaskPO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(targetEntity = ManagerPO.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id", nullable = false, referencedColumnName = "id")
	private ManagerPO manager;
	@OneToOne(targetEntity=OrderPO.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id")
	private OrderPO order;
	@Column
	private Date task_take_date ;
	@Column
	private Date task_complete_date ;

}
