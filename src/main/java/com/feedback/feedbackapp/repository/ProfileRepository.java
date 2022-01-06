package com.feedback.feedbackapp.repository;


import com.feedback.feedbackapp.model.User;
import com.feedback.feedbackapp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findById(Long userId);
//    boolean existsByEmailAddress(String userEmailAddress);

    User findUserByEmailAddress(String userEmailAddress);
}