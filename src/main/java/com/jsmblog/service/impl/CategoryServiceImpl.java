package com.jsmblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsmblog.entity.Category;
import com.jsmblog.exception.ResourceNotFoundException;
import com.jsmblog.payload.CategoryDto;
import com.jsmblog.repository.CategoryDao;
import com.jsmblog.service.CategoryService;
import com.jsmblog.utility.Slugify;

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
		Category savedCategory = categoryDao.save(categoryFindById);
		return this.categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category categoryFindById = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		return this.categoryToCategoryDto(categoryFindById);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryDao.findAll();
		
		List<CategoryDto> listOfCategoryDto = categories.stream()
			.map((category) -> this.categoryToCategoryDto(category)).collect(Collectors.toList());
		
		return listOfCategoryDto;
	}

	@Override
	public CategoryDto deleteCategory(Integer categoryId) {
		Category categoryFindById = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		this.categoryDao.delete(categoryFindById);
		return this.categoryToCategoryDto(categoryFindById);
	}

	public Category categoryDtoToCategory(CategoryDto categoryDto) {
		return this.modelMapper.map(categoryDto, Category.class);
	}

	public CategoryDto categoryToCategoryDto(Category category) {
		return this.modelMapper.map(category, CategoryDto.class);
	}

}
