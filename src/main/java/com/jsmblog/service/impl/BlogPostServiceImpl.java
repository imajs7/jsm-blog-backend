package com.jsmblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsmblog.config.Slugify;
import com.jsmblog.entity.BlogPost;
import com.jsmblog.entity.Category;
import com.jsmblog.entity.User;
import com.jsmblog.exception.ResourceNotFoundException;
import com.jsmblog.payload.BlogPostDto;
import com.jsmblog.repository.BlogPostDao;
import com.jsmblog.repository.CategoryDao;
import com.jsmblog.repository.UserDao;
import com.jsmblog.service.BlogPostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogPostServiceImpl implements BlogPostService {

	@Autowired
	public BlogPostDao blogPostDao;
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public CategoryDao categoryDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public Slugify slugify;

	@Override
	public BlogPostDto addBlogPost(BlogPostDto blogPostDto) {
		BlogPost blogPost = this.blogPostDtoToBlogPost(blogPostDto);
		
		blogPost.setCreateDate(new Date());
		blogPost.setModifiedDate(new Date());
		
		String slug = this.slugify.getSlug(blogPostDto.getTitle());
		blogPost.setSlug(slug);
		
		String featuredImage = blogPostDto.getFeaturedImage();
		if (featuredImage == "")
			featuredImage = "default.png";
		blogPost.setFeaturedImage(featuredImage);
		
		int userId = blogPostDto.getUser().getId();
		User author = this.userDao.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User", "userId", userId) );
		blogPost.setUser(author);
		
		int categoryId = blogPostDto.getCategory().getId();
		Category category = this.categoryDao.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category" , "categoryId", categoryId) );
		blogPost.setCategory(category);
		
		BlogPost savedBlogPost = this.blogPostDao.save(blogPost);
		log.info("New Blog Post Created With Id -> {} & Title -> {}", savedBlogPost.getId(), savedBlogPost.getTitle());
		return this.blogPostToBlogPostDto(savedBlogPost);
	}

	@Override
	public List<BlogPostDto> getBlogPost() {
		List<BlogPost> blogPosts = this.blogPostDao.findAll();
		List<BlogPostDto> blogPostsDto = blogPosts.stream()
			.map((blogPost) -> this.blogPostToBlogPostDto(blogPost)).collect(Collectors.toList());
		return blogPostsDto;
	}

	@Override
	public BlogPostDto getBlogPostById(Integer blogPostId) {
		BlogPost blogPostFindById = this.blogPostDao.findById(blogPostId)
				.orElseThrow( () -> new ResourceNotFoundException("Blogpost", "blogPostId", blogPostId));
		return this.blogPostToBlogPostDto(blogPostFindById);
	}

	@Override
	public BlogPostDto editBlogPost(BlogPostDto blogPostDto, Integer blogPostId) {
		
		BlogPost blogPostFindById = this.blogPostDao.findById(blogPostId)
				.orElseThrow( () -> new ResourceNotFoundException("Blogpost", "blogPostId", blogPostId));
		
		blogPostFindById.setTitle(blogPostDto.getTitle());
		blogPostFindById.setContent(blogPostDto.getContent());
		
		String slug = slugify.getSlug( blogPostDto.getTitle() );
		blogPostFindById.setSlug(slug);
		
		String featuredImage = blogPostDto.getFeaturedImage();
		if( featuredImage == "" )
			featuredImage = "default.png";
		blogPostFindById.setFeaturedImage(featuredImage);
		
		
		int userId = blogPostDto.getUser().getId();
		User author = this.userDao.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User", "userId", userId) );
		blogPostFindById.setUser(author);
		
		int categoryId = blogPostDto.getCategory().getId();
		Category category = this.categoryDao.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category" , "categoryId", categoryId) );
		blogPostFindById.setCategory(category);
		
		blogPostFindById.setModifiedDate(new Date());
		
		BlogPost savedBlogPost = this.blogPostDao.save(blogPostFindById);
		return this.blogPostToBlogPostDto(savedBlogPost);
	}

	@Override
	public BlogPostDto deleteBlogPost(Integer blogPostId) {
		BlogPost blogPostFindById = this.blogPostDao.findById(blogPostId)
				.orElseThrow( () -> new ResourceNotFoundException("Blogpost", "blogPostId", blogPostId));
		this.blogPostDao.delete(blogPostFindById);
		return this.blogPostToBlogPostDto(blogPostFindById);
	}

	@Override
	public List<BlogPostDto> getPostByUser(Integer userId) {
		User user = this.userDao.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User", "userId", userId) );
		List<BlogPost> blogPosts = this.blogPostDao.findByUser(user);
		List<BlogPostDto> blogPostsDto = blogPosts.stream().map( (blogPost) -> this.blogPostToBlogPostDto(blogPost) ).collect(Collectors.toList());
		return blogPostsDto;
	}

	@Override
	public List<BlogPostDto> getPostByCategory(Integer categoryId) {
		Category category = this.categoryDao.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<BlogPost> blogPosts = this.blogPostDao.findByCategory(category);
		List<BlogPostDto> blogPostsDto = blogPosts.stream().map( (blogPost) -> this.blogPostToBlogPostDto(blogPost) ).collect(Collectors.toList());
		return blogPostsDto;
	}

	@Override
	public List<BlogPostDto> searchPost(String keyword) {
		

		
		return null;
	}
	
	public BlogPostDto blogPostToBlogPostDto(BlogPost blogPost) {
		return this.modelMapper.map(blogPost, BlogPostDto.class);
	}

	public BlogPost blogPostDtoToBlogPost(BlogPostDto blogPostDto) {
		return this.modelMapper.map(blogPostDto, BlogPost.class);
	}

}
