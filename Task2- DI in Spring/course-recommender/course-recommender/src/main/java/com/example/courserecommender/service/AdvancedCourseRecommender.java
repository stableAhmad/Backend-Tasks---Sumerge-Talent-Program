package com.example.courserecommender.service;
import org.springframework.stereotype.Component;
import com.example.courserecommender.model.Course;
import java.util.List;

@Component
public class AdvancedCourseRecommender implements CourseRecommender {
    @Override
    public List<Course> recommendCourses() {
        // returning mock values
        return List.of(
            new Course("3", "Advanced Java", "Deep dive into Java programming"),
            new Course("4", "Machine Learning", "Introduction to Machine Learning concepts")
        );
    }
    
}
