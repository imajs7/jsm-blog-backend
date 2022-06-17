package com.jsmblog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsmblog.config.Slugify;
import com.jsmblog.entity.Category;
import com.jsmblog.exception.ResourceNotFoundException;
import com.jsmblog.payload.CategoryDto;
import com.jsmblog.repository.CategoryDao;
import com.jsmblog.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	public Slugify slugify;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = this.categoryDtoToCategory(categoryDto);
		String slug = slugify.getSlug(categoryDto.getTitle());
		category.setSlug(slug);
		Category savedCategory = categoryDao.save(category);
		log.info("New Category Added -> {}", savedCategory);
		return this.categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto editCategory(CategoryDto categoryDto, Integer categoryId) {
		Category categoryFindById = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		categoryFindById.setTitle(categoryDto.getTitle());
		categoryFindById.setDescription(categoryDto.getDescription());
		String slug = slugify.getSlug(categoryDto.getTitle());
		categoryFindById.setSlug(slug);
		return null;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Category categoryDtoToCategory(CategoryDto categoryDto) {
		return this.modelMapper.map(categoryDto, Category.class);
	}

	public CategoryDto categoryToCategoryDto(Category category) {
		return this.modelMapper.map(category, CategoryDto.class);
	}

}
