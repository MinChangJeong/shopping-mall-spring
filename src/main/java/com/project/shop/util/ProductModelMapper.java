package com.project.shop.util;

import com.project.shop.model.Product;
import com.project.shop.payload.ProductResponse;

public class ProductModelMapper {
	public static ProductResponse mapProductToProductResponse(Product product) {
		
		ProductResponse productResponse = new ProductResponse();
		
		productResponse.setProductId(product.getId());
		productResponse.setProductName(product.getProductName());
		productResponse.setProductPrice(product.getProductPrice());
		productResponse.setProductExplain(product.getProductExplain());
		productResponse.setStack(product.getStock());
		productResponse.setCategory(product.getCategory());
		
		return productResponse;
	}
	
}