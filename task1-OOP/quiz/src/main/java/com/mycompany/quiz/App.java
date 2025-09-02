package com.mycompany.quiz;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz Application!");
        System.out.print("Please enter your name to start the quiz... : ");
        String username = System.console().readLine();
        User user = new User(username);
        Quiz quiz = new Quiz(user);
        quiz.setOptions(populate());
        while(true){
            System.out.print("to start the quiz type start, to exit type exit : ");
            String input = System.console().readLine();
            if(input.equals("start")){
                System.out.println("Starting the quiz...");
                for(Question q : quiz.getQuestions()){
                    System.out.println(q.getQuestionText());
                    String[] options = q.getOptions();
                    for(int i = 0; i < options.length; i++){
                        System.out.println((i+1) + ". " + options[i]);
                    }
                    while(true){
                        System.out.print("Your answer (type the option number): ");
                        String answer = System.console().readLine();
                        int answerIndex;
                        try {
                            answerIndex = Integer.parseInt(answer) - 1;
                            if(answerIndex < 0 || answerIndex >= options.length){
                                System.out.println("Invalid option. Please try again.");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                            continue;
                        }
                        if(q.isCorrect(options[answerIndex])){
                            quiz.increment();
                        }
                        break;
                    }
                }
                System.out.println("Quiz finished! Your score is: " + quiz.getScore() + " out of " + quiz.getQuestions().length);
            } else if(input.equals("exit")){
                System.out.println("Exiting the quiz. Goodbye!");
                break;
            } else {
                System.out.println("Invalid input. Please type 'start' or 'exit'.");
            }
        }
    }
    private static Question[] populate(){
        Question q1 = new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, "Paris");
        Question q2 = new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, "4");
        Question q3 = new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, "Jupiter");
        Question q4 = new Question("Who wrote 'To be, or not to be'?", new String[]{"Charles Dickens", "Mark Twain", "William Shakespeare", "Jane Austen"}, "William Shakespeare");
        Question q5 = new Question("What is the boiling point of water?", new String[]{"90°C", "100°C", "110°C", "120°C"}, "100°C");
        Question q6 = new Question("What is the chemical symbol for gold?", new String[]{"Au", "Ag", "Fe", "Pb"}, "Au");
        Question[] questions = new Question[]{q1, q2, q3, q4, q5, q6};
        return questions;
    }
}
