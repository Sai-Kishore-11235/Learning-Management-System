package com.demo.lms.CourseService.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Builder
@Data
public class CreateCourseCommand {
    @TargetAggregateIdentifier
    private final String courseId;
    private final String title;
    private final BigDecimal hours;
    private final String technology;


}
