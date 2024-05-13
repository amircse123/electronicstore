package com.electronicstore.service;

import java.util.List;

import com.electronicstore.dto.ProductDto;

public interface ProductService {

	//create
	ProductDto createProduct(ProductDto productDto);
	
	
	//update
	ProductDto updateProduct(ProductDto productDto, String id);
	
	//delete
	void deleteProduct(ProductDto productDto);
	
	//single product
	ProductDto getSingleProduct(String id);
	
	//All product
	List<ProductDto> getAllProduct();
	
	//get All Live
	List<ProductDto> getAllLiveProduct();
	
	//search Product
	List<ProductDto> searchProductByTitle(String subTitle);
}
