package com.jsmblog.payload;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.jsmblog.entity.Category;
import com.jsmblog.entity.User;
import com.jsmblog.utility.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostDto {
	
	private int id;
	
	private String slug;
	
	@NotEmpty
	@Size(min = 5, message = "length of title must be 6 characters or more")
	private String title;
	
	@Size(max = Constants.CONTENT_LENGTH, message = "length of content can be " + Constants.CONTENT_LENGTH + "  characters or less")
	private String content;
	
	private String featuredImage;
	
	private Date createDate;
	
	private Date modifiedDate;
	
	private Category category;
	
	private User user;

}
