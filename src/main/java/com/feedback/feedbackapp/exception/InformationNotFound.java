package com.feedback.feedbackapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class InformationNotFound {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class InformationNotFoundException extends RuntimeException {
        public  InformationNotFoundException(String message) {super(message);}
    }
}
