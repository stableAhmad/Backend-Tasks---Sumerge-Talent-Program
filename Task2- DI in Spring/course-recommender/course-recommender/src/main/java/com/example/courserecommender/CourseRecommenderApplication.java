package com.example.courserecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.courserecommender.service.CourseService;

@SpringBootApplication
public class CourseRecommenderApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(CourseRecommenderApplication.class, args);
		System.out.println("I got the context");
		CourseService courseService = context.getBean(CourseService.class);
		var courses = courseService.getRecommendedCourses();
		for (var course : courses) {
			System.out.println(course.getTitle());
		}
	}

}
