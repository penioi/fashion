package com.demo.fashion.security;

import com.demo.fashion.ResourceNotFoundException;
import com.demo.fashion.users.UserRepository;
import com.demo.fashion.users.auth.UserLogin;
import com.demo.fashion.users.auth.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        UserLogin userLogin = userLoginRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username not found: " + username)
        );

        return UserPrincipal.create(userLogin);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserLogin user = userLoginRepository.findByUserId(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}