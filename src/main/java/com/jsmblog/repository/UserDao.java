package com.jsmblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsmblog.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUserName(String userName);

}
