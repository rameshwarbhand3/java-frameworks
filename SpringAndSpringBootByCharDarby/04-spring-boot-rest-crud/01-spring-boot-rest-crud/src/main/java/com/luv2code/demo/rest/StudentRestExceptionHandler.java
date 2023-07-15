package com.luv2code.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //To handle exception globally
public class StudentRestExceptionHandler {
    //add exception handling code here
    //Add exception handler to catch studentId not found exception
    @ExceptionHandler //This  method used to handle exception
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        //ResponseEntity wrappers the response object
        //create studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return responseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //Add another exception handler to catch any exception e.g.If it is string type
    @ExceptionHandler //This  method used to handle exception
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
