package com.example.courserecommender.service;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.example.courserecommender.model.Course;
import java.util.List;


@Component
@Primary
public class AdvancedCourseRecommender implements CourseRecommender {
    @Override
    public List<Course> recommendCourses() {
        // returning mock values
        return List.of(
            new Course("Advanced Java", "Deep dive into Java programming", 3),
            new Course("Machine Learning", "Introduction to Machine Learning concepts", 3)
        );
    }
    
}
