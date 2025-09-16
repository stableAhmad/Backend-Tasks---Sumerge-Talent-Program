package com.example.courserecommender.service;
import com.example.courserecommender.model.Course;
import com.example.courserecommender.repository.CourseRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
I wanna implement the following CRUD operations 
    Add course
    Update course
    View course
    Delete course
*/

@Service
public class CourseService {
    private CourseRecommender courseRecommender;
    private CourseRepository courseRepo;

    // constructor injection
    CourseService(CourseRecommender courserecommender, CourseRepository courseRepo) {
        this.courseRecommender = courserecommender;
        this.courseRepo = courseRepo;
    }

    // setter injection
    @Autowired
    public void setCourseRecommender(CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

    public List<Course> getRecommendedCourses() {
        return courseRecommender.recommendCourses();
    }

    public void addCourse(Course course) {
        System.out.println("add course called ");
        this.courseRepo.insertCourse(course);
    }

    public void updateCourse(String id, Course updatedCourse) {
        //  Course course = courseRepo.getCourseById(id);
        updatedCourse.setId(id);
        int rows = courseRepo.updateCourse(updatedCourse);
        if (rows > 0) {
            System.out.println("Course updated successfully: " + updatedCourse.getName());
        } else {
            System.out.println("No course found with id: " + updatedCourse.getId());
        }
    }

    public Course viewCourse(int courseId) {
        return this.courseRepo.getCourseById(courseId);
    }
    
    public void deleteCourse(int courseId) {
        courseRepo.deleteCourse(courseId);
    }

}
