package com.electronicstore.service;

import java.util.List;

import com.electronicstore.dto.CategoryDto;
import com.electronicstore.utility.PageableResponse;

public interface CategoryService {

	//create
	CategoryDto create(CategoryDto categoryDto);
	
	
	//update
	CategoryDto update(CategoryDto categoryDto,String categoryId);
	
	//delete
	void delete(String categoryId);
	
	//get all
	PageableResponse<CategoryDto> getAll(int pageNo,int pageSize,String
			sortBy,String sortDir);
	//get Single category
	CategoryDto get(String categoryId);
	//search
	List<CategoryDto> searchCategoryByKeyword(String keyword);
	
}
