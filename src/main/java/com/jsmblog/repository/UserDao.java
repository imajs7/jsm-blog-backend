package com.jsmblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsmblog.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

}
