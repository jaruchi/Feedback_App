package com.feedback.feedbackapp.repository;

import com.feedback.feedbackapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Optional<Course> findById(Long courseId);
    Optional<Course> findByTopic(String topic);
}
