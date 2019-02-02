package com.steplabs.backend.vidtalk.repository;

import com.steplabs.backend.vidtalk.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sun.awt.SunToolkit;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    @Override
    Optional<UserProfile> findById(Long userId);
}
