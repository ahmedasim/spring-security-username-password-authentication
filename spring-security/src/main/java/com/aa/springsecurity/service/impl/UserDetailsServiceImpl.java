package com.aa.springsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aa.springsecurity.dto.UserResponseDto;
import com.aa.springsecurity.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserResponseDto userResponse = userService.findByUserName(username);
		return User.builder().username(userResponse.getUserName()).password(userResponse.getPassword()).build();
	}

}
