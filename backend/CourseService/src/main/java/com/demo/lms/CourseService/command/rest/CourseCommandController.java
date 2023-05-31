package com.demo.lms.CourseService.command.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.lms.CourseService.command.CreateCourseCommand;
import com.demo.lms.CourseService.command.DeleteCourseCommand;
import com.demo.lms.CourseService.model.CreateCourseRestModel;



@RestController
@RequestMapping("/api/v1.0/lms/courses")
public class CourseCommandController {
    private static Logger logger = LoggerFactory.getLogger(CourseCommandController.class);

	private final CommandGateway commandGateway;
	
	@Autowired
	public CourseCommandController(CommandGateway commandGateway) {
		this.commandGateway=commandGateway;
	}
	
    @PostMapping("/add/{courseName}")
    public ResponseEntity<?> createCourse(@PathVariable String courseName, @RequestBody CreateCourseRestModel createCourseRestModel){
		Map<String,String> addCourse = new HashMap<String,String>();
        CreateCourseCommand createCourseCommand=CreateCourseCommand.builder()
        .description(createCourseRestModel.getDescription())	
        .hours(createCourseRestModel.getHours())
        .title(createCourseRestModel.getTitle())
        .courseId(UUID.randomUUID().toString())
        .technology(createCourseRestModel.getTechnology()).build();
        String returnValue;
        try {
        	returnValue=commandGateway.sendAndWait(createCourseCommand);
        	addCourse.put("id", returnValue);
        	return new ResponseEntity<>(addCourse, HttpStatus.CREATED);
        }catch(Exception e) {
        	returnValue=e.getLocalizedMessage();
        	addCourse.put("id", null);
        	return new ResponseEntity<>(addCourse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        

    }
    @DeleteMapping("/delete/{courseName}")
    public String deleteCourse(@PathVariable String courseName) {
    	logger.info("Delete the course:"+courseName);
    	 DeleteCourseCommand deleteCourseCommand=new DeleteCourseCommand(UUID.randomUUID().toString(),courseName);
    	 String returnValue;
    	 try {
    		 returnValue= this.commandGateway.sendAndWait(deleteCourseCommand);
    	 }  
    	 catch(Exception e) {
         	returnValue=e.getLocalizedMessage();
         }
    	return returnValue;
    	
    }
}
