package com.jsmblog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	@Size(min = 3, message = "length of category title must be 3 characters or more")
	private String title;
	
	private String description;
	
	private String slug;

}
