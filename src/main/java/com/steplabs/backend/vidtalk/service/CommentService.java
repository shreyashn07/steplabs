package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.model.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Comment saveComment(Long posId,Comment comment);
    Comment editComment(Long commentId,Comment comment);
    ResponseEntity<?> deleteComment(Long commentId);
}
