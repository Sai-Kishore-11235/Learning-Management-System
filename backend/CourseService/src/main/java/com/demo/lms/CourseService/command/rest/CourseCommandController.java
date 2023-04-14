package com.demo.lms.CourseService.command.rest;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.lms.CourseService.command.CreateCourseCommand;
import com.demo.lms.CourseService.model.CreateCourseRestModel;


@RestController
@RequestMapping("/api/v1.0/lms/courses")
public class CourseCommandController {
	private final CommandGateway commandGateway;
	
	@Autowired
	public CourseCommandController(CommandGateway commandGateway) {
		this.commandGateway=commandGateway;
	}
	
    @PostMapping("/add/{courseName}")
    public String createCourse(@PathVariable String courseName, @RequestBody CreateCourseRestModel createCourseRestModel){
        CreateCourseCommand createCourseCommand=CreateCourseCommand.builder()
        .hours(createCourseRestModel.getHours())
        .title(createCourseRestModel.getTitle())
        .courseId(UUID.randomUUID().toString())
        .technology(createCourseRestModel.toString()).build();
        String returnValue;
        try {
        	returnValue=commandGateway.sendAndWait(createCourseCommand);
        }catch(Exception e) {
        	returnValue=e.getLocalizedMessage();
        }
        
        
        return returnValue;

    }
}
