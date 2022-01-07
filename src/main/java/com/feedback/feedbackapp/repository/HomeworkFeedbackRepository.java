package com.feedback.feedbackapp.repository;

import com.feedback.feedbackapp.model.HomeworkFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeworkFeedbackRepository extends JpaRepository<HomeworkFeedback,Long> {
    List<HomeworkFeedback> findByHomeworkId(Long homeworkId);

    List<HomeworkFeedback> findByUserId(Long userId);

    Optional<HomeworkFeedback> findByUserIdAndHomeworkId(Long userId, Long homeworkId);

    Optional<HomeworkFeedback> deleteById(HomeworkFeedback homeworkFeedback);
}
