package com.example.courserecommender.model;

public class Course {
    private String id;
    private String name;
    private String description;
    private int credit;

    public Course(String name, String description, int credit) {
        this.name = name;
        this.description = description;
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit(){
        return this.credit;
    }

    public void setCredit(int credit){
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
