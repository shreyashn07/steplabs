package com.steplabs.backend.vidtalk.repository;

import com.steplabs.backend.vidtalk.dto.PostDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<PostDto> findByUserProfileId(Long userProfId){

        Query query = entityManager.createNativeQuery("select p.id,p.title,p.description,p.content,u.id as userId,u.profile_Pic_Url from posts p,userprofile u where p.user_prof_id=u.id and p.user_prof_id=?","findAllDataMapping");
        query.setParameter(1, userProfId );
        return query.getResultList();




    }

}
