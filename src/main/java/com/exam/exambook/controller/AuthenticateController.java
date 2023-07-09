package com.exam.exambook.controller;


import com.exam.exambook.config.JwtUtil;
import com.exam.exambook.exception.InvalidCredentialsException;
import com.exam.exambook.model.JwtRequest;
import com.exam.exambook.model.JwtResponse;
import com.exam.exambook.model.User;
import com.exam.exambook.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User not found!");
        }

        // user authenticated
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User)userDetailsService.loadUserByUsername(principal.getName());
    }

    public void authenticate(String username, String password) throws Exception {
        try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("User Disabled: " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
    }


}
