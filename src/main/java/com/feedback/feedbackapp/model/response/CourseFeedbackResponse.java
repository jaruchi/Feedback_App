package com.feedback.feedbackapp.model.response;

import com.feedback.feedbackapp.model.CourseFeedBack;

public class CourseFeedbackResponse {
    private String courseTitle;
    private CourseFeedBack courseFeedback;
    private String userName;

    public CourseFeedbackResponse(CourseFeedBack courseFeedback) {
        this.courseTitle = courseFeedback.getCourse().getTopic();
        this.courseFeedback = courseFeedback;
        this.userName=courseFeedback.getUser().getFirstname()+" "+courseFeedback.getUser().getLastname();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
