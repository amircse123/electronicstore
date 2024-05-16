package com.electronicstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicstore.entity.Product;

public interface ProductRepo extends JpaRepository<Product, String>{

	Page<Product> findByTitleContaining(String subTitle,Pageable pageable);
	
	Page<Product> findByLiveTrue(Pageable pageable);
	
	
}
