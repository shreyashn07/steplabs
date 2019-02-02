package com.steplabs.backend.vidtalk.repository;


import com.steplabs.backend.vidtalk.model.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value="select * from posts p,userprofile u where p.user_prof_id=u.id and p.user_prof_id=:userProfId",nativeQuery = true)
    List<Post> findByUserProfid(Long userProfId);


}

