package com.demo.lms.CourseService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.lms.CourseService.core.data.AggregateRepository;
import com.demo.lms.CourseService.core.data.CourseMongoEntity;
import com.demo.lms.CourseService.core.data.CourseRepository;
import com.demo.lms.CourseService.query.rest.CourseQueryController;
import com.demo.lms.CourseService.query.rest.CourseRestModel;

@SpringBootTest
class CourseServiceApplicationTests {
	
	@Mock
	private AggregateRepository aggregateRepository;

	@Mock
	private CourseRepository courseRepository;
	
//	@InjectMocks
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testGetCoursesByTechnology() {
		String technology = "Java";
		List<CourseMongoEntity> courses = new ArrayList<>();
		courses.add(new CourseMongoEntity("1","Java",new BigDecimal(23.0),"Jvaerer"));
		courses.add(new CourseMongoEntity("2","Java",new BigDecimal(23.0),"Jvarwerert"));
		when(aggregateRepository.findByTechnologyIgnoreCase(technology)).thenReturn(courses);
		List<CourseMongoEntity> result = aggregateRepository.findByTechnologyIgnoreCase(technology);
		assertEquals(courses, result);
	}
	@Test
	public void testGetCoursesByTechnologyandHours() {
		String technology = "Java";
		String minHours = "20";
		String maxHours = "24";
		List<CourseMongoEntity> courses = new ArrayList<>();
		courses.add(new CourseMongoEntity("1","Java",new BigDecimal(22.0),"Jvaerer"));
		courses.add(new CourseMongoEntity("2","Java",new BigDecimal(23.0),"Jvarwerert"));
		when(aggregateRepository.findByTechnologyAndHoursBetween(technology,minHours,maxHours)).thenReturn(courses);
		List<CourseMongoEntity> result = aggregateRepository.findByTechnologyAndHoursBetween(technology,minHours,maxHours);
		assertEquals(courses, result);
	}

}
