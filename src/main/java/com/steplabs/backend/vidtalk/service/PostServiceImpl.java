package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.RestResponse;
import com.steplabs.backend.vidtalk.exception.ResourceNotFoundException;
import com.steplabs.backend.vidtalk.model.Post;
import com.steplabs.backend.vidtalk.model.UserProfile;
import com.steplabs.backend.vidtalk.repository.PostRepository;
import com.steplabs.backend.vidtalk.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Post InsertPost(Post post,Long UserProfId){

        Optional<UserProfile> userProfile = userProfileRepository.findById(UserProfId);
        if(userProfile != null) {
            post.setUserProfile(userProfile.get());
            return post;
        }
        else{throw new ResourceNotFoundException("User Profile Is Not Found");
        }

       // return userProfileRepository.findById(UserProfId).map(post.s).orElseThrow(()->new ResourceNotFoundException("UserProfile Not found"));

    }

    @Override
    public Post EditPost(Long postId,Post post){

        return postRepository.findById(postId).map(Updatedpost -> {
            Updatedpost.setTitle(post.getTitle());
            Updatedpost.setDescription(post.getDescription());
            Updatedpost.setContent(post.getContent());
            return postRepository.save(Updatedpost);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));

    }

    @Override
    public ResponseEntity<?> deletePost(Long postId){
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
