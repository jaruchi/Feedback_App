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

    //to get all the courses
    // http://localhost:9092/api/coursefeedbacks
    @GetMapping(path = "/coursefeedbacks")
    public List<Course> getCourseFeedBacks() {
        LOGGER.info("calling getCourseFeedBacks method from controller");
        return courseFeedBackService.getCourseFeedBacks();
    }

    //to get a single course feedback
    // http://localhost:9092/api/coursefeedbacks/1
    @GetMapping(path = "/coursefeedbacks/{courseFeedBackId}")
    public Optional getCourseFeedBack(@PathVariable Long courseFeedBackId){
        LOGGER.info("calling getCourseFeedBack method from controller");
        return courseFeedBackService.getCourseFeedBack(courseFeedBackId);
    }

    //to create a course
    // http://localhost:9092/api/coursefeedbacks
    @PostMapping(path = "/coursefeedbacks")
    public Course createCourseFeedBack(@RequestBody CourseFeedBack courseFeedBackObject){
        LOGGER.info("calling createCourseFeedBack method from controller");
        return courseFeedBackService.createCourse(courseFeedBackObject);
    }

    //to update a course
    // http://localhost:9092/api/courses/1
    @PutMapping(path = "/coursefeedbacks/{courseFeedBackId}")
    public Course updateCourseFeedBack(@PathVariable(value = "courseFeedBackId") Long courseFeedBackId,
                               @RequestBody CourseFeedBack courseFeedBackObject){
        LOGGER.info("calling updateCourseFeedBack method from controller");
        return courseFeedBackService.updateCourseFeedBack(courseFeedBackId,courseFeedBackObject);
    }

    //to delete a course
    // http://localhost:9092/api/courses/1
    @DeleteMapping(path="/coursefeedbacks/{courseFeedBackId}")
    public Optional<Course> deleteCourseFeedBack(@PathVariable(value = "courseFeedBackId") Long courseFeedBackId){
        LOGGER.info("calling deleteCourseFeedBack method from controller");
        return courseFeedBackService.deleteCourseFeedBack(courseFeedBackId);
    }
}
