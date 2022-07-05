package com.jsmblog.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsmblog.payload.UserDto;
import com.jsmblog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> userLogin(@RequestBody UserDto userDto) {
		UserDto userById = userService.getUserById(userDto.getId());
		log.info("User with userId {} attempted login at {}", userDto.getId(), new Date());
		return ResponseEntity.ok().body(userById);
	}
	
	@PostMapping("/register")
	public String userRegister() {
		return "register page";
	}

}
