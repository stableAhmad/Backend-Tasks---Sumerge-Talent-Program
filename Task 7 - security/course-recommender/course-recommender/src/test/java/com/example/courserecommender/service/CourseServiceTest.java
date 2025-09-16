package com.example.courserecommender.service;

// splitting my imports
import com.example.courserecommender.repository.CourseRepository;
import com.example.courserecommender.model.Course;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    // unit dependencies 
    @Mock 
    private CourseRecommender courseRecommender;
    @Mock
    private CourseRepository courseRepo;

    @InjectMocks 
    private CourseService courseService;

    @Test
    public void testAddCourse() {
        Course course = new Course("Java Basics", "Learn the basics of Java programming.", 4);
        course.setId("1");
        courseService.addCourse(course);

        // method is void so no return value to be assert 

        // verify that insertCourse was called once with the given course
        Mockito.verify(courseRepo, Mockito.times(1)).insertCourse(course);
    }

    @Test
    public void testUpdateCourse() {
        int courseId = 1;
        Course course = new Course("Java Basics", "Learn the basics of Java programming.", 4);
        course.setId("1");

        // mock behavior of repo.getCourseById : dummy value of above course
        Mockito.when(courseRepo.getCourseById(courseId)).thenReturn(course);

        // mock behavior of repo.updateCourse : dummy value of 1
        Mockito.when(courseRepo.updateCourse(course)).thenReturn(1);

        // Call the method under test
        courseService.updateCourse(courseId, course);

        // Verify that getCourseById was called once with the given courseId
        Mockito.verify(courseRepo, Mockito.times(1)).getCourseById(courseId);

        // Verify that updateCourse was called once with the given course
        Mockito.verify(courseRepo, Mockito.times(1)).updateCourse(course);
    }

    @Test
    public void testViewCourse() {
        int courseId = 1;
        Course course = new Course("Java Basics", "Learn the basics of Java programming.", 4);
        course.setId("1");

        // mock behavior of repo.getCourseById : dummy value of above course
        Mockito.when(courseRepo.getCourseById(courseId)).thenReturn(course);

        // value to be asserted
        Course result = courseService.viewCourse(courseId);
        assert(result == course);

        // Verify that getCourseById was called once with the given courseId
        Mockito.verify(courseRepo, Mockito.times(1)).getCourseById(courseId);
    }

    @Test
    public void testDeleteCourse() {
        int courseId = 1;

        // Call the method under test
        courseService.deleteCourse(courseId);

        // Verify that deleteCourse was called once with the given courseId
        Mockito.verify(courseRepo, Mockito.times(1)).deleteCourse(courseId);
    }

    @Test
    public void testGetRecommendedCourses() {   
        List<Course> dummyCourses = List.of(
            new Course("Java Basics", "Learn the basics of Java programming.", 4),
            new Course("Advanced Java", "Deep dive into advanced Java topics.", 5)
        );
        Mockito.when(courseRecommender.recommendCourses()).thenReturn(dummyCourses);

        List<Course> result = courseService.getRecommendedCourses();
        assert(result == dummyCourses);
        
        Mockito.verify(courseRecommender, Mockito.times(1)).recommendCourses();
    }
}
