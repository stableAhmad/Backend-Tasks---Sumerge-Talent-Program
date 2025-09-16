package com.example.courserecommender.controller;

import com.example.courserecommender.controller.CourseController;
import com.example.courserecommender.model.Course;
import com.example.courserecommender.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
    ----------------------MY NOTES-----------------------

    steps for now
        1) determine the dependencies (other units) of the unit you wanna test class/mehtod
        2) create mock objects for those dependencies
        4) define the behavior of the mock objects (what to return when certain methods are called)

        3) create an instance of the class under test, injecting the mock dependencies
        5) invoke the method to be tested

        2 types of assertions 
            6) assert the results
            7) verify that the mock objects were called as expected
        

    @Test : before any Test methods, notifies JUnit
    @Before :  exectues before each test methods , sets up mocks 
    @After :    "        after   "    "   " , cleans mocks 

    @BeforeEach
    @AfterEach
    @BeforeAll : static method, runs once before all tests
    @AfterAll  : static method, runs once after all tests

    assertions 
        - assertEquals
        - assertNotNull
        - assertTrue
        - assertFalse
        - assertNull
        - assertArraysEquals
        - assertThrows
        - assertIterableEquals
        - assertTimeout
        - assertAll
    
    ()=> assertEquals(x,y) // preferred for large objects, lambda is executed only if assertion fails

    @InjectMocks : creates instance of class and injects mocks into it
 


    testing setup
        1) use this annotation to enable Mockito in JUnit 5
            @ExtendWith(MockitoExtension.class)
        2) @Mock : creates fake version of a class/interface
        3) @InjectMocks : creates real instance of the class, and uses the @Mock as injections 

    difference between real objects (@InjectMocks) and fake/mock objects (@Mock)
        - real objects
            real objects that interact with the database
        - mock/fake objects
            fake objects that simulate the behavior of real objects
            used to isolate the class under test
            can be programmed to return specific values when methods are called
            can verify if certain methods were called, how many times, with what arguments
            Mock Objects
            Fake versions created by Mockito (@Mock or Mockito.mock(Class.class)).
            They don’t run real logic — instead, they return whatever you tell them to return.
            By default, if you don’t tell them anything, they return:
            null for objects
            0 for numbers
            false for booleans
            empty lists/maps for collections
        
    - why create mock objects ? 
        - since all the methods they have return dummy value, they dont' do real effect on DB
        - you need them to just stand there and make sure they were invoked 
        
        - real objects have actual logic, mock objects return default values (null, 0, false) unless specified otherwise
        - real objects can call methods, mock objects cannot unless specified otherwise
        - real objects are used to test the logic, mock objects are used to isolate the class under test
 */

@ExtendWith(MockitoExtension.class) // enables Mockito in JUnit 5
class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @Test
    void testGetCourses() {
        // dummy value returned by the mock object
        List<Course> mockCourses = Arrays.asList(new Course("Spring Boot", "Learn Spring Boot", 3));
        when(courseService.getRecommendedCourses()).thenReturn(mockCourses);

        // invoke/simulate the tested method (GetCourse)
        List<Course> result = courseController.getCourses();

        // Assert: check that controller gave us the mock data
        assertEquals(1, result.size());
        assertEquals("Spring Boot", result.get(0).getName());

        // Verify: make sure controller actually called the service
        verify(courseService, times(1)).getRecommendedCourses();
    }

    @Test
    void testGetCourseById() {
        Course mockCourse = new Course("Java Basics", "Learn Java", 4);
        mockCourse.setId("1");
        when(courseService.viewCourse(1)).thenReturn(mockCourse);

        Course result = courseController.getCourseById(1);

        assertNotNull(result);
        assertEquals("Java Basics", result.getName());

        verify(courseService, times(1)).viewCourse(1);
    }

    @Test
    void testAddCourse() {
        Course newCourse = new Course("Python Basics", "Learn Python", 4);

        // invoke the method to be tested
        courseController.addCourse(newCourse);

        // no return value needed here

        // verify that the service method was called with the correct argument
        verify(courseService, times(1)).addCourse(newCourse);
    }
    @Test
    void testUpdateCourse() {
        int courseId = 1;
        courseController.updateCourse(courseId, new Course("Updated Course", "Updated Description", 5));
        verify(courseService, times(1)).updateCourse(courseId, new Course("Updated Course", "Updated Description", 5));
    }

    @Test
    void testDeleteCourse() {
        int courseId = 1;
        courseController.deleteCourse(courseId);
        verify(courseService, times(1)).deleteCourse(courseId);
    }
}
