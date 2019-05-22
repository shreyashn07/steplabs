package com.steplabs.backend.vidtalk.controllers;


import com.steplabs.backend.vidtalk.RestResponse;
import com.steplabs.backend.vidtalk.dto.PostDto;
import com.steplabs.backend.vidtalk.exception.CustomException;
import com.steplabs.backend.vidtalk.exception.ResourceNotFoundException;
import com.steplabs.backend.vidtalk.model.Post;
import com.steplabs.backend.vidtalk.model.User;
import com.steplabs.backend.vidtalk.repository.PostRepository;
import com.steplabs.backend.vidtalk.repository.UserRepository;
import com.steplabs.backend.vidtalk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value="Vidtalk", description="Operations pertaining to user)")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    @PostMapping("/users/signup")
    @ApiOperation(value = "Signup for the user")
    public RestResponse createUser(@Valid @RequestBody User user){

        try{
           return RestResponse.createSuccessResponse(userService.insertUser(user));
        }
        catch(CustomException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

   //    return RestResponse.createFailureResponse("User Already exists",400);

        }

    @GetMapping("/users/signin")
    @ApiOperation(value = "Signing in of the user")
    public RestResponse getUser(@Valid @RequestParam("email") String user){
        try{
           return RestResponse.createSuccessResponse(userService.fetchUserToken(user));

        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

    }


    @GetMapping("/users/getall")
    @ApiOperation(value = "Get all posts of the user")
    public List<PostDto> getAllPosts(@Valid @RequestParam("userProfId") Long userProfId){
        List<Post> posts= new ArrayList<>();

        return postRepository.findByUserProfileId(userProfId);



    }


}
