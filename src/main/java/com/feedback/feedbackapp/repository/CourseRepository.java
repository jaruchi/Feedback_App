package com.feedback.feedbackapp.repository;

import com.feedback.feedbackapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByTopic(String topic);

    Optional<Course> findById(Long id);
}
