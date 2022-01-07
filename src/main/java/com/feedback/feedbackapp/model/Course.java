package com.feedback.feedbackapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @Column
    private Integer week;

    @Column
    private String topic;

    // course can have more than one feedback
//    @OneToMany(mappedBy = "course")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<CourseFeedBack> courseFeedBackList;
    // We don't need list of feedback as part of course model BECAUSE a course should not list all its feedback as it
    // is restricted by user role.
    // course endpoints are not/should not provide information about course feedbacks.
    //to avoid recursion

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Course() {
    }

    public Course(Long id, String startDate, String endDate, String topic) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.week = week;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public List<CourseFeedBack> getCourseFeedBackList() {
//        return courseFeedBackList;
//    }
//
//    public void setCourseFeedBackList(List<CourseFeedBack> courseFeedBackList) {
//        this.courseFeedBackList = courseFeedBackList;
//    }
}
