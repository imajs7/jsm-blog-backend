package com.jsmblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsmblog.payload.UserDto;
import com.jsmblog.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// get all users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> allUsers = this.userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.FOUND);
	}

	// get user by id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto userFoundById = this.userService.getUserById(userId);
		return new ResponseEntity<>(userFoundById, HttpStatus.FOUND);
	}

	// post user
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto addedUserDto = this.userService.addUser(userDto);
		return new ResponseEntity<>(addedUserDto, HttpStatus.CREATED);
	}

	// put user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> editUser(@Valid
			@RequestBody UserDto userDto, 
			@PathVariable Integer userId
			) {
		UserDto editedUser = this.userService.editUser(userDto, userId);
		return new ResponseEntity<>(editedUser, HttpStatus.OK);
	}

	// delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable Integer userId) {
		UserDto deletedUser = this.userService.deleteUser(userId);
		return new ResponseEntity<>(deletedUser, HttpStatus.OK);
	}

}
