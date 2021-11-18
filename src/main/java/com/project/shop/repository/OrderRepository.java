package com.project.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.shop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	Optional<Order> findById(Long orderId);
	
	List<Order> findByIdIn(List<Long> orderIds);
	
}
