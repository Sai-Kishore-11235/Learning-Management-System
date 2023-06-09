package com.demo.lms.CourseService.core.data;



import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "courses")
@Data
@AllArgsConstructor
public class CourseMongoEntity {
	
	@Id
	private String courseId;
	private String technology;
	private BigDecimal hours;
	private String title;
	private  String description;

}
