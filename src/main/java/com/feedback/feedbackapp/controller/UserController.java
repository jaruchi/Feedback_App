package com.feedback.feedbackapp.controller;

import com.feedback.feedbackapp.model.User;
import com.feedback.feedbackapp.model.request.LoginRequest;
import com.feedback.feedbackapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // to register a user
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        LOGGER.info("This instance create a User!");
        return userService.createUser(userObject);
    }

    // to login a user
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        LOGGER.info("This instance login a User!");
        return userService.loginUser(loginRequest);
    }

    // to see a user profile
    @GetMapping("/profile")
    public User getUserProfile() {
        LOGGER.info("Retrieving info");
        return userService.getUserProfile();
    }
}





