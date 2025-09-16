package com.example.courserecommender.model;

public class Assessment {
    private String id;
    private String content;
    private Course course;

    public Assessment() {}

    public Assessment(String content, Course course) {
        this.content = content;
        this.course = course;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
