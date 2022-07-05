package com.jsmblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsmblog.entity.Role;
import com.jsmblog.entity.User;
import com.jsmblog.exception.*;
import com.jsmblog.payload.UserDto;
import com.jsmblog.repository.RoleDao;
import com.jsmblog.repository.UserDao;
import com.jsmblog.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto addUser(UserDto userDto) {
		User newUser = this.UserdtoToUser(userDto);
		User savedUser = userDao.save(newUser);
		log.info("New user added -> {}", newUser);
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto editUser(UserDto userDto, Integer userId) {

		User userFoundById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		userFoundById.setUserName(userDto.getUserName());
		userFoundById.setUserBio(userDto.getUserBio());

		userFoundById.setPrimaryEmail(userDto.getPrimaryEmail());
		userFoundById.setSecondaryEmail(userDto.getSecondaryEmail());

		userFoundById.setPrimaryContact(userDto.getPrimaryContact());
		userFoundById.setSecondaryContact(userDto.getSecondaryContact());

		userFoundById.setBillingAddressLine1(userDto.getBillingAddressLine1());
		userFoundById.setBillingAddressLine2(userDto.getBillingAddressLine2());
		userFoundById.setBillingCity(userDto.getBillingCity());
		userFoundById.setBillingState(userDto.getBillingState());
		userFoundById.setBillingCountry(userDto.getBillingCountry());
		userFoundById.setBillingPincode(userDto.getBillingPincode());

		userFoundById.setShippingAddressLine1(userDto.getShippingAddressLine1());
		userFoundById.setShippingAddressLine2(userDto.getShippingAddressLine2());
		userFoundById.setShippingCity(userDto.getShippingCity());
		userFoundById.setShippingState(userDto.getShippingState());
		userFoundById.setShippingCountry(userDto.getShippingCountry());
		userFoundById.setShippingPincode(userDto.getShippingPincode());
		
		User savedUser = userDao.save(userFoundById);

		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User userFoundById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		return this.userToUserDto(userFoundById);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userDao.findAll();

		List<UserDto> listOfUsersDto = users.stream().map(user -> this.userToUserDto(user))
				.collect(Collectors.toList());

		return listOfUsersDto;
	}

	@Override
	public UserDto deleteUser(Integer userId) {
		User userFoundById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		userDao.delete(userFoundById);
		return this.userToUserDto(userFoundById);
	}
	
	@Override
	public UserDto resetPassword(Integer userId, String password) {
		User userFoundById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		userFoundById.setPassword(password);
		User savedUser = userDao.save(userFoundById);
		return this.userToUserDto(savedUser);
	}
	
	@Override
	public void addRoleToUser(Integer userId, String roleName) {
		User userFoundById = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Role role = roleDao.findByRoleName(roleName);
		userFoundById.addRole(role);	
	}

	// Model Mapper - User To UserDTO
	public UserDto userToUserDto(User user) {
		return this.modelMapper.map(user, UserDto.class);

	}

	// Model Mapper - UserDTO to User
	public User UserdtoToUser(UserDto userDto) {
		return this.modelMapper.map(userDto, User.class);
	}

}
