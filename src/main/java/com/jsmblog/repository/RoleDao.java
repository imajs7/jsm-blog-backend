package com.jsmblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsmblog.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	
	Role findByRoleName(String roleName);

}
