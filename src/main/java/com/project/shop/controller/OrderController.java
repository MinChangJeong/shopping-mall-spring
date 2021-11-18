package com.project.shop.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.shop.model.Order;
import com.project.shop.payload.ApiResponse;
import com.project.shop.security.CurrentUser;
import com.project.shop.security.UserPrincipal;
import com.project.shop.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/createOrder")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createOrder(@RequestParam(value = "productId") Long productId,
					@CurrentUser UserPrincipal currentUser) {
		
		Order Order = orderService.createOrder(productId, currentUser);
		
		// Rest API를 구현하는 과정에서 특정값을 포함한 URI를 전달하는 상황
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{orderId}")
				.buildAndExpand(Order.getId())
				.toUri();
		
		return ResponseEntity.created(location)
				.body(new ApiResponse(true, "Order Created Successfully"));
	}	
}
