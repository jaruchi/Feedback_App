package com.feedback.feedbackapp.service;

import com.feedback.feedbackapp.exception.InformationExistException;
import com.feedback.feedbackapp.exception.InformationNotFoundException;
import com.feedback.feedbackapp.model.Course;
import com.feedback.feedbackapp.model.CourseFeedBack;
import com.feedback.feedbackapp.model.response.CourseFeedbackResponse;
import com.feedback.feedbackapp.repository.CourseFeedBackRepository;
import com.feedback.feedbackapp.repository.CourseRepository;
import com.feedback.feedbackapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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


    // to get single course feedback for a course
    // http://localhost:9092/api/coursefeedback/course/1
    public Optional<CourseFeedBack> getCourseFeedBack(Long courseId) {
        LOGGER.info("calling getCourseFeedBack method from service");

        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        }

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        Optional<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findByUserIdAndCourseId(userId, courseId);

        if (courseFeedBack.isEmpty()) {
            throw new InformationNotFoundException("course feedback for course : " + course.get().getTopic() +
                    " not found");
        }
        //CourseTitleResponse courseTitleResponse = new CourseTitleResponse(courseFeedBack.get());

        return courseFeedBack;
    }

    // to create course feedback for a course
    // http://localhost:9092/api/coursefeedback/courses/1
    public CourseFeedBack createCourseFeedBack(Long courseId, CourseFeedBack courseFeedBackObject) {
        LOGGER.info("calling createCourseFeedBack method from service");
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        }

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user only student can create a course " +
                    "feedback---");
        }
        Optional<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findByUserIdAndCourseId(userId, courseId);
        if (courseFeedBack.isPresent()) {
            throw new InformationExistException("course feed back with name " + courseFeedBack.get().getTitle()
                    + " " + "already exists");
        }

        courseFeedBackObject.setCourse(course.get());
        courseFeedBackObject.setUser(userDetails.getUser());
        return courseFeedBackRepository.save(courseFeedBackObject);
    }

    //to update course feedback for a course
    // http://localhost:9092/api/coursefeedback/course/1
    public CourseFeedBack updateCourseFeedBack(Long courseId,
                                               CourseFeedBack courseFeedBackObject) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        }

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user only student can update a course feedback" +
                    "---");
        }

        Optional<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findByUserIdAndCourseId(userId, courseId);
        if (courseFeedBack.isEmpty()) {
            throw new InformationNotFoundException("course feedback for course " + course.get().getTopic()
                    + " don't exists");
        }

        if (courseFeedBackObject.getTitle() != null)
            courseFeedBack.get().setTitle((courseFeedBackObject.getTitle()));
        if (courseFeedBackObject.getComments() != null)
            courseFeedBack.get().setComments((courseFeedBackObject.getComments()));
        if (courseFeedBackObject.getRating() != null)
            courseFeedBack.get().setRating((courseFeedBackObject.getRating()));
        return courseFeedBackRepository.save(courseFeedBack.get());
    }

    //to delete course feedback for a course
    // http://localhost:9092/api/coursefeedback/courses/1
    public Optional<CourseFeedBack> deleteCourseFeedBack(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        }

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid user only student can delete a course feedback" +
                    "---");
        }
        Optional<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findByUserIdAndCourseId(userId, courseId);

        if (courseFeedBack.isEmpty()) {
            throw new InformationNotFoundException("course feedback for course : " + course.get().getTopic() +
                    " not found");
        }
        courseFeedBackRepository.deleteById(courseFeedBack.get().getId());
        return courseFeedBack;
    }

    // a student can get all course feedbacks
    // http://localhost:9092/api/coursefeedbacks/
    public List<CourseFeedbackResponse> getCourseFeedBacks() {
        LOGGER.info("calling getCourseFeedBacks method from service");
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();


        List<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findByUserId(userId);

        List<CourseFeedbackResponse> response=
                courseFeedBack.stream().map(cf->new CourseFeedbackResponse(cf)).collect(Collectors.toList());

        return response;
    }

    // to get all course feedbacks for a course by an instructor
    // http://localhost:9092/api/coursefeedbacks/course/{courseId}
    public List<CourseFeedbackResponse> getCourseFeedBacksByCourse(Long courseId) {
        LOGGER.info("calling getCourseFeedBacks method from service");
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRole = userDetails.getUser().getRole().toLowerCase();
        if (!userRole.equals("instructor")) {
            throw new InformationNotFoundException("-------not a valid instructor ---");
        }

        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new InformationNotFoundException("course with id " + courseId + " not found");
        }
        List<CourseFeedBack> courseFeedBack = courseFeedBackRepository.findByCourseId(courseId);

       List<CourseFeedbackResponse> response=
               courseFeedBack.stream().map(cf->new CourseFeedbackResponse(cf)).collect(Collectors.toList());

       return response;
    }
}
