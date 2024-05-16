package com.electronicstore.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class Helper {

	
	public static<T,U> PageableResponse getPageableResponse(Page<T> page,Class<U> type){
		
		List<T> entity = page.getContent();
		List<U> dtoList = entity.stream().map(s->new ModelMapper().map(s, type)).collect(Collectors.toList());
		
		
		PageableResponse<U> response=new PageableResponse<>();
		response.setContent(dtoList);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastPage(page.isLast());
		
		return response;
		
	}
}
