package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.model.User;

public interface UserService {

    User insertUser(User user);
    User getUserInfo();
}
