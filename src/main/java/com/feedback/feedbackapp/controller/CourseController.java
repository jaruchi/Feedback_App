package com.feedback.feedbackapp.controller;

import com.feedback.feedbackapp.model.Course;
import com.feedback.feedbackapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api")
public class CourseController {

    private CourseService courseService;
    private static final Logger LOGGER = Logger.getLogger(CourseController.class.getName());

    @Autowired
    public void setCourseService(CourseService courseService){
        this.courseService = courseService;
    }

    //to get all the courses
    // http://localhost:9092/api/courses
    @GetMapping(path = "/courses")
    public List<Course> getCourses() {
        LOGGER.info("calling getCourses method from controller");
        return courseService.getCourses();
    }

    //to get a single course
    // http://localhost:9092/api/courses/1
    @GetMapping(path = "/courses/{courseId}")
    public Optional getCourse(@PathVariable Long courseId){
        LOGGER.info("calling getCourse method from controller");
        return courseService.getCourse(courseId);
    }

    //to create a course
    // http://localhost:9092/api/courses
    @PostMapping(path = "/courses")
    public Course createCourse(@RequestBody Course courseObject){
        LOGGER.info("calling getCourse method from controller");
        return courseService.createCourse(courseObject);
    }

    //to update a course
    // http://localhost:9092/api/courses/1
    @PutMapping(path = "/courses/{courseId}")
    public Course updateCourse(@PathVariable(value = "courseId") Long courseId,
                               @RequestBody Course courseObject){
        LOGGER.info("calling updateCourse method from controller");
        return courseService.updateCourse(courseId,courseObject);
    }

    //to delete a course
    // http://localhost:9092/api/courses/1
    @DeleteMapping(path="/courses/{courseId}")
    public Optional<Course> deleteCourse(@PathVariable(value = "courseId") Long courseId){
        LOGGER.info("calling deleteCourse method from controller");
        return courseService.deleteCourse(courseId);
    }
}
