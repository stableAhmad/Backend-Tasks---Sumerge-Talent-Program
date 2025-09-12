package com.example.courserecommender.model;

public class CourseAuthorJunction {
    private CourseAuthorKey id;
    private Course course;
    private Author author;

    // Constructors
    public CourseAuthorJunction() {}

    public CourseAuthorJunction(Course course, Author author) {
        this.course = course;
        this.author = author;
        // we use the ptr
        this.id = new CourseAuthorKey(course.getId(), author.getId());
    }

    // Getters & Setters
    public CourseAuthorKey getId() {
        return id;
    }

    public void setId(CourseAuthorKey id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
