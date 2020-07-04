package com.example.JwtSpringSecurity.service.imlp;

import com.example.JwtSpringSecurity.entity.User;
import com.example.JwtSpringSecurity.repository.UserRepository;
import com.example.JwtSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;


    @Override
    public Optional<User> findUser(String username) {
        return repository.findByUsername(username);
    }
}
