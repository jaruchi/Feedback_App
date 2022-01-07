package com.feedback.feedbackapp.model.response;

import com.feedback.feedbackapp.model.CourseFeedBack;

public class CourseTitleResponse {
    private String courseTitle;
    private CourseFeedBack courseFeedback;

    public CourseTitleResponse(String courseTitle, CourseFeedBack courseFeedback) {
        this.courseTitle = courseTitle;
        this.courseFeedback = courseFeedback;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public CourseFeedBack getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CourseFeedBack courseFeedback) {
        this.courseFeedback = courseFeedback;
    }
}
