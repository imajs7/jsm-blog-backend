package com.jsmblog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsmblog.payload.BlogPostDto;
import com.jsmblog.response.PostResponse;
import com.jsmblog.service.BlogPostService;
import com.jsmblog.service.FileService;
import com.jsmblog.utility.Constants;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {
	
	@Autowired
	public BlogPostService blogPostService;
	
	@Autowired
	private FileService fileService;

	@GetMapping
	public ResponseEntity<PostResponse> getAllBlogPosts(
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants. DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortOrder", defaultValue = Constants.DEFAULT_SORT_ORDER, required = false) String sortOrder
			) {
		PostResponse postResponse = this.blogPostService.getBlogPost(pageNumber, pageSize, sortBy, sortOrder);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.FOUND);
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
	
	@GetMapping("/search/title/{keyword}")
	public ResponseEntity<List<BlogPostDto>> searchInTitle(@PathVariable String keyword) {
		List<BlogPostDto> searchResults = this.blogPostService.searchPostByTitle(keyword);
		return new ResponseEntity<List<BlogPostDto>>(searchResults, HttpStatus.OK);
	}
	
	@GetMapping("/search/content/{keyword}")
	public ResponseEntity<List<BlogPostDto>> searchInContent(@PathVariable String keyword) {
		List<BlogPostDto> searchResults = this.blogPostService.searchPostByContent(keyword);
		return new ResponseEntity<List<BlogPostDto>>(searchResults, HttpStatus.OK);
	}
	
	@PostMapping("/featuredImage/{postId}")
	public ResponseEntity<BlogPostDto> featuredImageUpload(
			@RequestParam("featuredImage") MultipartFile featuredImage,
			@PathVariable Integer postId
			) throws IOException {
		BlogPostDto blogPostById = this.blogPostService.getBlogPostById(postId);
		String imageUploaded = this.fileService.uploadImage(Constants.DEFAULT_IMAGE_LOCATION, featuredImage);
		blogPostById.setFeaturedImage(imageUploaded);
		BlogPostDto editBlogPost = this.blogPostService.editBlogPost(blogPostById, postId);
		return new ResponseEntity<BlogPostDto>(editBlogPost, HttpStatus.OK);
	}
	
	@GetMapping(value = "/featuredImage/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getFeaturedImage(
			@PathVariable String imageName,
			HttpServletResponse response
			) throws IOException {
		InputStream resource = this.fileService.getResource(Constants.DEFAULT_IMAGE_LOCATION, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
