package com.mycompany.quiz;

public class Question {
    private String questionText;
    private String[] options;
    private String correctOption;

    Question(String questionText, String[] options, String correctOption){
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String text){
        this.questionText = text;
    }

    public String[] getOptions(){
        return options;
    }
    public void setOptions(String[] options){
        this.options = options;
    }

    public boolean isCorrect(String answer){
        return answer.equals(correctOption);
    }

}
