package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.model.Course;
import com.feedback.feedbackapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private static final Logger LOGGER = Logger.getLogger(CourseService.class.getName());

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
    }

    public Course getCourse(Long courseId) {
    }

    public Course createCourse(Course courseObject) {
    }

    public Course updateCategory(Long courseId, Course courseObject) {
    }

    public Optional<Course> deleteCourse(Long courseId) {
    }
}
