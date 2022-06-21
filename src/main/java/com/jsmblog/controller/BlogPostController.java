package com.jsmblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<List<BlogPostDto>> getAllBlogPosts() {
		List<BlogPostDto> allBlogPosts = this.blogPostService.getBlogPost();
		return new ResponseEntity<List<BlogPostDto>>(allBlogPosts, HttpStatus.FOUND);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<BlogPostDto> getBlogPostById(@PathVariable Integer postId) {
		BlogPostDto blogPostById = this.blogPostService.getBlogPostById(postId);
		return new ResponseEntity<BlogPostDto>(blogPostById, HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<BlogPostDto> addBlogPost(@Valid @RequestBody BlogPostDto blogPostDto) {
		BlogPostDto addedBlogPostDto = blogPostService.addBlogPost(blogPostDto);
		return new ResponseEntity<BlogPostDto>(addedBlogPostDto, HttpStatus.OK);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<BlogPostDto> editBlogPost(@Valid
			@RequestBody BlogPostDto blogPostDto,
			@PathVariable Integer postId
			) {
		BlogPostDto editedBlogPost = this.blogPostService.editBlogPost(blogPostDto, postId);
		return new ResponseEntity<BlogPostDto>(editedBlogPost, HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<BlogPostDto> deleteBlogPost(@PathVariable Integer postId) {
		BlogPostDto deletedBlogPost = this.blogPostService.deleteBlogPost(postId);
		return new ResponseEntity<BlogPostDto>(deletedBlogPost, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<BlogPostDto>> getBlogPostsByUser(@PathVariable Integer userId) {
		List<BlogPostDto> postByUser = this.blogPostService.getPostByUser(userId);
		return new ResponseEntity<List<BlogPostDto>>(postByUser, HttpStatus.FOUND);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<BlogPostDto>> getBlogPostsByCategory(@PathVariable Integer categoryId) {
		List<BlogPostDto> postByCategory = this.blogPostService.getPostByCategory(categoryId);
		return new ResponseEntity<List<BlogPostDto>>(postByCategory, HttpStatus.FOUND);
	}
	
	@GetMapping("/search/{keyword}")
	public void searchByKeyword(@PathVariable String keyword) {
		
	}
	
	
}
