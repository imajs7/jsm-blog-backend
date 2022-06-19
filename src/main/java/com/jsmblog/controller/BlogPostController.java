package com.jsmblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsmblog.payload.BlogPostDto;
import com.jsmblog.service.BlogPostService;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {
	
	@Autowired
	public BlogPostService blogPostService;

	@GetMapping
	public void getAllBlogPosts() {
		
	}
	
	@GetMapping("/{postId}")
	public void getBlogPostById() {
		
	}
	
	@PostMapping
	public ResponseEntity<BlogPostDto> addBlogPost(@RequestBody BlogPostDto blogPostDto) {
		BlogPostDto addedBlogPostDto = blogPostService.addBlogPost(blogPostDto);
		return new ResponseEntity<BlogPostDto>(addedBlogPostDto, HttpStatus.OK);
	}
	
	@PutMapping("/{postId}")
	public void editBlogPost() {
		
	}
	
	public void deleteBlogPost() {
		
	}
	
	
}
