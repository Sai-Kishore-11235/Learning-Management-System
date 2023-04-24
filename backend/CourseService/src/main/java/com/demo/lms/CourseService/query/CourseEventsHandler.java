package com.demo.lms.CourseService.query;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.demo.lms.CourseService.core.data.AggregateRepository;
import com.demo.lms.CourseService.core.data.CourseEntity;
import com.demo.lms.CourseService.core.data.CourseMongoEntity;
import com.demo.lms.CourseService.core.data.CourseRepository;
import com.demo.lms.CourseService.core.events.CourseCreatedEvent;
import com.demo.lms.CourseService.core.events.CourseDeletedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CourseEventsHandler {
	private final CourseRepository courseRepository;
	
	private final AggregateRepository aggregateRepository;
	
	private final ObjectMapper objectMapper;
	 @Autowired
	 KafkaTemplate<String,Object> kafkaTemplate;
	
	public CourseEventsHandler(CourseRepository courseRepository,AggregateRepository aggregateRepository, ObjectMapper objectMapper) {
		this.courseRepository=courseRepository;
		this.aggregateRepository = aggregateRepository;
		this.objectMapper = objectMapper;
	}
	@EventHandler
	public void on (CourseCreatedEvent event) {
		CourseEntity courseEntity= new CourseEntity();
		BeanUtils.copyProperties(event, courseEntity);
		courseRepository.save(courseEntity);
		this.kafkaTemplate.send("CourseEventsTopic",courseEntity);
	}
	
	@EventHandler
	public void on (CourseDeletedEvent event) {
//		CourseEntity courseEntity= new CourseEntity();
//		BeanUtils.copyProperties(event, courseEntity);
		try {
			courseRepository.deleteByTitle(event.getTitle());
			aggregateRepository.deleteByTitle(event.getTitle());
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@KafkaListener(topics = "CourseEventsTopic",groupId ="CourseEventsTopic")
	public void consume(ConsumerRecord<String,String> record) throws JsonMappingException, JsonProcessingException {
		System.out.println(record);
		CourseMongoEntity courseEntity= objectMapper.readValue(record.value(), CourseMongoEntity.class);
		aggregateRepository.save(courseEntity);
	}

}
