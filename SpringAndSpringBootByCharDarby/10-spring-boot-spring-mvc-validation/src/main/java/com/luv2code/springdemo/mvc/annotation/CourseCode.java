package com.luv2code.springdemo.mvc.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CourseCode {
    //define default courseCode
    public String value() default "LUV";
    //define default erro message
    public String message() default "must start with LUV";
    //define defaults groups
    public Class<?>[] groups() default {};
    //define defaults payloads
}
