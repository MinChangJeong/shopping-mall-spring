package com.project.shop.payload;

import java.util.List;

import com.project.shop.model.Review;

public class ProductResponse {

	private Long productId;
	
	private String productName;
	
	private int productPrice;
	
	private int stack;
	
	private String productExplain;
	
	private String category;
	
	private List<ReviewResponse> reviews;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public int getStack() {
		return stack;
	}
	public void setStack(int stack) {
		this.stack = stack;
	}

	public String getProductExplain() {
		return productExplain;
	}
	public void setProductExplain(String productExplain) {
		this.productExplain = productExplain;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<ReviewResponse> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewResponse> reviews) {
		this.reviews = reviews;
	}

	
}
