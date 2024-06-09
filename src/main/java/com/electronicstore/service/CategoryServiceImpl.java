package com.electronicstore.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.electronicstore.dto.CategoryDto;
import com.electronicstore.entity.Category;
import com.electronicstore.exceptions.ResourceNotFoundException;
import com.electronicstore.repository.CategoryRepo;
import com.electronicstore.utility.PageableResponse;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto create(CategoryDto categoryDto) {
		Category entity = mapper.map(categoryDto, Category.class);
		
		String id = UUID.randomUUID().toString();
		entity.setCategoryId(id);
		Category savedEntity = repo.save(entity);

		return mapper.map(savedEntity, CategoryDto.class);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto, String categoryId) {

		Category entity = repo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not found with given Id...!!"));
		
		
		
		entity.setCategoryId(categoryId);
		entity.setTitle(categoryDto.getTitle());
		entity.setCoverImage(categoryDto.getCoverImage());
		entity.setDescription(categoryDto.getDescription());

		Category savedEntity = repo.save(entity);

		return mapper.map(savedEntity, CategoryDto.class);
	}

	@Override
	public void delete(String categoryId) {
		Category entity = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException());
		repo.delete(entity);

	}

	@Override
	public PageableResponse<CategoryDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Category> page = repo.findAll(pageable);
		List<Category> categories = page.getContent();

		List<CategoryDto> dtoList = categories.stream().map(s -> mapper.map(s, CategoryDto.class))
				.collect(Collectors.toList());

		PageableResponse<CategoryDto> pr = PageableResponse.<CategoryDto>builder().pageNumber(page.getNumber())
				.pageSize(pageSize).totalElements(page.getTotalElements()).totalPages(page.getTotalPages())
				.isLastPage(page.isLast()).content(dtoList).build();

		/*
		 * PageableResponse<CategoryDto> pr=new PageableResponse<>();
		 * pr.setContent(dtoList); pr.setPageNumber(page.getNumber());
		 * pr.setPageSize(page.getSize()); pr.setLastPage(page.isLast());
		 * pr.setTotalPages(page.getTotalPages());
		 * pr.setTotalElements(page.getTotalElements());
		 */
		return pr;
	}

	@Override
	public CategoryDto get(String categoryId) {
		Category entity = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException());

		return mapper.map(entity, CategoryDto.class);

	}

	@Override
	public List<CategoryDto> searchCategoryByKeyword(String keyword) {

		List<Category> searchByKeyword = repo.searchByKeyword(keyword);
		List<CategoryDto> dtoList = searchByKeyword.stream().map(s -> mapper.map(s, CategoryDto.class))
				.collect(Collectors.toList());
		return dtoList;
	}

}
