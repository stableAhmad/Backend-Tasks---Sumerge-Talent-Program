package com.example.courserecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.courserecommender.repository.CourseRepository;
import com.example.courserecommender.service.CourseService;
import com.example.courserecommender.model.Course;
// import com.outerbean.outerbean.outerbean.ConcreteOuterBean;

@SpringBootApplication
public class CourseRecommenderApplication {

	public static void main(String[] args) {

		// TESTING SPRING DI
		var context = SpringApplication.run(CourseRecommenderApplication.class, args);
		System.out.println("I got the context");
		CourseService courseService = context.getBean(CourseService.class);
		var courses = courseService.getRecommendedCourses();
		for (var course : courses) {
			System.out.println(course.getName());
		}

		// TESTING CRUD OPERATIONS

		// courseService.addCourse(courses.get(0));
		// courseService.addCourse(courses.get(1));
		// courseService.addCourse(courses.get(2));

		Course myNewCourse = new Course("Advanced Programming", "Learn advanced programming concepts", 4);
		myNewCourse.setId("1"); // assuming the course with id 1 exists
		// courseService.updateCourse(myNewCourse);

		Course fetchedCourse = courseService.viewCourse(5);
		System.out.println("Fetched Course: " + (fetchedCourse != null ? fetchedCourse.getName() : "Not Found"));

		// courseService.deleteCourse(3);

		// testing db connection
		CourseRepository repo = context.getBean(CourseRepository.class);
		int result = repo.testConnection();
        System.out.println("DB Connection test result: " + (result == 1 ? "Success" : "Failure"));

	}
}
