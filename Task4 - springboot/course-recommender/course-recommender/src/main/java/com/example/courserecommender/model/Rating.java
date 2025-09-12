package com.example.courserecommender.model;

public class Rating {
    private String id;
    private int number; 
    private Course course;

    public Rating() {}

    public Rating(int number, Course course) {
        this.number = number;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
