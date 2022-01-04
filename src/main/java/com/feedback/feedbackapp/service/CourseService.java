package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private static final Logger LOGGER = Logger.getLogger(CourseService.class.getName());

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

}
