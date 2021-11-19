package com.project.shop.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.shop.model.Review;
import com.project.shop.payload.ApiResponse;
import com.project.shop.payload.ReviewRequest;
import com.project.shop.security.CurrentUser;
import com.project.shop.security.UserPrincipal;
import com.project.shop.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/createReview")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createReview(@Valid @RequestBody ReviewRequest reviewReqeust, Long productId,
					@CurrentUser UserPrincipal currentUser) {		
		Review review = reviewService.createReview(reviewReqeust, currentUser);
		
		// Rest API를 구현하는 과정에서 특정값을 포함한 URI를 전달하는 상황
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{reviewId}")
				.buildAndExpand(review.getId())
				.toUri();
		
		return ResponseEntity.created(location)
				.body(new ApiResponse(true, "Review Created Successfully"));
	}	
	
}
