package com.luv2code.restcruddemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<String> handleException(StudentNotFoundException exc) {

        // instantiate StudentErrorResponse custom error class
        StudentErrorResponse error = new StudentErrorResponse();

        // set error status
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        return new ResponseEntity<>(HTMLizeError(error.getMessage()), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exc) {

        StudentErrorResponse error = new StudentErrorResponse();

        // set error status
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Please only enter integer value!");
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(HTMLizeError(error.getMessage()), HttpStatus.NOT_FOUND);
    }




    // returns error message as HTML
    public String HTMLizeError(String errorMessage) {
        return "<h1>" + errorMessage + "</h1>";
    }


}
