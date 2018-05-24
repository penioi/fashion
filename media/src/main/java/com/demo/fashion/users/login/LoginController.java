package com.demo.fashion.users.login;

import com.demo.fashion.OperationResult;
import com.demo.fashion.security.JwtTokenProvider;
import com.demo.fashion.users.User;
import com.demo.fashion.users.UserRepository;
import com.demo.fashion.users.auth.*;
import com.demo.fashion.users.signup.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userLoginRepository.existsByUsername(signUpRequest.getUsername())) {
            return  OperationResult.error("", "Username Taken!");
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getEmail());
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        UserLogin userLogin = new UserLogin();
        userLogin.setUser(user);
        userLogin.setUsername(signUpRequest.getUsername());
        userLogin.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userLoginRepository.save(userLogin);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(userLogin.getUsername()).toUri();

        return  OperationResult.succes("User registered successfully").created(location).build();
    }
}