package com.larry.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_rec_cat")
public class ProductCatPO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@OneToMany(mappedBy = "productCat", targetEntity = ProductPO.class, cascade = CascadeType.ALL)
	private Set<ProductPO> products = new HashSet<ProductPO>();
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String price;
	@Column
	private String image_url;
}
