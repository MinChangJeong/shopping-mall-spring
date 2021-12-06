package com.project.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shop.model.Product;
import com.project.shop.model.Review;
import com.project.shop.model.User;
import com.project.shop.payload.ReviewRequest;
import com.project.shop.repository.ProductRepository;
import com.project.shop.repository.ReviewRepository;
import com.project.shop.repository.UserRepository;
import com.project.shop.security.UserPrincipal;

@Service
public class ReviewService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	
	public Review createReview(ReviewRequest reviewRequest, UserPrincipal currentUser) {
		Review review = new Review();
		
		User user = userRepository.getOne(currentUser.getId());
		
		Product product = productRepository.getOne(reviewRequest.getProductId());
		
		review.setAuthorName(user.getUsername());
		review.setReviewContent(reviewRequest.getReviewContent());
		review.setReviewScore(reviewRequest.getReviewScore());
		
		review.setUser(user);
		review.setProduct(product);
		
		return reviewRepository.save(review);
	}
}
