package com.demo.lms.CourseService.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/lms/courses")
public class CourseController {

    @PostMapping("/add/{courseName}")
    public String createCourse(@PathVariable String courseName){
        return "hello"+courseName;
    }
}
