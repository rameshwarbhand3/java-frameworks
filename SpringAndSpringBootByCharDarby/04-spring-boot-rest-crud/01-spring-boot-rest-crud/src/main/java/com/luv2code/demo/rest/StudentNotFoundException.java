package com.luv2code.demo.rest;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}

    /*If you extend RuntimeException, you don't need to declare it in the throws clause
        (i.e. it's an unchecked exception).
        If you extend Exception,you do(it's a checked exception).*/