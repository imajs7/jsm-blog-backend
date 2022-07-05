package com.jsmblog.service;

import java.util.List;
import com.jsmblog.payload.UserDto;

public interface UserService {
	
	UserDto addUser(UserDto userDto);
	UserDto editUser(UserDto userDto, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	UserDto deleteUser(Integer userId);
	UserDto resetPassword(Integer userId, String password);
	
	void addRoleToUser(Integer userId, String roleName);

}
