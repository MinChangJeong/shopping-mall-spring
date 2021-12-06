package com.project.shop.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.shop.exception.BadRequestException;
import com.project.shop.model.Order;
import com.project.shop.model.Product;
import com.project.shop.model.User;
import com.project.shop.payload.OrderResponse;
import com.project.shop.payload.PagedResponse;
import com.project.shop.payload.ProductResponse;
import com.project.shop.repository.OrderRepository;
import com.project.shop.repository.ProductRepository;
import com.project.shop.repository.UserRepository;
import com.project.shop.security.UserPrincipal;
import com.project.shop.util.AppConstants;
import com.project.shop.util.OrderModelMapper;
import com.project.shop.util.ProductModelMapper;

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
	
	public PagedResponse<OrderResponse> getAllOrderList(UserPrincipal currentUser, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
		
		Page<Order> orders = orderRepository.findOrderByUserId(currentUser.getId(), pageable);
		
		if(orders.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), orders.getNumber(), orders.getSize(), 
					orders.getTotalElements(), orders.getTotalPages(), orders.isLast());
		}
		
		List<OrderResponse> orderResponses = orders.map(order -> {
			return OrderModelMapper.mapOrderToOrderResponse(order);
		}).getContent();
		
		return new PagedResponse<>(orderResponses, orders.getNumber(),
				orders.getSize(), orders.getTotalElements(), orders.getTotalPages(), orders.isLast());
		
	}
	
	// 주문한 상품들중 중복을 제거한 경우 
	public PagedResponse<ProductResponse> getAllOrderedProducts(UserPrincipal currentUser, int page, int size) {
		validatePageNumberAndSize(page, size);
		
		List<Long> orderIds = orderRepository.findOrderedProductIdByUserId(currentUser.getId());
		
		Pageable pagealbe = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

		Page<Product> products = productRepository.findProductsByOrderIds(orderIds, pagealbe);
	
		if(products.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), products.getNumber(),
					products.getSize(), products.getTotalElements(), products.getTotalPages(), products.isLast());			
		}
		
		List<Long> productIds = products.map(Product::getId).getContent();
		
		List<ProductResponse> productResponses = products.map(product -> {
			return ProductModelMapper.mapProductToProductResponse(product);
		}).getContent();
		
		return new PagedResponse<>(productResponses, products.getNumber(),
				products.getSize(), products.getTotalElements(), products.getTotalPages(), products.isLast());
	}
	
	private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
