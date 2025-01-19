package com.example.arazon.service;

import com.example.arazon.repository.UserRepository;
import com.example.arazon.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User create(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("There is always a user with such a username!");
        }

        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("There is always a user with such a email");
        }

        return save(user);
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public User getCurrentUser(){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
}
