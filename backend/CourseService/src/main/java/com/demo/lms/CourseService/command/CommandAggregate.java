package com.demo.lms.CourseService.command;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.demo.lms.CourseService.core.events.CourseCreatedEvent;

@Aggregate
public class CommandAggregate {
	
	@AggregateIdentifier
	private String courseId;
	private String title;
    private BigDecimal hours;
    private  String technology;

	public CommandAggregate() {}
	
	@CommandHandler
	public CommandAggregate(CreateCourseCommand createCourseCommand) {
		if(createCourseCommand.getHours().compareTo(BigDecimal.ZERO)<=0) {
			throw new IllegalArgumentException("Hours cannot be less than or equal to zero");
		}
		
		if(createCourseCommand.getTitle() ==null || createCourseCommand.getTitle().isBlank()) {
			throw new IllegalArgumentException("Title cannot be Empty");
		}
		
		CourseCreatedEvent courseCreatedEvent = new CourseCreatedEvent();
		BeanUtils.copyProperties(createCourseCommand, courseCreatedEvent);
		AggregateLifecycle.apply(courseCreatedEvent);
		
	}
	
	@EventSourcingHandler
	public void on(CourseCreatedEvent courseCreatedEvent) {
		this.courseId= courseCreatedEvent.getCourseId();
		this.title=courseCreatedEvent.getTitle();
		this.hours=courseCreatedEvent.getHours();
		this.technology =courseCreatedEvent.getTechnology();
	}

}
