package com.feedback.feedbackapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "homeworkfeedback")
public class HomeworkFeedback {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer completness;

    @Column
    private Integer comfort;

    @Column
    private String comments;

    @ManyToOne
    @JoinColumn(name = "homework_id")
    //@JsonIgnore
    private Course homework;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public HomeworkFeedback(Long id, String title, Integer completness, Integer comfort, String comments, Course course) {
        this.id = id;
        this.title = title;
        this.completness = completness;
        this.comfort = comfort;
        this.comments = comments;
        this.homework = course;
    }

    public HomeworkFeedback() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCompletness() {
        return completness;
    }

    public void setCompletness(Integer completness) {
        this.completness = completness;
    }

    public Integer getComfort() {
        return comfort;
    }

    public void setComfort(Integer comfort) {
        this.comfort = comfort;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Course getHomework() {
        return homework;
    }

    public void setHomework(Course course) {
        this.homework = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
