package com.electronicstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electronicstore.dto.CategoryDto;
import com.electronicstore.dto.ProductDto;
import com.electronicstore.service.CategoryService;
import com.electronicstore.service.ProductService;
import com.electronicstore.utility.ApiResponseMessage;
import com.electronicstore.utility.PageableResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@Autowired
	private ProductService productService;

	// create
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {

		CategoryDto dto = service.create(categoryDto);

		return new ResponseEntity<CategoryDto>(dto, HttpStatus.CREATED);

	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto, @PathVariable String id) {

		CategoryDto categoryDto = service.update(dto, id);

		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.ACCEPTED);

	}

	// getAll
	@GetMapping("/getall")
	public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir) {
		PageableResponse<CategoryDto> pageableResponse = service.getAll(pageNo, pageSize, sortBy, sortDir);

		return new ResponseEntity<PageableResponse<CategoryDto>>(pageableResponse, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable("id") String categoryId) {
		service.delete(categoryId);

		ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder()
				.message("category is deleted successfully...!!").success(true).status(HttpStatus.FORBIDDEN).build();

		return new ResponseEntity<ApiResponseMessage>(apiResponseMessage, HttpStatus.FORBIDDEN);

	}

	// getbyId
	@GetMapping("/get/{id}")
	public ResponseEntity<CategoryDto> getcategoryById(@PathVariable("id") String id) {
		CategoryDto categoryDto = service.get(id);

		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.FOUND);

	}

	@GetMapping("/search/{key}")
	public ResponseEntity<List<CategoryDto>> searchByKeyword(@PathVariable String key) {
		List<CategoryDto> searchedList = service.searchCategoryByKeyword(key);

		return new ResponseEntity<List<CategoryDto>>(searchedList, HttpStatus.OK);
	}

	// create Product with a category

	@PostMapping("/{categoryId}/products")
	public ResponseEntity<ProductDto> createProductWithCategory(@PathVariable("categoryId") String categoryId,
			@RequestBody ProductDto productDto) {

		ProductDto createProductWithCategory = productService.createProductWithCategory(productDto, categoryId);
		return new ResponseEntity<ProductDto>(createProductWithCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}/products/{productId}")
	public ResponseEntity<ProductDto> updateCategoryOfProduct(@PathVariable String categoryId,
			@PathVariable String productId) {

		ProductDto updateCategoryOfProduct = productService.updateCategoryOfProduct(categoryId, productId);

		return new ResponseEntity<>(updateCategoryOfProduct, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{categoryId}/products")
	public ResponseEntity<PageableResponse<ProductDto>> getProductsOfCategory(@PathVariable String categoryId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir) {

		PageableResponse<ProductDto> allProductsOfCategory = productService.getAllProductsOfCategory(categoryId, pageNo,
				pageSize, sortBy, sortDir);

		return new ResponseEntity<>(allProductsOfCategory, HttpStatus.FOUND);
	}
}
