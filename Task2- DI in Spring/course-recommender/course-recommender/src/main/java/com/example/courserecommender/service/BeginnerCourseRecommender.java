package com.example.courserecommender.service;
import org.springframework.stereotype.Component;
import com.example.courserecommender.model.Course;
import org.springframework.context.annotation.Primary;
import java.util.List;

@Component
@Primary
public class BeginnerCourseRecommender implements CourseRecommender {
    @Override
    public List<Course> recommendCourses() {
        // returning mock values 
        return List.of(
            new Course("1", "Intro to Programming", "Learn the basics of programming"),
            new Course("2", "Web Development 101", "Introduction to web development"),
            new Course("3", "Pet Petting", "How to pet pets")
        );
    }
    
}
