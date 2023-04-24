package com.demo.lms.CourseService.core.data;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AggregateRepository extends MongoRepository<CourseMongoEntity, String> {

	List<CourseMongoEntity> findByTechnology(String technology);
	
	String deleteByTitle(String title);
	
	@Query("{'technology' : ?0, 'hours': {$gte: ?1 ,$lte : ?2}}")
	List<CourseMongoEntity> findByTechnologyAndHoursBetween(String technology,String durationFromRange,String durationToRange);
}
