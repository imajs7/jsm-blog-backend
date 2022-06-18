package com.jsmblog.service;

import java.util.List;

import com.jsmblog.payload.BlogPostDto;

public interface BlogPostService {
	
	BlogPostDto addBlogPost(BlogPostDto blogPostDto);
	List<BlogPostDto> getBlogPost();
	BlogPostDto getBlogPostById(Integer blogPostId);
	BlogPostDto editBlogPost(BlogPostDto blogPostDto, Integer blogPostId);
	BlogPostDto deleteBlogPost(Integer blogPostId);

}
