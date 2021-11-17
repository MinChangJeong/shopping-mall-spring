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
import com.project.shop.model.Product;
import com.project.shop.payload.PagedResponse;
import com.project.shop.payload.ProductResponse;
import com.project.shop.repository.ProductRepository;
import com.project.shop.security.UserPrincipal;
import com.project.shop.util.AppConstants;
import com.project.shop.util.ModelMapper;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	public PagedResponse<ProductResponse> getAllProducts(UserPrincipal currentUser, int page, int size) {
		validatePageNumberAndSize(page, size);
		
		//Retrieve product
		Pageable pagealbe = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
				
		System.out.println(pagealbe);
		
		Page<Product> products = productRepository.findAll(pagealbe);
		
		if(products.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), products.getNumber(),
					products.getSize(), products.getTotalElements(), products.getTotalPages(), products.isLast());			
		}
		
		List<Long> productIds = products.map(Product::getId).getContent();
		
		List<ProductResponse> productResponse = products.map(product -> {
			return ModelMapper.mapProductToProductResponse(product);
		}).getContent();
		
		return new PagedResponse<>(productResponse, products.getNumber(),
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
