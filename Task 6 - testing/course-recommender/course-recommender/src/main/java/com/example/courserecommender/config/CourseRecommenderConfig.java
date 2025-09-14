package com.example.courserecommender.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.courserecommender.service.BeginnerCourseRecommender;
import com.example.courserecommender.service.CourseRecommender;

@Configuration
public class CourseRecommenderConfig {
     @Bean
    //  @Primary
    public CourseRecommender beginnerCourseRecommender() {
        return new BeginnerCourseRecommender();
    }
}
