package com.example.JwtSpringSecurity.controller;

import com.example.JwtSpringSecurity.entity.User;
import com.example.JwtSpringSecurity.models.Password;
import com.example.JwtSpringSecurity.models.Payload;
import com.example.JwtSpringSecurity.models.UserResponse;
import com.example.JwtSpringSecurity.models.Username;
import com.example.JwtSpringSecurity.service.EmailService;
import com.example.JwtSpringSecurity.service.TempUsername;
import com.example.JwtSpringSecurity.service.UserService;
import com.example.JwtSpringSecurity.service.imlp.EmailServiceImpl;
import com.example.JwtSpringSecurity.service.imlp.MyUserDetailsService;
import com.example.JwtSpringSecurity.service.imlp.TempUsernameImpl;
import com.example.JwtSpringSecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
public class SecurityController {
    @Autowired
    private TempUsername tempUsername;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;



    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping({"/superadmin"})
    public String user() {
        return "SuperAdmin";
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
    @GetMapping({"/admin"})
    public String admin() {
        return "Admin";
    }



    @PostMapping("/username")
    public ResponseEntity<UserResponse> verifyUsername(@RequestBody Username username) throws Exception {
        UserResponse userResponse = new UserResponse();
        try {
            System.out.println(username);
            userDetailsService.loadUserByUsername(username.getUsername());
            emailService.sendEmail(username.getUsername());
            UserDetails userDetails = userDetailsService.loadUserByUsername(username.getUsername());
            tempUsername.setUsername(username.getUsername());
            userResponse.setMessage("user verified and mail send: ");
            userResponse.setStatus("success");
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } catch (NoSuchElementException e) {
            userResponse.setMessage("invalid user");
            userResponse.setStatus("failure");
//            throw new Exception("Incorrect username",e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(userResponse);
        }
    }

    @PostMapping("/password")
    public ResponseEntity<UserResponse> verifyPassword(@RequestBody Password password) throws Exception {
        UserResponse userResponse = new UserResponse();
        UserResponse.Payload payload = userResponse.new Payload();
        UserResponse.Payload.User userjson = payload.new User();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            tempUsername.getUsername(), password.getPassword()));
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(tempUsername.getUsername());

            final String jwt = jwtTokenUtil.generateToken(userDetails);
            Optional<User> user = userService.findUser(tempUsername.getUsername());

            System.out.println(user.get().getUsername()+" "+user.get().getFirstName());
            userjson.setEmail(user.get().getUsername());
            userjson.setFirstName(user.get().getFirstName());
            userjson.setLastName(user.get().getLastName());
            userjson.setLocation(user.get().getLocation().getId());
            userjson.setId(user.get().getId());

            userjson.setRole(user.get().getRoles());

            payload.setUser(userjson);

            payload.setToken(jwt);
            userResponse.setPayload(payload);

            userResponse.setStatus("success");
            userResponse.setMessage("Otp verified");
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } catch (BadCredentialsException e) {
            userResponse.setStatus("failure");
            userResponse.setMessage("Invalid OTP");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
        }
    }
}
