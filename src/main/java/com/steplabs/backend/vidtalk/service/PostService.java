package com.steplabs.backend.vidtalk.service;

import com.steplabs.backend.vidtalk.model.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    Post InsertPost(Post post,Long UserProfId);

    Post EditPost(Long postId,Post post);

    ResponseEntity<?> deletePost(Long PostId);
}
