package com.feedback.feedbackapp.controller;

import com.feedback.feedbackapp.model.User;
import com.feedback.feedbackapp.model.UserProfile;
import com.feedback.feedbackapp.model.request.LoginRequest;
import com.feedback.feedbackapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/auth/users")

public class UserController {
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        LOGGER.info("This instance create a User!");
        return userService.createUser(userObject);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        LOGGER.info("This instance login a User!");
        return userService.loginUser(loginRequest);
    }




//    @PostMapping("/profile")
//    public UserProfile createProfile(@RequestBody UserProfile user) {
//        LOGGER.info("Calling createProfile method from Controller!");
//        return userService.createProfile(user);
}





