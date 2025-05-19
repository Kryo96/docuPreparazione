package com.leasing.app.controller;


import com.leasing.app.dto.WebUserDTO;
import com.leasing.app.model.Role;
import com.leasing.app.model.WebUser;
import com.leasing.app.repository.WebUserRepository;
import com.leasing.app.security.JwtUtil;
import com.leasing.app.web.LoginRequest;
import com.leasing.app.web.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private WebUserRepository webUserRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        String token = jwtUtil.generateToken(loginRequest.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }


    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody WebUserDTO user){
        WebUser websiteUser = new WebUser();

        websiteUser.setName(user.getName());
        websiteUser.setEmail(user.getEmail());
        websiteUser.setUsername(user.getUsername());
        websiteUser.setPassword(passwordEncoder.encode(user.getPassword()));
        websiteUser.setRole(Role.USER);

        webUserRepository.save(websiteUser);

        return ResponseEntity.ok("HELL YEAH");

    }
}
