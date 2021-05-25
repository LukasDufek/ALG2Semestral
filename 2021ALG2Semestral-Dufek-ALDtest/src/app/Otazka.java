/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Lukáš
 */
public class Otazka {
    private String question;
    private String correctAnswer;
    private String answerA;
    private String answerB;
    private String answerC;

    public Otazka(String question, String correctAnswer, String answerA, String answerB, String answerC) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    @Override
    public String toString() {
        
        return question + "\n" + answerA + "\n" + answerB + "\n" + answerC +"\n";
    }
    
    
    
    
    
}