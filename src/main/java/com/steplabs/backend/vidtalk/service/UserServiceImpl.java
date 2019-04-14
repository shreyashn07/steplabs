package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.model.User;
import com.steplabs.backend.vidtalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.steplabs.backend.vidtalk.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public String insertUser(User user){
        user.getUserProfile().setUser(user);
        userRepository.save(user);
        return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
    }

    @Override
    public User getUserInfo(String email){
        User user;
        user=userRepository.findByEmail(email);
        return user;


    }

    @Override
    public boolean alreadyRegistered(String email){

        return userRepository.existsByEmail(email);


    }
}
