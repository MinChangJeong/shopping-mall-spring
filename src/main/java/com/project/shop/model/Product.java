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
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 80)
	private int orderPrice;
	
	@NotBlank
	@Size(max = 80)
	private String orderAddress;
	
	@NotBlank
	@Size(max = 80)
	private String recipientName;
	
	@NotBlank
	@Size(max = 80)
	private String orderState;
	
	@NotBlank
	@Size(max = 80)
	private boolean refundState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public boolean isRefundState() {
		return refundState;
	}

	public void setRefundState(boolean refundState) {
		this.refundState = refundState;
	}
	
	
}
