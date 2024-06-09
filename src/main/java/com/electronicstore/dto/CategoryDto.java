package com.electronicstore.dto;


import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CategoryDto {

	private String categoryId;
	
	@NotBlank
	@Size(min =4, message="title must be of minmum 5 character...!!")
	private String title;
	
	@NotBlank(message="Description required...!!")
	private String description;
	
	private String coverImage;
	
	
}
