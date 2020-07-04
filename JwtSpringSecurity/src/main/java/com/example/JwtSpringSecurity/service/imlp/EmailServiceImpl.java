package com.example.JwtSpringSecurity.service.imlp;

import com.example.JwtSpringSecurity.entity.User;
import com.example.JwtSpringSecurity.repository.UserRepository;
import com.example.JwtSpringSecurity.service.EmailService;
import com.example.JwtSpringSecurity.util.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendEmail(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        int otp = getRandomNumberUsingNextInt();
        user.get().setPassword(String.valueOf(otp));
        userRepository.save(user.get());
        System.out.println(otp);
        EmailSend.setEmail(user.get().getUsername(), "OTP send", otp + " This is your new otp for covid compliance");
    }

    @Override
    public int getRandomNumberUsingNextInt() {
        Random random = new Random();
        return random.nextInt(999999 - 100000) + 100000;
    }
}
