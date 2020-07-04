package com.example.JwtSpringSecurity.service;

import com.example.JwtSpringSecurity.entity.User;
import com.example.JwtSpringSecurity.util.EmailSend;

import java.util.Optional;
import java.util.Random;

public interface EmailService {
    void sendEmail(String username);

    int getRandomNumberUsingNextInt();
}
