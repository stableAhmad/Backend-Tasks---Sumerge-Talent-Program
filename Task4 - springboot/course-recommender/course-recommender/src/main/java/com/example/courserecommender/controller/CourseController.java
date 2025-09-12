package com.example.courserecommender.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.courserecommender.model.Course;
import com.example.courserecommender.service.CourseService;

@RestController
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(0);
    }

   @PutMapping("/{id}")
    public void updateCourse(@PathVariable Long id, @RequestBody Course course) {
        courseService.updateCourse(course);
    }
    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getRecommendedCourses();
    }

    @GetMapping("/course/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseService.viewCourse(id);
    }

}
