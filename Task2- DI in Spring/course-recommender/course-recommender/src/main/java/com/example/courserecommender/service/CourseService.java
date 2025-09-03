package com.example.courserecommender.service;

import com.example.courserecommender.service.CourseRecommender; // your interface
import com.example.courserecommender.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CourseRecommender courseRecommender;

    // constructor injection
    CourseService(CourseRecommender courserecommender) {
        this.courseRecommender = courserecommender;
    }

    // setter injection
    @Autowired
    public void setCourseRecommender(CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }

    public List<Course> getRecommendedCourses() {
        return courseRecommender.recommendCourses();
    }
}
