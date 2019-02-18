package com.steplabs.backend.vidtalk.repository;

import com.steplabs.backend.vidtalk.dto.PostDto;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public interface PostRepositoryCustom {

    List<PostDto> findByUserProfileId(Long UserProdId);
}
