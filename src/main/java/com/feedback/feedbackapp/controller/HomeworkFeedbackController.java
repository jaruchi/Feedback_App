package com.feedback.feedbackapp.controller;

import com.feedback.feedbackapp.model.HomeworkFeedback;
import com.feedback.feedbackapp.model.response.HomeworkFeedbackResponse;
import com.feedback.feedbackapp.service.HomeworkFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class HomeworkFeedbackController {

    private HomeworkFeedbackService homeworkFeedbackService;
    private static final Logger LOGGER = Logger.getLogger(HomeworkFeedbackController.class.getName());

    @Autowired
    public void setHomeworkFeedbackService(HomeworkFeedbackService homeworkFeedbackService) {
        this.homeworkFeedbackService = homeworkFeedbackService;
    }


    //to get a single homework feedback for a homework
    // http://localhost:9092/api/homeworkfeedback/homework/1
    @GetMapping(path = "/homeworkfeedback/homework/{homeworkId}")
    public HomeworkFeedback getHomeworkFeedback(@PathVariable(value = "homeworkId") Long homeworkId) {
        LOGGER.info("calling getHomeworkFeedback method from controller");
        return homeworkFeedbackService.getHomeworkFeedback(homeworkId);
    }

    //to create a homework feedback for a homework
    // http://localhost:9092/api/homeworkfeedback/homework/2/
    @PostMapping(path = "/homeworkfeedback/homework/{homeworkId}")
    public HomeworkFeedback createHomeworkFeedback(@PathVariable(value = "homeworkId") Long homeworkId,
                                               @RequestBody HomeworkFeedback homeworkFeedBackObject) {
        LOGGER.info("calling createHomeworkFeedback method from controller");
        return homeworkFeedbackService.createHomeworkFeedback(homeworkId, homeworkFeedBackObject);
    }

    //to update a homework feedback for a homework
    // http://localhost:9092/api/homeworkfeedback/homework/1
    @PutMapping(path = "/homeworkfeedback/homework/{homeworkId}")
    public HomeworkFeedback updateHomeworkFeedback(@PathVariable(value = "homeworkId") Long homeworkId,
                                                   @RequestBody HomeworkFeedback homeworkFeedBackObject) {
        LOGGER.info("calling updateHomeworkFeedback method from controller");
        return homeworkFeedbackService.updateHomeworkFeedback(homeworkId, homeworkFeedBackObject);
    }

    //to delete a homework feedback for a homework
    // http://localhost:9092/api/homeworkfeedback/1/homework/1
    @DeleteMapping(path = "/homeworkfeedback/homework/{homeworkId}")
    public Optional<HomeworkFeedback> deleteHomeworkFeedback(@PathVariable(value = "homeworkId") Long homeworkId) {
        LOGGER.info("calling deleteHomeworkFeedback method from controller");
        return homeworkFeedbackService.deleteHomeworkFeedback(homeworkId);
    }


    //to get all the homework feedbacks by a student
    // http://localhost:9092/api/homeworkfeedbacks/
    @GetMapping(path = "/homeworkfeedbacks")
    public List<HomeworkFeedbackResponse> getHomeworkFeedbacks( ) {
        LOGGER.info("calling getHomeworkFeedbacks method from controller");
        return homeworkFeedbackService.getHomeworkFeedbacks();
    }

    //to get all the homework feedbacks for a homework by an instructor
    // http://localhost:9092/api/homeworkfeedbacks/
    @GetMapping(path = "/homeworkfeedbacks/homework/{homeworkId}")
    public List<HomeworkFeedbackResponse> getHomeworkFeedbacksByHomework(@PathVariable(value = "homeworkId") Long homeworkId ) {
        LOGGER.info("calling getHomeworkFeedbacksByHomework method from controller");
        return homeworkFeedbackService.getHomeworkFeedbacksByHomework(homeworkId);
    }
}
