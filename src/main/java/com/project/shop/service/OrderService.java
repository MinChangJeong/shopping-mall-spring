package com.project.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shop.model.Order;
import com.project.shop.model.Product;
import com.project.shop.model.User;
import com.project.shop.repository.OrderRepository;
import com.project.shop.repository.ProductRepository;
import com.project.shop.repository.UserRepository;
import com.project.shop.security.UserPrincipal;

@Service
public class OrderService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


	public Order createOrder(Long productId, UserPrincipal currentUser) {
		Order order = new Order();
		
		User user = userRepository.getOne(currentUser.getId());
		
		Product product = productRepository.getOne(productId);
		
		order.setRecipientName(user.getUsername());
		order.setOrderPrice(product.getProductPrice());
		order.setOrderAddress(user.getAddress());
		order.setOrderState("배송준비중");
		order.setRefundState(true);
		
		order.setUser(user);
		order.setProduct(product);
		
		return orderRepository.save(order);
		
	}
}
