package com.demo.lms.CourseService.core.data;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AggregateRepository extends MongoRepository<CourseMongoEntity, String> {

	List<CourseMongoEntity> findByTechnology(String technology);
	
	String deleteByTitle(String title);
}
