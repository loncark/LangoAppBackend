package com.loncark.langoapp.controller;

import com.loncark.langoapp.dto.AuthRequest;
import com.loncark.langoapp.dto.JwtResponse;
import com.loncark.langoapp.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    // register == save in UserController

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/login")
    public JwtResponse authenticateAndReturnToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(authRequest.getUsername()))
                    .build();
        } else throw new UsernameNotFoundException("Bad Credentials, authentication failed.");
    }
}