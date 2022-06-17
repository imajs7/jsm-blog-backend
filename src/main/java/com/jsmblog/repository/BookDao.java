package com.jsmblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsmblog.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer>{

}
