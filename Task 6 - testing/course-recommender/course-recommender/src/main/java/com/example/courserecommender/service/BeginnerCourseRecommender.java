package com.example.courserecommender.service;
import org.springframework.stereotype.Component;
import com.example.courserecommender.model.Course;
import org.springframework.context.annotation.Primary;
import java.util.List;


public class BeginnerCourseRecommender implements CourseRecommender {
    @Override
    public List<Course> recommendCourses() {
        // returning mock values 
        return List.of(
            new Course("Intro to Programming", "Learn the basics of programming", 3),
            new Course("Web Development 101", "Introduction to web development", 2),
            new Course("Pet Petting", "How to pet pets", 3)
        );
    }
    
}
