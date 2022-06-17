package com.jsmblog.payload;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
	
	

	private int id;
	
	@NotEmpty
	private String title;
	
	private String description;
	
	private String slug;

}
