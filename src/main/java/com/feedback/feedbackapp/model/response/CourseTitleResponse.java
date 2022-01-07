package com.feedback.feedbackapp.model.response;

import com.feedback.feedbackapp.model.CourseFeedBack;

import java.util.Optional;

public class CourseTitleResponse {
    private String courseTitle;
    private Optional<CourseFeedBack> courseFeedback;

    public CourseTitleResponse(String courseTitle, Optional<CourseFeedBack> courseFeedback) {
        this.courseTitle = courseTitle;
        this.courseFeedback = courseFeedback;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Optional<CourseFeedBack> getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CourseFeedBack courseFeedback) {
        this.courseFeedback = Optional.ofNullable(courseFeedback);
    }
}
