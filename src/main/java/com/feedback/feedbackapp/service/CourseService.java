package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.exception.InformationExistException;
import com.feedback.feedbackapp.exception.InformationNotFoundException;
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
        LOGGER.info("calling getCourses method from service");
        return courseRepository.findAll();
    }

    public Optional getCourse(Long courseId) {
        LOGGER.info("calling getCourse method from service");
        Optional course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            return course;
        } else {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        }
    }

    public Course createCourse(Course courseObject) {
        LOGGER.info("calling createCourse method from service");
        Course course = courseRepository.findByTopic(courseObject.getTopic());
        if (course != null) {
            throw new InformationExistException("course with topic " + course.getTopic() + " already exists");
        } else {
            return courseRepository.save(courseObject);
        }
    }

    public Course updateCourse(Long courseId, Course courseObject) {
        LOGGER.info("calling updateCourse method from service");

        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
                if (courseObject.getTopic().equals(course.get().getTopic())) {
                throw new InformationExistException("Course already exists");
            } else {
                    course.get().setStartDate(courseObject.getStartDate());
                    course.get().setEndDate(courseObject.getEndDate());
                    course.get().setWeek(courseObject.getWeek());
                    course.get().setTopic(courseObject.getTopic());
                    return courseRepository.save(course.get());
                }
            } else {
                    throw new InformationNotFoundException("course with id " + courseId + " not found");
                }
    }

    public Optional<Course> deleteCourse(Long courseId) {
        LOGGER.info("calling deleteCourse method from service");

        Optional<Course> course = courseRepository.findById(courseId);

        if (((Optional<?>) course).isPresent()) {
            courseRepository.deleteById(courseId);
            return course;
        } else {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        }
    }

}
