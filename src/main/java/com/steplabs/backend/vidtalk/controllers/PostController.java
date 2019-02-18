package com.steplabs.backend.vidtalk.controllers;



import com.steplabs.backend.vidtalk.RestResponse;
import com.steplabs.backend.vidtalk.exception.ResourceNotFoundException;
import com.steplabs.backend.vidtalk.model.Post;
import com.steplabs.backend.vidtalk.model.UserProfile;
import com.steplabs.backend.vidtalk.repository.PostRepository;
import com.steplabs.backend.vidtalk.repository.UserProfileRepository;
import com.steplabs.backend.vidtalk.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts/{userprofileid}")
    public RestResponse createPost(@Valid @RequestBody Post post, @PathVariable Long userprofileid) {
            try {
                postService.InsertPost(post, userprofileid);
                return RestResponse.createSuccessResponse(postRepository.save(post));
            }

        catch (ResourceNotFoundException e){
                return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @PutMapping("/posts/{postId}")
    public RestResponse<?> updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {

        try{
            return RestResponse.createSuccessResponse(postService.EditPost(postId,postRequest));

        }catch (ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }


    @DeleteMapping("/posts/{postId}")
    public RestResponse<?> deletePostOfUser(@PathVariable Long postId) {


        try{return RestResponse.createSuccessResponse(postService.deletePost(postId)); }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

}

