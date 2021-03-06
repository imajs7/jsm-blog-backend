package com.jsmblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsmblog.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>{

}
