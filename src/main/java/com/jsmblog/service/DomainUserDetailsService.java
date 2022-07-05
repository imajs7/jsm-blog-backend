package com.jsmblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jsmblog.entity.DomainUserDetails;
import com.jsmblog.entity.User;
import com.jsmblog.repository.UserDao;

public class DomainUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao usereDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.usereDao.findByUserName(username);
		return new DomainUserDetails(user);
	}

}
