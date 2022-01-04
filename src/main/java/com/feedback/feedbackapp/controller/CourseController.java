package com.feedback.feedbackapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class CourseController {

    // http://localhost:9092/api/courses
    @GetMapping(path = "/courses/")
    public String getCourses() {
        return "get all courses";
    }
}
