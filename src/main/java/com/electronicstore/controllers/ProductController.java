package com.electronicstore.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.electronicstore.dto.ProductDto;
import com.electronicstore.service.FileService;
import com.electronicstore.service.ProductService;
import com.electronicstore.utility.ImageResponse;
import com.electronicstore.utility.PageableResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@Value("${products.image.path}")
	private String imagePath;

	@Autowired
	private FileService fileService;

	// create
	@PostMapping("/create")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productdto) {
		ProductDto createProduct = productService.createProduct(productdto);
		return new ResponseEntity<ProductDto>(createProduct, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String id) {

		ProductDto updateProduct = productService.updateProduct(productDto, id);

		return new ResponseEntity<ProductDto>(updateProduct, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
		return null;
	}

	// get single
	@GetMapping("/get/{id}")
	public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String id) {
		ProductDto singleProduct = productService.getSingleProduct(id);
		return new ResponseEntity(singleProduct, HttpStatus.FOUND);
	}

	// get all
	@GetMapping("/getAll")
	public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
			@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir) {

		PageableResponse<ProductDto> allProduct = productService.getAllProduct(pageNo, pageSize, sortBy, sortDir);
		return new ResponseEntity(allProduct, HttpStatus.FOUND);
	}

	// get all live
	@GetMapping("/live")
	public ResponseEntity<PageableResponse<ProductDto>> getAllLiveProduct(
			@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "6", required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir) {

		PageableResponse<ProductDto> allProduct = productService.getAllLiveProduct(pageNo, pageSize, sortBy, sortDir);
		return new ResponseEntity(allProduct, HttpStatus.FOUND);
	}

	// search all
	@GetMapping("/search/{query}")
	public ResponseEntity<PageableResponse<ProductDto>> searchAllProduct(@PathVariable String query,
			@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "6", required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir) {

		PageableResponse<ProductDto> allProduct = productService.searchProductByTitle(query, pageNo, pageSize, sortBy,
				sortDir);
		return new ResponseEntity(allProduct, HttpStatus.FOUND);
	}

	// upload Image
	@PostMapping("/image/{id}")
	public ResponseEntity<ImageResponse> uploadProductImage(@RequestParam("productImage") MultipartFile file,
			@PathVariable("id") String productId) throws IOException {

		String uploadFileNameWithPath = fileService.uploadFile(file, imagePath);
		ProductDto productDto = productService.getSingleProduct(productId);
		productDto.setImageName(uploadFileNameWithPath);
		ProductDto updateProduct = productService.updateProduct(productDto, productId);

		ImageResponse imageResponse = ImageResponse.builder().imageName(updateProduct.getImageName())
				.message("image  uploaded successfully..").status(HttpStatus.CREATED).success(true).build();

		return new ResponseEntity<ImageResponse>(imageResponse, HttpStatus.CREATED);
	}

	// Serve Image

	@GetMapping(value = "/image/{id}")
	public void serveImage(@PathVariable String id, HttpServletResponse response) throws IOException {
		ProductDto product = productService.getSingleProduct(id);

		InputStream inputStream = fileService.getResource(imagePath,
				Paths.get(product.getImageName()).getFileName().toString());
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());

	}
}
