package com.aa.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aa.springsecurity.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
