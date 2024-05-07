package com.aa.springsecurity.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aa.springsecurity.dto.UserRequestDto;
import com.aa.springsecurity.dto.UserResponseDto;
import com.aa.springsecurity.entity.User;
import com.aa.springsecurity.repo.UserRepo;
import com.aa.springsecurity.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	ObjectMapper objectMapper = new ObjectMapper();

	
	private void updateEntityFromRequestDto(UserRequestDto requestDto, User entity) {
		String encodedPassword = new BCryptPasswordEncoder().encode(requestDto.getPassword());
		entity.setUserName(requestDto.getUserName());
		entity.setPassword(encodedPassword);
		entity.setEmail(requestDto.getEmail());
	}
	
	@Override
	public UserResponseDto save(UserRequestDto requestDto) {
		User user = objectMapper.convertValue(requestDto, User.class);
		String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		return objectMapper.convertValue(user, UserResponseDto.class);
	}

	@Override
	public UserResponseDto update(UserRequestDto requestDto, Long userId) {
		User user = null;
		Optional<User> userOpt = repo.findById(userId);
		if(userOpt.isEmpty()) {
			throw new RuntimeException("Resource not found!");
		}else {
			user = userOpt.get();
			updateEntityFromRequestDto(requestDto, user);	
			repo.save(user);
		}
		
		return objectMapper.convertValue(user, UserResponseDto.class);
	}

	@Override
	public void delete(Long userId) {
		Optional<User> userOpt = repo.findById(userId);
		if(userOpt.isEmpty()) {
			throw new RuntimeException("Resource not found!");
		}else {
			repo.delete(userOpt.get());
		}
		
	}

	@Override
	public UserResponseDto findResponseDtoById(Long userId) {
		Optional<User> userOpt = repo.findById(userId);
		return objectMapper.convertValue(userOpt.get(), UserResponseDto.class);
	}

	@Override
	public User findById(Long userId) {
		Optional<User> userOpt = repo.findById(userId);
		return userOpt.get();
	}

	@Override
	public List<UserResponseDto> findAll() {
		return repo.findAll().stream().map(user -> objectMapper.convertValue(user, UserResponseDto.class)).toList();
	}
	
	@Override
	public UserResponseDto findByUserName(String userName) {
		User user = repo.findByUserName(userName);
		return objectMapper.convertValue(user, UserResponseDto.class);
	}
}
