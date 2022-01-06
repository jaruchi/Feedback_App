package com.feedback.feedbackapp.repository;

import com.feedback.feedbackapp.model.CourseFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseFeedBackRepository extends JpaRepository<CourseFeedBack,Long> {
    List<CourseFeedBack> findByCourseId(Long courseId);
    List<CourseFeedBack> findByUserId(Long userId);

    Optional<CourseFeedBack> findByUserIdAndCourseId(Long userId, Long courseId);
    //CourseFeedBack findByIdAndUserId(Long courseFeedBackId, Long userId);


    Optional<CourseFeedBack> findByTitle(String title);
}
