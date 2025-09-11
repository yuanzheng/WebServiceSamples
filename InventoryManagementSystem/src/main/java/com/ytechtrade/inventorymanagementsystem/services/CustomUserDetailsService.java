package com.ytechtrade.inventorymanagementsystem.services;

import com.ytechtrade.inventorymanagementsystem.exceptions.NotFoundException;
import com.ytechtrade.inventorymanagementsystem.models.User;
import com.ytechtrade.inventorymanagementsystem.repositories.UserRepository;
import com.ytechtrade.inventorymanagementsystem.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User Email Not Found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
