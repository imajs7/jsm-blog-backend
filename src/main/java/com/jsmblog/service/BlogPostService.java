package com.jsmblog.service;

import java.util.List;


import com.jsmblog.payload.BlogPostDto;
import com.jsmblog.response.PostResponse;

public interface BlogPostService {
	
	BlogPostDto addBlogPost(BlogPostDto blogPostDto);
	PostResponse getBlogPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	BlogPostDto getBlogPostById(Integer blogPostId);
	BlogPostDto editBlogPost(BlogPostDto blogPostDto, Integer blogPostId);
	BlogPostDto deleteBlogPost(Integer blogPostId);
	List<BlogPostDto> getPostByUser(Integer userId);
	List<BlogPostDto> getPostByCategory(Integer categoryId);
	List<BlogPostDto> searchPostByTitle(String keyword);
	List<BlogPostDto> searchPostByContent(String keyword);

}
