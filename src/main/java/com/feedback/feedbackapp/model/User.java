package com.feedback.feedbackapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;


    @Column
    private String role;

    @Column(unique = true)
    private String emailAddress;

    @Column
    private String userName;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    // user can have more than one coursefeedback
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CourseFeedBack> courseFeedBackList;



//Undo when merge

//    @OneToMany(mappedBy = "user")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Course> courseList;
//
//
//    public User(List<Course> courseList) {
//        this.courseList = couseList;
//    }
//
//    public User(Long id, String firstname, String lastname, String role, String emailAddress, String userName, String password, List<Course> categoryList) {
//        this.id = id;
//        this.courseList = categoryList;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.role = role;
//        this.emailAddress = emailAddress;
//        this.userName = userName;
//        this.password = password;
//    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
