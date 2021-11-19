package com.project.shop.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.shop.model.Product;
import com.project.shop.payload.ApiResponse;
import com.project.shop.payload.PagedResponse;
import com.project.shop.payload.ProductRequest;
import com.project.shop.payload.ProductResponse;
import com.project.shop.repository.ProductRepository;
import com.project.shop.security.CurrentUser;
import com.project.shop.security.UserPrincipal;
import com.project.shop.service.ProductService;
import com.project.shop.util.AppConstants;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;

	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("/explore")
	public PagedResponse<ProductResponse> getAllProducts(@CurrentUser UserPrincipal currentUser,
            									@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            									@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return productService.getAllProducts(currentUser, page, size);
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerProduct(@Valid @RequestBody ProductRequest productRequest) {	
		Product product = productService.registerProduct(productRequest);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{productId}")
				.buildAndExpand(product.getId())
				.toUri();
		
		return ResponseEntity.created(location)
				.body(new ApiResponse(true, "Prodcut Registerd Successfully"));
	}
	
}
