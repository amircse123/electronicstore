package com.electronicstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.electronicstore.dto.CategoryDto;
import com.electronicstore.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, String>{

	@Query("select c from Category c where c.title like %:key%")
	public List<Category> searchByKeyword(@Param("key") String keyword);
	
}
