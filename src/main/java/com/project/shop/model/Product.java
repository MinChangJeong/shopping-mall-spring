package com.project.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.project.shop.model.audit.DateAudit;

@Entity
@Table(name="products")
public class Product extends DateAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	private String productExplain;
	

	public Product() {}	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public String getProductExplain() {
		return productExplain;
	}
	
	public void setProductExplain(String productExplain) {
		this.productExplain = productExplain;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
