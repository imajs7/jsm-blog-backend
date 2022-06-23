package com.jsmblog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jsmblog.entity.BlogPost;
import com.jsmblog.entity.Category;
import com.jsmblog.entity.User;
import com.jsmblog.exception.ResourceNotFoundException;
import com.jsmblog.payload.BlogPostDto;
import com.jsmblog.repository.BlogPostDao;
import com.jsmblog.repository.CategoryDao;
import com.jsmblog.repository.UserDao;
import com.jsmblog.response.PostResponse;
import com.jsmblog.service.BlogPostService;
import com.jsmblog.utility.Slugify;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogPostServiceImpl implements BlogPostService {

	@Autowired
	private BlogPostDao blogPostDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Slugify slugify;

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
		User author = this.userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		blogPost.setUser(author);

		int categoryId = blogPostDto.getCategory().getId();
		Category category = this.categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		blogPost.setCategory(category);

		BlogPost savedBlogPost = this.blogPostDao.save(blogPost);
		log.info("New Blog Post Created With Id -> {} & Title -> {}", savedBlogPost.getId(), savedBlogPost.getTitle());
		return this.blogPostToBlogPostDto(savedBlogPost);
	}

	@Override
	public PostResponse getBlogPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

		Sort sortingExpression = sortOrder.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending()
				: Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sortingExpression);

		Page<BlogPost> pageOPost = this.blogPostDao.findAll(pageable);

		List<BlogPost> blogPosts = pageOPost.getContent();

		List<BlogPostDto> blogPostsDto = blogPosts.stream().map((blogPost) -> this.blogPostToBlogPostDto(blogPost))
				.collect(Collectors.toList());

		PostResponse postResponse = PostResponse.builder().blogPosts(blogPostsDto).pageNumber(pageOPost.getNumber())
				.pageSize(pageOPost.getSize()).totalNumberOfRecords(pageOPost.getTotalElements())
				.totalNumberOfPages(pageOPost.getTotalPages()).isFirstPage(pageOPost.isFirst())
				.isLastPage(pageOPost.isLast()).build();

		return postResponse;
	}

	@Override
	public BlogPostDto getBlogPostById(Integer blogPostId) {
		BlogPost blogPostFindById = this.blogPostDao.findById(blogPostId)
				.orElseThrow(() -> new ResourceNotFoundException("Blogpost", "blogPostId", blogPostId));
		return this.blogPostToBlogPostDto(blogPostFindById);
	}

	@Override
	public BlogPostDto editBlogPost(BlogPostDto blogPostDto, Integer blogPostId) {

		BlogPost blogPostFindById = this.blogPostDao.findById(blogPostId)
				.orElseThrow(() -> new ResourceNotFoundException("Blogpost", "blogPostId", blogPostId));

		blogPostFindById.setTitle(blogPostDto.getTitle());
		blogPostFindById.setContent(blogPostDto.getContent());

		String slug = slugify.getSlug(blogPostDto.getTitle());
		blogPostFindById.setSlug(slug);

		String featuredImage = blogPostDto.getFeaturedImage();
		if (featuredImage == "")
			featuredImage = "default.png";
		blogPostFindById.setFeaturedImage(featuredImage);

		int userId = blogPostDto.getUser().getId();
		User author = this.userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		blogPostFindById.setUser(author);

		int categoryId = blogPostDto.getCategory().getId();
		Category category = this.categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		blogPostFindById.setCategory(category);

		blogPostFindById.setModifiedDate(new Date());

		BlogPost savedBlogPost = this.blogPostDao.save(blogPostFindById);
		return this.blogPostToBlogPostDto(savedBlogPost);
	}

	@Override
	public BlogPostDto deleteBlogPost(Integer blogPostId) {
		BlogPost blogPostFindById = this.blogPostDao.findById(blogPostId)
				.orElseThrow(() -> new ResourceNotFoundException("Blogpost", "blogPostId", blogPostId));
		this.blogPostDao.delete(blogPostFindById);
		return this.blogPostToBlogPostDto(blogPostFindById);
	}

	@Override
	public List<BlogPostDto> getPostByUser(Integer userId) {
		User user = this.userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		List<BlogPost> blogPosts = this.blogPostDao.findByUser(user);
		List<BlogPostDto> blogPostsDto = blogPosts.stream().map((blogPost) -> this.blogPostToBlogPostDto(blogPost))
				.collect(Collectors.toList());
		return blogPostsDto;
	}

	@Override
	public List<BlogPostDto> getPostByCategory(Integer categoryId) {
		Category category = this.categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<BlogPost> blogPosts = this.blogPostDao.findByCategory(category);
		List<BlogPostDto> blogPostsDto = blogPosts.stream().map((blogPost) -> this.blogPostToBlogPostDto(blogPost))
				.collect(Collectors.toList());
		return blogPostsDto;
	}

	@Override
	public List<BlogPostDto> searchPostByTitle(String keyword) {
		List<BlogPost> blogPosts = this.blogPostDao.findByTitleContaining(keyword);
		List<BlogPostDto> blogPostsDto = blogPosts.stream().map((blogPost) -> this.blogPostToBlogPostDto(blogPost))
				.collect(Collectors.toList());
		return blogPostsDto;
	}
	
	@Override
	public List<BlogPostDto> searchPostByContent(String keyword) {
		List<BlogPost> blogPosts = this.blogPostDao.findByContentContaining(keyword);
		List<BlogPostDto> blogPostsDto = blogPosts.stream().map((blogPost) -> this.blogPostToBlogPostDto(blogPost))
				.collect(Collectors.toList());
		return blogPostsDto;
	}

	public BlogPostDto blogPostToBlogPostDto(BlogPost blogPost) {
		return this.modelMapper.map(blogPost, BlogPostDto.class);
	}

	public BlogPost blogPostDtoToBlogPost(BlogPostDto blogPostDto) {
		return this.modelMapper.map(blogPostDto, BlogPost.class);
	}

}
