package com.feedback.feedbackapp.model.response;

import com.feedback.feedbackapp.model.HomeworkFeedback;

public class HomeworkFeedbackResponse {
    private String homeworkTitle;
    private HomeworkFeedback homeworkFeedback;
    private String userName;

    public HomeworkFeedbackResponse(HomeworkFeedback homeworkFeedback) {
        this.homeworkTitle = homeworkFeedback.getHomework().getName();
        this.homeworkFeedback = homeworkFeedback;
        this.userName = homeworkFeedback.getUser().getFirstname()+" "+homeworkFeedback.getUser().getLastname();
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public HomeworkFeedback getHomeworkFeedback() {
        return homeworkFeedback;
    }

    public void setHomeworkFeedback(HomeworkFeedback homeworkFeedback) {
        this.homeworkFeedback = homeworkFeedback;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
