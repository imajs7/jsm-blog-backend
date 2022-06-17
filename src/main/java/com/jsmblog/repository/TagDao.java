package com.jsmblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsmblog.entity.Tag;

@Repository
public interface TagDao extends JpaRepository<Tag, Integer>{

}
