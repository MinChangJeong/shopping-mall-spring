package com.project.shop.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.shop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findById(Long productId);
	
	List<Product> findByIdIn(List<Long> productIds);
	
//	@Query("SELECT p FROM Product p WHERE p.id In "
//			+ "(SELECT DISTINCT o.product.id FROM Order o, User u WHERE o.user.id = u.id = :userId")
//	Page<Product> findOrderedProductByUserId(@Param("orderIds") List<Long> orderIds, 
//									@Param("userId") Long userId,
//									Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.id In :orderIds")
	Page<Product> findProductsByOrderIds(@Param("orderIds") List<Long> orderIds, Pageable pageable);
	
}
