package com.project.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shop.repository.ProductRepository;
import com.project.shop.security.CurrentUser;
import com.project.shop.security.UserPrincipal;
import com.project.shop.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;

	
//	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
//	
//	@GetMapping("/explore")
//	public PagedResponse<ProductResponse> getAllPosts(@CurrentUser UserPrincipal currentUser,
//            									@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//            									@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//		return productService.getAllProducts(currentUser, page, size);
//	}
	
}
