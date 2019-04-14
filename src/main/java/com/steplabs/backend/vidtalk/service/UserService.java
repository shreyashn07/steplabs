package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.model.User;

import java.util.Optional;

public interface UserService {

    String insertUser(User user);
    User getUserInfo(String user);
    boolean alreadyRegistered(String email);

}
