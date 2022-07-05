package com.jsmblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsmblog.entity.Role;
import com.jsmblog.exception.ResourceNotFoundException;
import com.jsmblog.repository.RoleDao;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleDao roleDao;
	
	@GetMapping("/getAllRoles")
	public ResponseEntity<List<Role>> getAllRoles() {
		List<Role> roles = roleDao.findAll();
		return ResponseEntity.ok().body(roles);
	}
	
	@GetMapping("/getRoleById/{roleId}")
	public ResponseEntity<Role> getRoleById(@PathVariable Integer roleId) {
		Role role = roleDao.findById(roleId).orElseThrow( () -> new ResourceNotFoundException("Role", "roleId", roleId) );
		return ResponseEntity.ok().body(role);
	}
	
	@GetMapping("/getRoleByRoleName/{roleName}")
	public ResponseEntity<Role> getRoleByRoleName(@PathVariable String roleName) {
		Role role = roleDao.findByRoleName(roleName);
		if(role == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(role);
	}

}
