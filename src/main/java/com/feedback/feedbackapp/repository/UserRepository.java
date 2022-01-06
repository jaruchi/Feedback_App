package com.feedback.feedbackapp.repository;

import com.feedback.feedbackapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAddress(String userEmailAddress);
    User findUserByEmailAddress(String userEmailAddress);
}