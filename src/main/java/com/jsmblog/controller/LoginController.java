package com.jsmblog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@PostMapping("/login")
	public String userLogin() {
		return "login page";
	}
	
	@PostMapping("/register")
	public String userRegister() {
		return "register page";
	}

}
