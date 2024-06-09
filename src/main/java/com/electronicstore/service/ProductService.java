package com.electronicstore.service;

import org.springframework.data.domain.Pageable;

import com.electronicstore.dto.ProductDto;
import com.electronicstore.entity.Category;
import com.electronicstore.utility.PageableResponse;

public interface ProductService {

	// create
	ProductDto createProduct(ProductDto productDto);

	// update
	ProductDto updateProduct(ProductDto productDto, String id);

	// delete
	void deleteProduct(String id);

	// single product
	ProductDto getSingleProduct(String id);

	// All product
	PageableResponse<ProductDto> getAllProduct(int pageNo, int pageSize, String sortBy, String sortDir);

	// get All Live
	PageableResponse<ProductDto> getAllLiveProduct(int pageNo, int pageSize, String sortBy, String sortDir);

	// search Product
	PageableResponse<ProductDto> searchProductByTitle(String subTitle, int pageNo, int pageSize, String sortBy,
			String sortDir);
	
	//create product with Category
	public ProductDto createProductWithCategory(ProductDto productDto,String categoryId);
	
	//update category of product
	ProductDto updateCategoryOfProduct(String categoryId,String productId);
	
	//get all products of a category
	PageableResponse<ProductDto> getAllProductsOfCategory(String categoryId,Integer pageNo,Integer pageSize,String sortBy,String sortDir);
}
