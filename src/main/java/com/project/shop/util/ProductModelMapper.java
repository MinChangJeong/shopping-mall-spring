package com.project.shop.util;

import java.util.List;
import java.util.stream.Collectors;

import com.project.shop.model.Product;
import com.project.shop.payload.ProductResponse;
import com.project.shop.payload.ReviewResponse;

public class ProductModelMapper {
	
	public static ProductResponse mapProductToProductResponse(Product product) {
		
		ProductResponse productResponse = new ProductResponse();
		
		productResponse.setProductId(product.getId());
		productResponse.setProductName(product.getProductName());
		productResponse.setProductPrice(product.getProductPrice());
		productResponse.setProductExplain(product.getProductExplain());
		productResponse.setStack(product.getStock());
		productResponse.setCategory(product.getCategory());
				
		List<ReviewResponse> reviewResponses = product.getReviews().stream().map(review -> {
			ReviewResponse reviewResponse = new ReviewResponse();
			
			reviewResponse.setReviewId(review.getId());
			reviewResponse.setReviewScore(review.getReviewScore());
			reviewResponse.setReviewContent(review.getReviewContent());
			
			return reviewResponse;
		}).collect(Collectors.toList());
		
		productResponse.setReviews(reviewResponses);
		
		return productResponse;
	}
	
}