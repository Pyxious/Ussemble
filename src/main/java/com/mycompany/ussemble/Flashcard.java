/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ussemble;

/**
 *
 * @author Justin
 */
class Flashcard {
    private final String difficulty;
    private final String question;
    private final String answer;

    // Constructor
    public Flashcard(String difficulty, String question, String answer) {
        this.difficulty = difficulty;
        this.question = question;
        this.answer = answer;
    }

    // Getters
    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    // Method to display the flashcard (can be overridden in subclasses)
    public void displayCard() {
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Question: " + question);
        System.out.println("Answer: " + answer);
    }
}
