package com.demo.lms.CourseService.model;

import lombok.Data;


import java.math.BigDecimal;


@Data
public class CreateCourseRestModel {
    private String title;
    private BigDecimal hours;
    private  String technology;

   
}

