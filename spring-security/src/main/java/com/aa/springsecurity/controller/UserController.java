package com.aa.springsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aa.springsecurity.dto.UserRequestDto;
import com.aa.springsecurity.dto.UserResponseDto;
import com.aa.springsecurity.dto.common.ApiError;
import com.aa.springsecurity.dto.common.ApiResponse;
import com.aa.springsecurity.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping
	public ApiResponse<UserResponseDto> save(@RequestBody UserRequestDto requestDto) {
		ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>();
		try {
			UserResponseDto responseDto = service.save(requestDto);
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseDto);
			apiResponse.setMessage("Entity saved successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", e.getMessage(), ""));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	@PutMapping("{userId}")
	public ApiResponse<UserResponseDto> update(@RequestBody UserRequestDto requestDto, @PathVariable Long userId) {
		ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>();
		try {
			UserResponseDto responseDto = service.update(requestDto, userId);
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseDto);
			apiResponse.setMessage("Entity updated successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", e.getMessage(), ""));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	@DeleteMapping("{userId}")
	public ApiResponse<UserResponseDto> delete(@PathVariable Long userId) {
		ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>();
		try {
			service.delete(userId);
			apiResponse.setSuccess(true);
			apiResponse.setMessage("Entity deleted successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", e.getMessage(), ""));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	@GetMapping("{userId}")
	public ApiResponse<UserResponseDto> findById(@PathVariable Long userId) {
		ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>();
		try {
			UserResponseDto responseDto = service.findResponseDtoById(userId);
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseDto);
			apiResponse.setMessage("Entity fetched successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", e.getMessage(), ""));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	@GetMapping("/all")
	public ApiResponse<List<UserResponseDto>> findAll() {
		ApiResponse<List<UserResponseDto>> apiResponse = new ApiResponse<>();
		try {
			List<UserResponseDto> responseList = service.findAll();
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseList);
			apiResponse.setMessage("Entities fetched successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", e.getMessage(), ""));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	
	@GetMapping
	public ApiResponse<UserResponseDto> findByUserName(@RequestParam String userName) {
		ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>();
		try {
			UserResponseDto responseDto = service.findByUserName(userName);
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseDto);
			apiResponse.setMessage("Entity fetched successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", e.getMessage(), ""));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
}
