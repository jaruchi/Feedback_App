package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.exception.InformationExistException;
import com.feedback.feedbackapp.exception.InformationNotFoundException;
import com.feedback.feedbackapp.model.Course;
import com.feedback.feedbackapp.model.CourseFeedBack;
import com.feedback.feedbackapp.repository.CourseFeedBackRepository;
import com.feedback.feedbackapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CourseFeedBackService {

    private CourseFeedBackRepository courseFeedBackRepository;
    private CourseRepository courseRepository;

    private static final Logger LOGGER = Logger.getLogger(CourseFeedBackService.class.getName());

    @Autowired
    public void setCourseFeedBackRepository(CourseFeedBackRepository courseFeedBackRepository) {
        this.courseFeedBackRepository = courseFeedBackRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //to get all course feedbacks for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks
    public List<CourseFeedBack> getCourseFeedBacks(Long courseId) {
        LOGGER.info("calling getCourseFeedBacks method from service");
        Optional<Course> course = courseRepository.findById(courseId);
        if (course != null) {
            return courseFeedBackRepository.findByCourseId(courseId);
        } else {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        }
    }

    //to get single course feedback for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks/1
    public CourseFeedBack getCourseFeedBack(Long courseId, Long courseFeedBackId) {
        LOGGER.info("calling getCourseFeedBack method from service");

        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        } else {
            Optional<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findByCourseId(courseId)
                    .stream().filter(c -> c.getId().equals(courseFeedBackId)).findFirst();
            if (courseFeedBack.isPresent()) {
                return courseFeedBack.get();
            } else {
                throw new InformationNotFoundException("course feed back with id " + courseFeedBackId + " not found");
            }
        }
    }

    //to create course feedback for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks
    public CourseFeedBack createCourseFeedBack(Long courseId, CourseFeedBack courseFeedBackObject) {
        LOGGER.info("calling createCourseFeedBack method from service");
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        } else {
            Optional<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findByTitle(courseFeedBackObject.getTitle());
            if (courseFeedBack.isEmpty()) {
                courseFeedBackObject.setCourse(course.get());
                return courseFeedBackRepository.save(courseFeedBackObject);
            } else {
                throw new InformationExistException("course feed back with name " + courseFeedBack.get().getTitle()
                        + " " + "already exists");
            }
        }
    }

    //to update course feedback for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks/1
    public CourseFeedBack updateCourseFeedBack(Long courseId, Long courseFeedBackId,
                                               CourseFeedBack courseFeedBackObject) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findById(courseFeedBackId);

        if (course != null && courseFeedBack != null
                && courseId.equals(courseFeedBack.get().getCourse().getId())) {
            if (courseFeedBackObject.getTitle() != null)
                courseFeedBack.get().setTitle((courseFeedBackObject.getTitle()));
            if (courseFeedBackObject.getComments() != null)
                courseFeedBack.get().setComments((courseFeedBackObject.getComments()));
            if (courseFeedBackObject.getRating() != null)
                courseFeedBack.get().setRating((courseFeedBackObject.getRating()));
            return courseFeedBackRepository.save(courseFeedBack.get());
        } else {
            throw new InformationNotFoundException(" course feedback with id " + courseFeedBackId + " not found");
        }
    }

    //to delete course feedback for a course
    // http://localhost:9092/api/courses/1/coursefeedbacks/1
    public Optional<CourseFeedBack> deleteCourseFeedBack(Long courseId, Long courseFeedBackId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findById(courseFeedBackId);

        if (course != null && courseFeedBack != null
                && courseId.equals(courseFeedBack.get().getCourse().getId())) {
            courseFeedBackRepository.deleteById(courseFeedBackId);
            return Optional.of(courseFeedBack.get());
        } else {
            throw new InformationNotFoundException("course feedback with id " + courseFeedBackId + " not found");
        }
    }
}
