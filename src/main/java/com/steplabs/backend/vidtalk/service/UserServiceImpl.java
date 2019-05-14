package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.exception.CustomException;
import com.steplabs.backend.vidtalk.exception.ResourceNotFoundException;
import com.steplabs.backend.vidtalk.model.User;
import com.steplabs.backend.vidtalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.steplabs.backend.vidtalk.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
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
        boolean flag;

        flag=alreadyRegistered(user.getEmail());
        if(flag!=true) {
            user.getUserProfile().setUser(user);
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        }
        else
        {
            throw new CustomException("User is already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String fetchUserToken(String email){
        User user;
//        user=userRepository.findByEmail(email);
//        return user;
        boolean flag;
        flag=alreadyRegistered(email);
        if(flag) {
            user=getUser(email);
            return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        }
        else{
            throw new ResourceNotFoundException("User is not registered");
        }


    }

    public User getUser(String email) {

      User user;
      user= userRepository.findByEmail(email);
      return user;
    }

    @Override
    public boolean alreadyRegistered(String email){

        return userRepository.existsByEmail(email);


    }
}
