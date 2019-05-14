package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.model.User;

import java.util.Optional;

public interface UserService {

    String insertUser(User user);
    String fetchUserToken(String user);
    boolean alreadyRegistered(String email);

}
