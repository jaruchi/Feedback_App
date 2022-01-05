package com.feedback.feedbackapp.controller;

import com.feedback.feedbackapp.model.Course;
import com.feedback.feedbackapp.model.CourseFeedBack;
import com.feedback.feedbackapp.service.CourseFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api")
public class CourseFeedBackController {

    private CourseFeedBackService courseFeedBackService;
    private static final Logger LOGGER = Logger.getLogger(CourseFeedBackController.class.getName());

    @Autowired
    public void setCourseFeedBackService(CourseFeedBackService courseFeedBackService){
        this.courseFeedBackService = courseFeedBackService;
    }

    //to get all the course feedbacks for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks
    @GetMapping(path = "/courses/{courseId}/coursefeedbacks")
    public List<CourseFeedBack> getCourseFeedBacks(@PathVariable(value = "courseId") Long courseId) {
        LOGGER.info("calling getCourseFeedBacks method from controller");
        return courseFeedBackService.getCourseFeedBacks(courseId);
    }

    //to get a single course feedback for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks/1
    @GetMapping(path = "/courses/{courseId}/coursefeedbacks/{courseFeedBackId}")
    public CourseFeedBack getCourseFeedBack(@PathVariable(value = "courseId") Long courseId,
                                      @PathVariable(value = "courseFeedBackId") Long courseFeedBackId){
        LOGGER.info("calling getCourseFeedBack method from controller");
        return courseFeedBackService.getCourseFeedBack(courseId, courseFeedBackId);
    }

    //to create a course feedback for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks
    @PostMapping(path = "/courses/{courseId}/coursefeedbacks")
    public CourseFeedBack createCourseFeedBack(@PathVariable(value = "courseId") Long courseId,
                                               @RequestBody CourseFeedBack courseFeedBackObject){
        LOGGER.info("calling createCourseFeedBack method from controller");
        return courseFeedBackService.createCourseFeedBack(courseId, courseFeedBackObject);
    }

    //to update a course feedback for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks/1
    @PutMapping(path = "/courses/{courseId}/coursefeedbacks/{courseFeedBackId}")
    public CourseFeedBack updateCourseFeedBack(@PathVariable(value = "courseId") Long courseId,
                                               @PathVariable(value = "courseFeedBackId") Long courseFeedBackId,
                                               @RequestBody CourseFeedBack courseFeedBackObject){
        LOGGER.info("calling updateCourseFeedBack method from controller");
        return courseFeedBackService.updateCourseFeedBack(courseId, courseFeedBackId,courseFeedBackObject);
    }

    //to delete a course feedback for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks/1
    @DeleteMapping(path="/courses/{courseId}/coursefeedbacks/{courseFeedBackId}")
    public Optional<CourseFeedBack> deleteCourseFeedBack(@PathVariable(value = "courseId") Long courseId,
                                                         @PathVariable(value = "courseFeedBackId") Long courseFeedBackId){
        LOGGER.info("calling deleteCourseFeedBack method from controller");
        return courseFeedBackService.deleteCourseFeedBack(courseId, courseFeedBackId);
    }
}
