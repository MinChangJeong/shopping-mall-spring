package com.project.shop.util;

import com.project.shop.model.Order;
import com.project.shop.payload.OrderResponse;

public class OrderModelMapper {
	public static OrderResponse mapOrderToOrderResponse(Order order) {
		OrderResponse orderResponse = new OrderResponse();
		
		orderResponse.setOrderId(order.getId());
		orderResponse.setOrderPrice(order.getOrderPrice());
		orderResponse.setOrderAddress(order.getOrderAddress());
		orderResponse.setOrderState(order.getOrderState());
		orderResponse.setRecipientName(order.getRecipientName());
		orderResponse.setRefundState(order.isRefundState());
		orderResponse.setCreatedAt(order.getCreatedAt());
		
		return orderResponse;
	}
}
