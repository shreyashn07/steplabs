package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.exception.ResourceNotFoundException;
import com.steplabs.backend.vidtalk.model.Comment;
import com.steplabs.backend.vidtalk.repository.CommentRepository;
import com.steplabs.backend.vidtalk.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment saveComment(Long postId,Comment comment){
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));

    }


    @Override
    public Comment editComment(Long commentId, Comment comment){
        return commentRepository.findById(commentId).map(updatedComment -> {
            updatedComment.setText(comment.getText());
            return commentRepository.save(updatedComment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }


    @Override
    public ResponseEntity deleteComment(Long commentId){

        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }

}
