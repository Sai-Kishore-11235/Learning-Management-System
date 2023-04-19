package com.demo.lms.CourseService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCourseCommand {
	@TargetAggregateIdentifier
    private String courseId;

    private String title;

}
