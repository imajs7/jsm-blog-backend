package com.jsmblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsmblog.entity.BlogPost;
import com.jsmblog.entity.Category;
import com.jsmblog.entity.User;

@Repository
public interface BlogPostDao extends JpaRepository<BlogPost, Integer>{
	
	List<BlogPost> findByUser(User user);
	List<BlogPost> findByCategory(Category category);

}
