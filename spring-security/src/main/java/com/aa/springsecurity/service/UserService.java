package com.aa.springsecurity.service;

import java.util.List;

import com.aa.springsecurity.dto.UserRequestDto;
import com.aa.springsecurity.dto.UserResponseDto;
import com.aa.springsecurity.entity.User;

public interface UserService{
	UserResponseDto save(UserRequestDto requestDto);

	UserResponseDto update(UserRequestDto requestDto, Long userId);
	
	void delete(Long itemId);

	UserResponseDto findResponseDtoById(Long userId);
	
	User findById(Long userId);

	List<UserResponseDto> findAll();
	
	UserResponseDto findByUserName(String userName);
}
