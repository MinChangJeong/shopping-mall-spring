package com.project.shop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.project.shop.model.audit.DateAudit;


@Entity
@Table(name = "products")
public class Product extends DateAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;
	
	@NotBlank
	@Size(max = 40)
	private String productName;
	
	@NotBlank
	@Size(max = 40)
	private int productPrice;
	
	@NotBlank
	@Size(max = 40)
	private int stock;
	
	@NotBlank
	@Size(max = 20)
	private String category;
	
	@NotBlank
	@Size(max = 40)
	private String explain;

	public Product() {}	
	
	public Product(Long product_id, String productName, int productPrice,
			int stock, String category, String explain) {
		super();
		this.product_id = product_id;
		this.productName = productName;
		this.productPrice = productPrice;
		this.stock = stock;
		this.category = category;
		this.explain = explain;
	}


	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
