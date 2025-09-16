package com.example.courserecommender.model;

import java.io.Serializable;
import java.util.Objects;

public class CourseAuthorKey implements Serializable {
    private String courseId;
    private String authorId;

    // Constructors
    public CourseAuthorKey() {}

    public CourseAuthorKey(String courseId, String authorId) {
        this.courseId = courseId;
        this.authorId = authorId;
    }

    // Getters & Setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, authorId);
    }
}
