package com.example.JwtSpringSecurity.service.imlp;

import com.example.JwtSpringSecurity.service.TempUsername;
import org.springframework.stereotype.Service;

@Service
public class TempUsernameImpl implements TempUsername {
    private String username;

    public TempUsernameImpl() {
    }

    public TempUsernameImpl(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }
}
