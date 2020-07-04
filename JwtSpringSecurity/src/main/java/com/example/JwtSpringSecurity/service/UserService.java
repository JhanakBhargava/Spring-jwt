package com.example.JwtSpringSecurity.service;

import com.example.JwtSpringSecurity.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUser(String username);
}
