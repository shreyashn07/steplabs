package com.steplabs.backend.vidtalk.controllers;


import com.steplabs.backend.vidtalk.RestResponse;
import com.steplabs.backend.vidtalk.exception.ResourceNotFoundException;
import com.steplabs.backend.vidtalk.model.Comment;
import com.steplabs.backend.vidtalk.model.UserProfile;
import com.steplabs.backend.vidtalk.repository.CommentRepository;
import com.steplabs.backend.vidtalk.repository.PostRepository;
import com.steplabs.backend.vidtalk.repository.UserProfileRepository;
import com.steplabs.backend.vidtalk.service.CommentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Api(value="Vidtalk", description="Operations pertaining to comments over a post")
public class CommentController {



    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Displays all commentes which is related to the post")
    public Page<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId,
                                                Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/{userProfileId}/comments")
    @ApiOperation(value = "Create a comment")
    public RestResponse<?> createComment(@PathVariable (value = "postId") Long postId,
                                      @Valid @RequestBody Comment comment, @PathVariable(value="userProfileId") Long userProfileId) {

        Optional<UserProfile> userProfile=userProfileRepository.findById(userProfileId);
        comment.setUserProfile(userProfile.get());
        try {
            return RestResponse.createSuccessResponse(commentService.saveComment(postId, comment));
        }
        catch(ResourceNotFoundException e)
        {
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    @ApiOperation(value = "Update a comment")
    public RestResponse<?> updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
      try{
          return RestResponse.createSuccessResponse(commentService.editComment(commentId,commentRequest));
      }
      catch(ResourceNotFoundException e)
        {
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    @ApiOperation(value = "Delete a comment")
    public RestResponse<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        try{
            return RestResponse.createSuccessResponse(commentService.deleteComment(commentId));
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }
}

