package com.jsmblog.payload;

import java.util.Date;

import com.jsmblog.entity.Category;
import com.jsmblog.entity.User;

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
	
	private String title;
	
	private String content;
	
	private String featuredImage;
	
	private Date createDate;
	
	private Date modifiedDate;
	
	private Category category;
	
	private User user;

}
