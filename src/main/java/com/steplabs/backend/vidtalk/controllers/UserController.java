package com.steplabs.backend.vidtalk.controllers;


import com.steplabs.backend.vidtalk.RestResponse;
import com.steplabs.backend.vidtalk.model.Post;
import com.steplabs.backend.vidtalk.model.User;
import com.steplabs.backend.vidtalk.repository.PostRepository;
import com.steplabs.backend.vidtalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user){
        user.getUserProfile().setUser(user);
        return userRepository.save(user);
    }

    @GetMapping("/user")
    public RestResponse getUser(@Valid @RequestParam("email") String user){

        Optional<User> userInfo =Optional.empty();
        List<User> list= new ArrayList();
        userInfo=userRepository.findByEmail(user);
        if(userInfo!= null)
        {
            //userInfo=userRepository.getUserByEmail(user);
            return RestResponse.createSuccessResponse(userInfo);

        }
        return RestResponse.createSuccessResponse(userInfo);
    }


    @GetMapping("/user/getall")
    public List<Post> getAllPosts(@Valid @RequestParam("userProfId") Long userProfId){
        List<Post> posts= new ArrayList<>();

        return postRepository.findByUserProfid(userProfId);



    }


}
