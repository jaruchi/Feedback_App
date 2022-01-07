package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.exception.InformationExistException;
import com.feedback.feedbackapp.exception.InformationNotFoundException;
import com.feedback.feedbackapp.model.Course;
import com.feedback.feedbackapp.repository.CourseRepository;
import com.feedback.feedbackapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id: " + courseId+
                    " not found");
        }
        return course;
    }

    public Course createCourse(Course courseObject) {
        LOGGER.info("calling createCourse method from service");

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user ---");
        }
        Optional<Course> course  = courseRepository.findByTopic(courseObject.getTopic());
        if (course.isPresent()) {
            throw new InformationExistException("course with topic " + course.get().getTopic()
                    + " " + "already exists");
        }
        // todo : check for validations of courseobject
        if(courseObject.getTopic() == null || courseObject.getTopic().isBlank())
        {
            throw new InformationNotFoundException("-------topic is not provided ---");
        }
        courseObject.setUser(userDetails.getUser());
        return courseRepository.save(courseObject);
    }

    public Course updateCourse(Long courseId, Course courseObject) {
        LOGGER.info("calling updateCourse method from service");

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user ---");
        }

        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id " + courseId
                    + " don't exists");
        }

        if (courseObject.getStartDate() != null)
            course.get().setStartDate(courseObject.getStartDate());
        if (courseObject.getEndDate() != null)
            course.get().setEndDate(courseObject.getEndDate());
        if (courseObject.getWeek() != null)
            course.get().setWeek(courseObject.getWeek());
        if (courseObject.getTopic() != null)
            course.get().setTopic(courseObject.getTopic());
        return courseRepository.save(course.get());
    }

    public Optional<Course> deleteCourse(Long courseId) {
        LOGGER.info("calling deleteCourse method from service");

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user ---");
        }
        Optional<Course> course = courseRepository.findById(courseId);

        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id : " + courseId +
                    " not found");
        }
        courseRepository.deleteById(course.get().getId());
        return course;
    }
}
