package com.jsmblog.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsmblog.config.Slugify;
import com.jsmblog.entity.BlogPost;
import com.jsmblog.payload.BlogPostDto;
import com.jsmblog.repository.BlogPostDao;
import com.jsmblog.service.BlogPostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogPostServiceImpl implements BlogPostService {
	
	@Autowired
	public BlogPostDao blogPostDao;
	
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
		BlogPost savedBlogPost = this.blogPostDao.save(blogPost);
		log.info("New Blog Post Created -> {}", savedBlogPost);
		return this.blogPostToBlogPostDto(savedBlogPost);
	}

	@Override
	public List<BlogPostDto> getBlogPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogPostDto getBlogPostById(Integer blogPostId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogPostDto editBlogPost(BlogPostDto blogPostDto, Integer blogPostId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogPostDto deleteBlogPost(Integer blogPostId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BlogPostDto blogPostToBlogPostDto(BlogPost blogPost) {
		return this.modelMapper.map(blogPost, BlogPostDto.class);
	}
	
	public BlogPost blogPostDtoToBlogPost(BlogPostDto blogPostDto) {
		return this.modelMapper.map(blogPostDto, BlogPost.class);
	}

	@Override
	public List<BlogPostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogPostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogPostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
