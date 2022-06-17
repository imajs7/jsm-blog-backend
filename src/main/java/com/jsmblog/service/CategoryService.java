package com.jsmblog.service;

import java.util.List;

import com.jsmblog.payload.CategoryDto;

public interface CategoryService {
	
	CategoryDto addCategory(CategoryDto categoryDto);
	CategoryDto editCategory(CategoryDto categoryDto, Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategories();
	CategoryDto deleteCategory(Integer categoryId);

}
