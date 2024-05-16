package com.electronicstore.service;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.electronicstore.dto.ProductDto;
import com.electronicstore.entity.Product;
import com.electronicstore.exceptions.ResourceNotFoundException;
import com.electronicstore.repository.ProductRepo;
import com.electronicstore.utility.Helper;
import com.electronicstore.utility.PageableResponse;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo productRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		String productId = UUID.randomUUID().toString();

		product.setProductId(productId);
		product.setAddedDate(new Date());
		Product productEntity = productRepo.save(product);

		return modelMapper.map(productEntity, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, String id) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with given Id is not Fount...!!"));

		product.setTitle(productDto.getTitle());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setQuantity(productDto.getQuantity());
		product.setLive(product.isLive());
		product.setStock(productDto.isStock());
		product.setDiscount(productDto.getDiscount());
		product.setImageName(productDto.getImageName());

		Product savedProduct = productRepo.save(product);

		return modelMapper.map(savedProduct, ProductDto.class);

	}

	@Override
	public void deleteProduct(String id) {
		Product prod = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with given id is not availble for deletion"));
		productRepo.delete(prod);

	}

	@Override
	public ProductDto getSingleProduct(String id) {
		Product prod = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with given id is not available"));

		return modelMapper.map(prod, ProductDto.class);
	}

	@Override
	public PageableResponse<ProductDto> getAllProduct(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
		Page<Product> page = productRepo.findAll(pageable);

		return Helper.getPageableResponse(page, ProductDto.class);
	}

	@Override
	public PageableResponse<ProductDto> getAllLiveProduct(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Product> prod = productRepo.findByLiveTrue(pageable);
		return Helper.getPageableResponse(prod, ProductDto.class);
	}

	@Override
	public PageableResponse<ProductDto> searchProductByTitle(String subTitle, int pageNo, int pageSize, String sortBy,
			String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Product> prod = productRepo.findByTitleContaining(subTitle, pageable);

		return Helper.getPageableResponse(prod, ProductDto.class);
	}

}
