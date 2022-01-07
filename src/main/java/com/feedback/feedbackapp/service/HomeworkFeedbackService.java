package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.exception.InformationExistException;
import com.feedback.feedbackapp.exception.InformationNotFoundException;
import com.feedback.feedbackapp.model.Homework;
import com.feedback.feedbackapp.model.HomeworkFeedback;
import com.feedback.feedbackapp.repository.HomeworkFeedbackRepository;
import com.feedback.feedbackapp.repository.HomeworkRepository;
import com.feedback.feedbackapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class HomeworkFeedbackService {

    private HomeworkFeedbackRepository homeworkFeedbackRepository;
    private HomeworkRepository homeworkRepository;

    private static final Logger LOGGER = Logger.getLogger(HomeworkFeedbackService.class.getName());

    @Autowired
    public void setHomeworkFeedbackRepository(HomeworkFeedbackRepository homeworkFeedbackRepository) {
        this.homeworkFeedbackRepository = homeworkFeedbackRepository;
    }

    @Autowired
    public void setHomeworkRepository(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }


    // to get all homework feedbacks for a homework
    // http://localhost:9092/api/homeworkfeedbacks/
    public List<HomeworkFeedback> getHomeworkFeedbacks() {
        LOGGER.info("calling getHomeworkFeedbacks method from service");
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        return homeworkFeedbackRepository.findByUserId(userId);
    }

    // to get single homework feedback for a homework
    // http://localhost:9092/api/homeworkfeedback/homework/1
    public HomeworkFeedback getHomeworkFeedback(Long homeworkId) {
        LOGGER.info("calling getHomeworkFeedback method from service");

        Optional<Homework> homework = homeworkRepository.findById(homeworkId);
        if (homework.isEmpty()) {
            throw new InformationNotFoundException("homework with id " + homeworkId + " not found");
        }

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        Optional<HomeworkFeedback> homeworkFeedback = homeworkFeedbackRepository.findByUserIdAndHomeworkId(userId, homeworkId);

        if (homeworkFeedback.isEmpty()) {
            throw new InformationNotFoundException("homework feedback for homework : " + homework.get().getName() +
                    " not found");
        }
        return homeworkFeedback.get();
    }

    // to create homework feedback for a homework
    // http://localhost:9092/api/homeworkfeedback/homework/1
    public HomeworkFeedback createHomeworkFeedback(Long homeworkId, HomeworkFeedback homeworkFeedbackObject) {
        LOGGER.info("calling createHomeworkFeedback method from service");
        Optional<Homework> homework = homeworkRepository.findById(homeworkId);
        if (homework.isEmpty()) {
            throw new InformationNotFoundException("homework with id " + homeworkId + " not found");
        }

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user only student can create a homework" +" " +
                    "feedback ---");
        }
        Optional<HomeworkFeedback> homeworkFeedback = homeworkFeedbackRepository.findByUserIdAndHomeworkId(userId, homeworkId);
        if (homeworkFeedback.isPresent()) {
            throw new InformationExistException("homework feed back with name " + homeworkFeedback.get().getTitle()
                    + " " + "already exists");
        }
        homeworkFeedbackObject.setHomework(homework.get());
        homeworkFeedbackObject.setUser(userDetails.getUser());
        return homeworkFeedbackRepository.save(homeworkFeedbackObject);
    }

    //to update homework feedback for a homework
    // http://localhost:9092/api/homeworkfeedback/homework/1
    public HomeworkFeedback updateHomeworkFeedback(Long homeworkId,
                                                   HomeworkFeedback homeworkFeedbackObject) {
        Optional<Homework> homework = homeworkRepository.findById(homeworkId);
        if (homework.isEmpty()) {
            throw new InformationNotFoundException("homework with id " + homeworkId + " not found");
        }

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user only student can update a homework " +
                    "feedback----");
        }

        Optional<HomeworkFeedback> homeworkFeedback = homeworkFeedbackRepository.findByUserIdAndHomeworkId(userId, homeworkId);
        if (homeworkFeedback.isEmpty()) {
            throw new InformationNotFoundException("homework feedback for homework " + homework.get().getName()
                    + " don't exists");
        }

        if (homeworkFeedbackObject.getTitle() != null)
            homeworkFeedback.get().setTitle((homeworkFeedbackObject.getTitle()));
        if (homeworkFeedbackObject.getComments() != null)
            homeworkFeedback.get().setComments((homeworkFeedbackObject.getComments()));
        if (homeworkFeedbackObject.getComfort() != null)
            homeworkFeedback.get().setComfort((homeworkFeedbackObject.getComfort()));
        if (homeworkFeedbackObject.getCompletness() != null)
            homeworkFeedback.get().setCompletness((homeworkFeedbackObject.getCompletness()));
        return homeworkFeedbackRepository.save(homeworkFeedback.get());
    }

    //to delete homework feedback for a homework
    // http://localhost:9092/api/homeworkfeedback/homework/1
    public Optional<HomeworkFeedback> deleteHomeworkFeedback(Long homeworkId) {
        Optional<Homework> homework = homeworkRepository.findById(homeworkId);
        if (homework.isEmpty()) {
            throw new InformationNotFoundException("homework with id " + homeworkId + " not found");
        }

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user only student can delete a homework " +
                    "feedback----");
        }
        Optional<HomeworkFeedback> homeworkFeedback = homeworkFeedbackRepository.findByUserIdAndHomeworkId(userId, homeworkId);

        if (homeworkFeedback.isEmpty()) {
            throw new InformationNotFoundException("homework feedback for homework : " + homework.get().getName() +
                    " not found");
        }
         homeworkFeedbackRepository.deleteById(homeworkFeedback.get());
        return homeworkFeedback;
    }

    // to get all homework feedbacks by an instructor
    // http://localhost:9092/api/homeworkfeedbacks/
    public List<HomeworkFeedback> getHomeworkFeedbacksByHomework(Long homeworkId) {
        LOGGER.info("calling getHomeworkFeedbacks method from service");
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid instructor ---");
        }

        return homeworkFeedbackRepository.findByHomeworkId(homeworkId);
    }
}
