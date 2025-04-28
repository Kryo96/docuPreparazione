package com.preparazione.preparazione.controller;

import com.preparazione.preparazione.dto.WebsiteUserDTO;
import com.preparazione.preparazione.entities.Role;
import com.preparazione.preparazione.entities.WebsiteUser;
import com.preparazione.preparazione.repo.WebsiteUserRepository;
import com.preparazione.preparazione.security.JwtUtil;
import com.preparazione.preparazione.web.LoginRequest;
import com.preparazione.preparazione.web.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ControllerAut {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;


    private  WebsiteUserRepository websiteUserRepository;


    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest){
        try{
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );

            String token = jwtUtil.generateToken(loginRequest.getUsername());

            return new LoginResponse(token);
        }catch (AuthenticationException e){
            throw new RuntimeException("compare hai sbagliato dati");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody WebsiteUserDTO user){
        WebsiteUser websiteUser = new WebsiteUser();

        websiteUser.setName(user.getName());
        websiteUser.setEmail(user.getEmail());
        websiteUser.setUser(user.getUser());
        websiteUser.setPassword(passwordEncoder.encode(user.getPassword()));
        websiteUser.setRole(Role.USER);

        websiteUserRepository.save(websiteUser);

        return ResponseEntity.ok("HELL YEAH");

    }
}
