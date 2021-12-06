package com.project.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.shop.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	Optional<Review> findById(Long reviewId);
	
	List<Review> findByIdIn(List<Long> reviewIds);
	
	@Query("SELECT r FROM Review r WHERE r.product.id = :productId")
	List<Review> findByProductId(@Param("productId") Long productId);
	
}
