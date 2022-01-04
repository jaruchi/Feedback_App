package com.feedback.feedbackapp.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api")
public class CourseController {

    private static final Logger LOGGER = Logger.getLogger(CourseController.class.getName());

    //to get all the courses
    // http://localhost:9092/api/courses
    @GetMapping(path = "/courses")
    public String getCourses() {
        LOGGER.info("calling getCourses method from controller");
        return "get all courses";
    }

    //to get a single course
    // http://localhost:9092/api/courses/1
    @GetMapping(path = "/courses/{courseId}")
    public String getCourse(@PathVariable Long courseId){
        LOGGER.info("calling getCourse method from controller");
        return "get single course";
    }

    //to create a course
    // http://localhost:9092/api/courses
    @PostMapping(path = "/courses/{courseId}")
    public String createCourse(@PathVariable Long courseId){
        LOGGER.info("calling getCourse method from controller");
        return "create a course";
    }

    //to update a course
    // http://localhost:9092/api/courses/1
    @PutMapping(path = "/courses/{courseId}")
    public String updateCourse(@PathVariable Long courseId){
        LOGGER.info("calling getCourse method from controller");
        return "update a course";
    }

    @DeleteMapping(path="/courses/{courseId}")
    public String deleteCourse(@PathVariable Long courseId){
        LOGGER.info("calling deleteCategory method from controller");
        return "delete a course";
    }


}
