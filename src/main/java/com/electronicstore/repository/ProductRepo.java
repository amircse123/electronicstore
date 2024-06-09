package com.electronicstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicstore.entity.Category;
import com.electronicstore.entity.Product;
import com.electronicstore.utility.PageableResponse;

public interface ProductRepo extends JpaRepository<Product, String>{

	Page<Product> findByTitleContaining(String subTitle,Pageable pageable);
	
	Page<Product> findByLiveTrue(Pageable pageable);
	
	//find by category
	Page<Product> findByCategory(Category category,Pageable pageable);
	
	
}
