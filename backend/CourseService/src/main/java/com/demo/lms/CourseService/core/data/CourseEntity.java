package com.demo.lms.CourseService.core.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="courses")
@Data
public class CourseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	private  String courseId;
	
	@Column(unique=true)
    private  String title;

	private  String technology;
	
    private  BigDecimal hours;
    
    private  String description;

}
