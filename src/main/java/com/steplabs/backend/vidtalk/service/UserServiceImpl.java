package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.model.User;
import com.steplabs.backend.vidtalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User insertUser(User user){
        user.getUserProfile().setUser(user);
        return userRepository.save(user);
    }
}
