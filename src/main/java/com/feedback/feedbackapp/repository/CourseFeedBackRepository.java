package com.feedback.feedbackapp.repository;

import com.feedback.feedbackapp.model.CourseFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseFeedBackRepository extends JpaRepository<CourseFeedBack,Long> {
}
