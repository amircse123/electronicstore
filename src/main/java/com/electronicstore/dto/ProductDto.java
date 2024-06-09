package com.electronicstore.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {

	
	private String productId;
	
	private String title;
	
	
	private String description;
	
	private int price;
	
	private int quantity;
	
	private Date addedDate;
	
	private boolean live;
	
	private boolean stock;
	
	private int discount;
	
	private String imageName;
	
	private CategoryDto category;
}
