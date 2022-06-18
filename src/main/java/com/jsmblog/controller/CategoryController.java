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

import com.jsmblog.payload.CategoryDto;
import com.jsmblog.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// get all category
	@GetMapping("/getcategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> allCategories = this.categoryService.getAllCategories();
		return new ResponseEntity<>(allCategories, HttpStatus.FOUND);
	}
	
	// get category by id
	@GetMapping("/getcategory/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
		CategoryDto categoryFoundById = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryFoundById, HttpStatus.FOUND);
	}
	
	// create new category
	@PostMapping("/addcategory")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto savedCategoryDto = this.categoryService.addCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(savedCategoryDto, HttpStatus.CREATED);
	}
	
	// edit category by id
	@PutMapping("/editcategory/{categoryId}")
	public ResponseEntity<CategoryDto> editCategory(@Valid 
			@RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId
			){
		CategoryDto editedCategory = this.categoryService.editCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(editedCategory, HttpStatus.OK);
	}	
	
	// delete category
	@DeleteMapping("/deletecategory/{categoryId}")
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Integer categoryId){
		CategoryDto deletedCategory = this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<CategoryDto>(deletedCategory, HttpStatus.OK);
	}
	

}
