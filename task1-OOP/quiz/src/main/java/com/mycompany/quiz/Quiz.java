package com.mycompany.quiz;

public class Quiz {
    private int score;
    private User user;
    private Question[] questions;

    Quiz(User user){
        this.score = 0;
        this.user = user;
    }

    public Question[] getQuestions(){
        return this.questions;
    }
    public void setOptions(Question[] questions){
        this.questions = questions;
    }
    public int getScore(){
        return score;
    }
    public void increment(){
        this.score++;
    }

}
