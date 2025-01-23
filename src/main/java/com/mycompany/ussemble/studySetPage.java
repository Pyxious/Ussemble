/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ussemble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ACER
 */
public class studySetPage extends javax.swing.JFrame {


    /**
     * Creates new form 
     */
    public studySetPage() {
        initComponents();
    }
    
    private void loadFlashcards(String fileName) {
        flashcards = parseFlashcards(fileName);
    }
    
    private List<Flashcard> parseFlashcards(String fileName) {
            List<Flashcard> flashcards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String difficulty = null, question = null, answer = null;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Difficulty: ")) {
                difficulty = line.substring(12).trim(); // Get difficulty and trim spaces
            } else if (line.startsWith("Question: ")) {
                question = line.substring(10).trim(); // Get question and trim spaces
            } else if (line.startsWith("Answer: ")) {
                answer = line.substring(8).trim(); // Get answer and trim spaces
            } else if (line.equals("-------------------------")) { // Match separator
                // Validate and add the flashcard
                if (difficulty != null && question != null && answer != null) {
                    Flashcard flashcard;
                    flashcard = switch (difficulty.toLowerCase()) {
                        case "Easy" -> new EasyFlashcard(question, answer);
                        case "Moderate" -> new ModerateFlashcard(question, answer);
                        case "Difficult" -> new DifficultFlashcard(question, answer);
                        default -> new Flashcard(difficulty, question, answer);
                    }; // Convert difficulty to lowercase for comparison
                    // Fallback for unknown difficulties
                    flashcards.add(flashcard);
                }
                // Reset fields for the next flashcard
                difficulty = null;
                question = null;
                answer = null;
            }
        }
    } catch (IOException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
    }
    return flashcards;
}

    
    private List<Flashcard> filterFlashcardsByDifficulty(String difficulty) {
         return flashcards.stream()
            .filter(flashcard -> flashcard.getDifficulty().equalsIgnoreCase(difficulty)) // Case-insensitive matching
            .collect(Collectors.toList());
}
    
    
    private void enableAnswerFields(int count) {
    // Enable or disable fields based on the count
    answerFieldLabel.setEnabled(count >= 1); // Always enable on Easy, Moderate, and Difficult
    answerFieldLabel1.setEnabled(count >= 2); // Enabled for Moderate and Difficult
    answerFieldLabel2.setEnabled(count >= 3); // Enabled for Difficult
    answerFieldLabel3.setEnabled(count >= 4); // Enabled only for Difficult

    // Optionally clear unused fields for consistency
    if (count < 2) answerFieldLabel1.setText("");
    if (count < 3) answerFieldLabel2.setText("");
    if (count < 4) answerFieldLabel3.setText("");
}
    

    private List<Flashcard> currentFlashcards = new ArrayList<>(); // Holds the flashcards of the current selected difficulty
    private int currentIndex = 0; // To track the current card in the current difficulty list
    private boolean questionAnswered = false; 
    
    
    private void displayCard(List<Flashcard> flashcards) {
   if (flashcards.isEmpty()) return; // Safety check

    Flashcard currentCard = flashcards.get(currentIndex);

    // Set the question
    jTextField1.setText(currentCard.getQuestion());
    
    
    currentCard.displayCard();

    // Get the correct answer
    String correctAnswer = currentCard.getAnswer();

    // Filter wrong answers from flashcards with the same difficulty
    String currentDifficulty = currentCard.getDifficulty();
    List<String> sameDifficultyAnswers = flashcards.stream()
            .filter(flashcard -> flashcard.getDifficulty().equalsIgnoreCase(currentDifficulty))
            .map(Flashcard::getAnswer)
            .filter(answer -> !answer.equals(correctAnswer))
            .distinct()
            .collect(Collectors.toList());

    // Shuffle wrong answers
    Collections.shuffle(sameDifficultyAnswers);

    // Determine the number of options based on difficulty
    int maxOptions;
    if (currentDifficulty.equalsIgnoreCase("Easy")) {
        maxOptions = 2;
    } else if (currentDifficulty.equalsIgnoreCase("Moderate")) {
        maxOptions = 3;
    } else { // Difficult
        maxOptions = 4;
    }

    // Prepare the answer options
    List<String> options = new ArrayList<>(sameDifficultyAnswers.subList(0, Math.min(sameDifficultyAnswers.size(), maxOptions - 1)));
    Random random = new Random();

    // Ensure the correct answer is placed in the appropriate position based on difficulty
    if (currentDifficulty.equalsIgnoreCase("Easy")) {
        int correctPosition = random.nextInt(2); // Place correct answer in 0 or 1
        if (correctPosition == 0) {
            options.add(0, correctAnswer);
        } else {
            options.add(correctAnswer);
        }
        while (options.size() < 2) options.add(""); // Fill empty slots if needed
    } else if (currentDifficulty.equalsIgnoreCase("Moderate")) {
        int correctPosition = random.nextInt(3); // Place correct answer in 0, 1, or 2
        options.add(correctPosition, correctAnswer);
        while (options.size() < 3) options.add(""); // Fill empty slots if needed
    } else { // Difficult
        int correctPosition = random.nextInt(4); // Place correct answer in 0, 1, 2, or 3
        options.add(correctPosition, correctAnswer);
        while (options.size() < 4) options.add(""); // Fill empty slots if needed
    }

    // Display answers in JLabels
    answerFieldLabel.setText(!options.isEmpty() ? options.get(0) : "");
    answerFieldLabel1.setText(options.size() > 1 ? options.get(1) : "");
    answerFieldLabel2.setText(options.size() > 2 ? options.get(2) : "");
    answerFieldLabel3.setText(options.size() > 3 ? options.get(3) : "");

    // Reset button states
    questionAnswered = false;
    nextCardBtn.setEnabled(false);
    RetryCardBtn.setEnabled(false); 
}
    
    private void answerLabelClicked(String selectedAnswer, String correctAnswer) {
    if (questionAnswered) return; // Ignore if already answered

    questionAnswered = true;

    // Check against the correct answer from currentFlashcards
    Flashcard currentCard = currentFlashcards.get(currentIndex);
    if (selectedAnswer.equals(currentCard.getAnswer())) {
        javax.swing.JOptionPane.showMessageDialog(this, "Correct!");
        RetryCardBtn.setEnabled(false); // Disable retry on correct answer
        nextCardBtn.setEnabled(true); // Enable the next button
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Wrong! Try again.");
        RetryCardBtn.setEnabled(true); // Enable retry on incorrect answer
    }
}

    


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jOptionPane1 = new javax.swing.JOptionPane();
        backgroundPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        sidebarPanel = new javax.swing.JPanel();
        overviewBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        flashcardsBtn = new javax.swing.JButton();
        studyBtn = new javax.swing.JButton();
        profileIcon = new javax.swing.JButton();
        flashcardsIcon = new javax.swing.JButton();
        overviewIcon = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        profileBtn = new javax.swing.JButton();
        logOutIcon = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        logOutIcon1 = new javax.swing.JButton();
        addNewSetPanel = new javax.swing.JPanel();
        chooseSetBtn = new javax.swing.JButton();
        editSetHeaderPanel = new javax.swing.JPanel();
        editSetLabel = new javax.swing.JTextField();
        finishSetBtn = new javax.swing.JButton();
        difficultyPanel = new javax.swing.JPanel();
        dificultyLabel = new javax.swing.JTextField();
        easyBtn = new javax.swing.JButton();
        moderateBtn = new javax.swing.JButton();
        difficultBtn = new javax.swing.JButton();
        RetryCardBtn = new javax.swing.JButton();
        nextCardBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        displayQuestionLabel = new javax.swing.JTextField();
        displayAnswerLabel = new javax.swing.JTextField();
        answerFieldLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        answerFieldLabel3 = new javax.swing.JLabel();
        answerFieldLabel1 = new javax.swing.JLabel();
        answerFieldLabel2 = new javax.swing.JLabel();
        prevCardBtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundPanel.setBackground(new java.awt.Color(255, 255, 255));
        backgroundPanel.setForeground(new java.awt.Color(0, 55, 158));
        backgroundPanel.setMaximumSize(new java.awt.Dimension(960, 540));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(960, 540));

        sidebarPanel.setBackground(new java.awt.Color(229, 238, 255));

        overviewBtn.setBackground(new java.awt.Color(229, 238, 255));
        overviewBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        overviewBtn.setForeground(new java.awt.Color(0, 55, 158));
        overviewBtn.setText("Overview");
        overviewBtn.setBorder(null);
        overviewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overviewBtnActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aaa new logo final.png"))); // NOI18N

        flashcardsBtn.setBackground(new java.awt.Color(229, 238, 255));
        flashcardsBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        flashcardsBtn.setForeground(new java.awt.Color(0, 55, 158));
        flashcardsBtn.setText("Flashcards");
        flashcardsBtn.setBorder(null);
        flashcardsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flashcardsBtnActionPerformed(evt);
            }
        });

        studyBtn.setBackground(new java.awt.Color(0, 55, 158));
        studyBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        studyBtn.setForeground(new java.awt.Color(229, 238, 255));
        studyBtn.setText("Study");
        studyBtn.setBorder(null);
        studyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studyBtnActionPerformed(evt);
            }
        });

        profileIcon.setBackground(new java.awt.Color(255, 255, 204));
        profileIcon.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        profileIcon.setForeground(new java.awt.Color(0, 55, 158));
        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/studyIcon.png"))); // NOI18N
        profileIcon.setBorder(null);
        profileIcon.setBorderPainted(false);
        profileIcon.setContentAreaFilled(false);
        profileIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileIconActionPerformed(evt);
            }
        });

        flashcardsIcon.setBackground(new java.awt.Color(255, 255, 204));
        flashcardsIcon.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        flashcardsIcon.setForeground(new java.awt.Color(0, 55, 158));
        flashcardsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aaa flashcards.png"))); // NOI18N
        flashcardsIcon.setBorder(null);
        flashcardsIcon.setBorderPainted(false);
        flashcardsIcon.setContentAreaFilled(false);
        flashcardsIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flashcardsIconActionPerformed(evt);
            }
        });

        overviewIcon.setBackground(new java.awt.Color(255, 255, 204));
        overviewIcon.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        overviewIcon.setForeground(new java.awt.Color(0, 55, 158));
        overviewIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/homeIcon.png"))); // NOI18N
        overviewIcon.setBorder(null);
        overviewIcon.setBorderPainted(false);
        overviewIcon.setContentAreaFilled(false);
        overviewIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overviewIconActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 55, 158));

        profileBtn.setBackground(new java.awt.Color(229, 238, 255));
        profileBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        profileBtn.setForeground(new java.awt.Color(0, 55, 158));
        profileBtn.setText("Profile");
        profileBtn.setBorder(null);
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        logOutIcon.setBackground(new java.awt.Color(255, 255, 204));
        logOutIcon.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        logOutIcon.setForeground(new java.awt.Color(0, 55, 158));
        logOutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aaa profile.png"))); // NOI18N
        logOutIcon.setBorder(null);
        logOutIcon.setBorderPainted(false);
        logOutIcon.setContentAreaFilled(false);
        logOutIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutIconActionPerformed(evt);
            }
        });

        logOutBtn.setBackground(new java.awt.Color(229, 238, 255));
        logOutBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        logOutBtn.setForeground(new java.awt.Color(0, 55, 158));
        logOutBtn.setText("Log Out");
        logOutBtn.setBorder(null);
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });

        logOutIcon1.setBackground(new java.awt.Color(255, 255, 204));
        logOutIcon1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        logOutIcon1.setForeground(new java.awt.Color(0, 55, 158));
        logOutIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aaa log out button.png"))); // NOI18N
        logOutIcon1.setBorder(null);
        logOutIcon1.setBorderPainted(false);
        logOutIcon1.setContentAreaFilled(false);
        logOutIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutIcon1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarPanelLayout.createSequentialGroup()
                        .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sidebarPanelLayout.createSequentialGroup()
                                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(flashcardsIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                    .addComponent(profileIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                    .addComponent(logOutIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                    .addComponent(logOutIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                    .addComponent(overviewIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(flashcardsBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                        .addComponent(overviewBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(studyBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)))
                .addGap(30, 30, 30))
        );
        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(overviewIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(overviewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(flashcardsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flashcardsIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebarPanelLayout.createSequentialGroup()
                        .addComponent(logOutIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logOutIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidebarPanelLayout.createSequentialGroup()
                        .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(286, Short.MAX_VALUE))
        );

        addNewSetPanel.setBackground(new java.awt.Color(255, 255, 255));

        chooseSetBtn.setBackground(new java.awt.Color(255, 255, 204));
        chooseSetBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        chooseSetBtn.setForeground(new java.awt.Color(0, 55, 158));
        chooseSetBtn.setText("Open Folder to Choose A Set");
        chooseSetBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chooseSetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseSetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addNewSetPanelLayout = new javax.swing.GroupLayout(addNewSetPanel);
        addNewSetPanel.setLayout(addNewSetPanelLayout);
        addNewSetPanelLayout.setHorizontalGroup(
            addNewSetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewSetPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chooseSetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        addNewSetPanelLayout.setVerticalGroup(
            addNewSetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewSetPanelLayout.createSequentialGroup()
                .addComponent(chooseSetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editSetHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));

        editSetLabel.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        editSetLabel.setForeground(new java.awt.Color(0, 55, 158));
        editSetLabel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        editSetLabel.setText("Study Set");
        editSetLabel.setBorder(null);
        editSetLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSetLabelActionPerformed(evt);
            }
        });

        finishSetBtn.setBackground(new java.awt.Color(0, 55, 158));
        finishSetBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        finishSetBtn.setForeground(new java.awt.Color(229, 238, 255));
        finishSetBtn.setText("Finish Studying");
        finishSetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishSetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editSetHeaderPanelLayout = new javax.swing.GroupLayout(editSetHeaderPanel);
        editSetHeaderPanel.setLayout(editSetHeaderPanelLayout);
        editSetHeaderPanelLayout.setHorizontalGroup(
            editSetHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSetHeaderPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(editSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addComponent(finishSetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(60, 60, 60))
        );
        editSetHeaderPanelLayout.setVerticalGroup(
            editSetHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editSetHeaderPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(finishSetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editSetHeaderPanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(editSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        difficultyPanel.setBackground(new java.awt.Color(255, 255, 255));

        dificultyLabel.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        dificultyLabel.setForeground(new java.awt.Color(0, 55, 158));
        dificultyLabel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dificultyLabel.setText("Difficulty :");
        dificultyLabel.setBorder(null);
        dificultyLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dificultyLabelActionPerformed(evt);
            }
        });

        easyBtn.setBackground(new java.awt.Color(204, 255, 204));
        easyBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        easyBtn.setForeground(new java.awt.Color(0, 55, 158));
        easyBtn.setText("Easy");
        buttonGroup1.add(easyBtn);
        easyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyBtnActionPerformed(evt);
            }
        });

        moderateBtn.setBackground(new java.awt.Color(255, 204, 153));
        moderateBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        moderateBtn.setForeground(new java.awt.Color(0, 55, 158));
        moderateBtn.setText("Moderate");
        buttonGroup1.add(moderateBtn);
        moderateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moderateBtnActionPerformed(evt);
            }
        });

        difficultBtn.setBackground(new java.awt.Color(255, 153, 153));
        difficultBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        difficultBtn.setForeground(new java.awt.Color(0, 55, 158));
        difficultBtn.setText("Difficult");
        buttonGroup1.add(difficultBtn);
        difficultBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                difficultBtnActionPerformed(evt);
            }
        });

        RetryCardBtn.setBackground(new java.awt.Color(229, 238, 255));
        RetryCardBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        RetryCardBtn.setForeground(new java.awt.Color(0, 55, 158));
        RetryCardBtn.setText("Retry");
        RetryCardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetryCardBtnActionPerformed(evt);
            }
        });

        nextCardBtn.setBackground(new java.awt.Color(229, 238, 255));
        nextCardBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        nextCardBtn.setForeground(new java.awt.Color(0, 55, 158));
        nextCardBtn.setText("Next Card");
        nextCardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextCardBtnActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        displayQuestionLabel.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        displayQuestionLabel.setForeground(new java.awt.Color(0, 55, 158));
        displayQuestionLabel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayQuestionLabel.setText("Question:");
        displayQuestionLabel.setBorder(null);
        displayQuestionLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayQuestionLabelActionPerformed(evt);
            }
        });

        displayAnswerLabel.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        displayAnswerLabel.setForeground(new java.awt.Color(0, 55, 158));
        displayAnswerLabel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayAnswerLabel.setText("Answer:");
        displayAnswerLabel.setBorder(null);
        displayAnswerLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayAnswerLabelActionPerformed(evt);
            }
        });

        answerFieldLabel.setBackground(new java.awt.Color(229,238,255));
        answerFieldLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 55, 158), 1, true));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        answerFieldLabel3.setBackground(new java.awt.Color(229,238,255));
        answerFieldLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 55, 158), 1, true));

        answerFieldLabel1.setBackground(new java.awt.Color(229,238,255));
        answerFieldLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 55, 158), 1, true));

        answerFieldLabel2.setBackground(new java.awt.Color(229,238,255));
        answerFieldLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 55, 158), 1, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(answerFieldLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(answerFieldLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayQuestionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(displayAnswerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(answerFieldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(answerFieldLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(displayQuestionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayAnswerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(answerFieldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(answerFieldLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(answerFieldLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(answerFieldLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        answerFieldLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerLabelClicked(answerFieldLabel.getText(), flashcards.get(currentIndex).getAnswer());
            }
        });

        answerFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
        answerFieldLabel.setVerticalAlignment(SwingConstants.CENTER);

        answerFieldLabel.setOpaque(true);
        answerFieldLabel1.setOpaque(true);
        answerFieldLabel2.setOpaque(true);
        answerFieldLabel3.setOpaque(true);
        jTextField1.setEnabled(false);
        answerFieldLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerLabelClicked(answerFieldLabel2.getText(), flashcards.get(currentIndex).getAnswer());
            }
        });

        answerFieldLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        answerFieldLabel2.setVerticalAlignment(SwingConstants.CENTER);
        answerFieldLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerLabelClicked(answerFieldLabel1.getText(), flashcards.get(currentIndex).getAnswer());
            }
        });

        answerFieldLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        answerFieldLabel1.setVerticalAlignment(SwingConstants.CENTER);
        answerFieldLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerLabelClicked(answerFieldLabel3.getText(), flashcards.get(currentIndex).getAnswer());
            }
        });

        answerFieldLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        answerFieldLabel3.setVerticalAlignment(SwingConstants.CENTER);

        prevCardBtn.setBackground(new java.awt.Color(229, 238, 255));
        prevCardBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        prevCardBtn.setForeground(new java.awt.Color(0, 55, 158));
        prevCardBtn.setText("Previous Card");
        prevCardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevCardBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout difficultyPanelLayout = new javax.swing.GroupLayout(difficultyPanel);
        difficultyPanel.setLayout(difficultyPanelLayout);
        difficultyPanelLayout.setHorizontalGroup(
            difficultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(difficultyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dificultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(easyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moderateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(difficultBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(difficultyPanelLayout.createSequentialGroup()
                .addComponent(prevCardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RetryCardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nextCardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        difficultyPanelLayout.setVerticalGroup(
            difficultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, difficultyPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(difficultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(easyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dificultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moderateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(difficultBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(difficultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextCardBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(difficultyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RetryCardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(prevCardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addComponent(sidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editSetHeaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addNewSetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(difficultyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(62, 62, 62))))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(sidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel3))
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editSetHeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addNewSetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(difficultyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sidebarPanel.getAccessibleContext().setAccessibleName("sideBar");
        addNewSetPanel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.Alignment.TRAILING, 966, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        backgroundPanel.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void overviewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overviewBtnActionPerformed
        // TODO add your handling code here:
        OverviewPage overview = new OverviewPage();
        overview.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_overviewBtnActionPerformed

    private void flashcardsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flashcardsBtnActionPerformed
        // TODO add your handling code here:
        FlashcardsPage flashcard = new FlashcardsPage();
        flashcard.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_flashcardsBtnActionPerformed

    private void studyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studyBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studyBtnActionPerformed

    private void profileIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileIconActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profileIconActionPerformed

    private void flashcardsIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flashcardsIconActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flashcardsIconActionPerformed

    private void overviewIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overviewIconActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_overviewIconActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        // TODO add your handling code here:
        ProfilePage profile = new ProfilePage();
        profile.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_profileBtnActionPerformed

    private void logOutIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutIconActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logOutIconActionPerformed
    
    private List<Flashcard> flashcards;
    private void chooseSetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseSetBtnActionPerformed
       // Define paths for Documents and Downloads folders
    String documentsPath = "Documents\\NetBeansProjects\\Ussemble\\Flashcards\\" + Login.currentUsername;
    String downloadsPath = "Downloads\\Ussemble\\Flashcards\\" + Login.currentUsername;
    String userHomePath = System.getProperty("user.home"); // Get the user's home directory

    // Construct File objects for both paths
    File documentsFolder = new File(userHomePath, documentsPath);
    File downloadsFolder = new File(userHomePath, downloadsPath);

    // Determine which folder to use
    File userFolder;
    if (documentsFolder.exists()) {
        userFolder = documentsFolder;
        System.out.println("Using Documents folder: " + userFolder.getAbsolutePath());
    } else {
        userFolder = downloadsFolder;
        if (!downloadsFolder.exists()) {
            if (downloadsFolder.mkdirs()) {
                System.out.println("Fallback folder created at: " + downloadsFolder.getAbsolutePath());
            } else {
                System.err.println("Failed to create fallback folder. Using home directory instead.");
                userFolder = new File(userHomePath); // Fallback to home directory
            }
        }
    }

    // Initialize the JFileChooser with the determined folder
    JFileChooser fileChooser = new JFileChooser(userFolder);

    // Add a filter to allow only text files
    FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
    fileChooser.setFileFilter(textFilter);
    fileChooser.setAcceptAllFileFilterUsed(false); // Optional: Disable "All Files" filter

    // Show the open dialog
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if (file.getName().endsWith(".txt")) {
            loadFlashcards(file.getAbsolutePath());
            javax.swing.JOptionPane.showMessageDialog(this, "Flashcards loaded successfully!");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a valid text file!", "Invalid File", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_chooseSetBtnActionPerformed

    private void easyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyBtnActionPerformed
        currentFlashcards = filterFlashcardsByDifficulty("Easy");
    if (!currentFlashcards.isEmpty()) {
        currentIndex = 0; // Reset index for new difficulty
        displayCard(currentFlashcards);
        enableAnswerFields(2); // Enable only two answer fields
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "No Easy flashcards found!");
    }
    }//GEN-LAST:event_easyBtnActionPerformed

    private void moderateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moderateBtnActionPerformed
        currentFlashcards = filterFlashcardsByDifficulty("Moderate");
    if (!currentFlashcards.isEmpty()) {
        currentIndex = 0;
        displayCard(currentFlashcards);
        enableAnswerFields(3); // Enable three answer fields
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "No Moderate flashcards found!");
    }
    }//GEN-LAST:event_moderateBtnActionPerformed

    private void difficultBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_difficultBtnActionPerformed
        currentFlashcards = filterFlashcardsByDifficulty("Difficult");
    if (!currentFlashcards.isEmpty()) {
        currentIndex = 0;
        displayCard(currentFlashcards);
        enableAnswerFields(4); // Enable all four answer fields
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "No Difficult flashcards found!");
    }
    }//GEN-LAST:event_difficultBtnActionPerformed

    private void finishSetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishSetBtnActionPerformed
        if (currentFlashcards.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "No more flashcards in the set!");
        return;
    }
        // Remove the current flashcard


    // Check if there are any flashcards left in the set
    if (currentFlashcards.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "You have completed this set!");
        resetStudyState();
        return;
    }

    // Show the option to exit studying
    int choice = javax.swing.JOptionPane.showConfirmDialog(this, 
        "Do you want to exit studying this set?", 
        "Exit Studying", 
        javax.swing.JOptionPane.YES_NO_OPTION);

    if (choice == javax.swing.JOptionPane.YES_OPTION) {
        // User chose to exit studying
        resetStudyState();
    } else {
        // User chose to continue
        if (currentIndex >= currentFlashcards.size()) {
            currentIndex = 0; // Reset index if it goes out of bounds
        }
        displayCard(currentFlashcards); // Show the next card
    }
    }//GEN-LAST:event_finishSetBtnActionPerformed

    
private void resetStudyState() {
    currentFlashcards.clear(); // Clear the current set
    currentIndex = 0; // Reset the index
    jTextField1.setText(""); // Clear the question field
    answerFieldLabel.setText(""); 
    answerFieldLabel1.setText(""); 
    answerFieldLabel2.setText(""); 
    answerFieldLabel3.setText("");
    questionAnswered = false;

    javax.swing.JOptionPane.showMessageDialog(this, "You can now choose a new set to study!");
    // Optionally, you can provide a method to load a new set here, e.g., loadFlashcards() or similar
}

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
       // Show a confirmation dialog
    int choice = javax.swing.JOptionPane.showConfirmDialog(
        this, 
        "Confirm LogOut?", 
        "Log Out", 
        javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (choice == javax.swing.JOptionPane.YES_OPTION) {
        // Redirect to the login page
        Login loginPage = new Login();
        loginPage.setVisible(true);
        this.dispose(); // Close the current window
    } else {
        // Do nothing; close the dialog automatically
        System.out.println("Log out canceled.");
    }
    }//GEN-LAST:event_logOutBtnActionPerformed

    private void logOutIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutIcon1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logOutIcon1ActionPerformed

    private void dificultyLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dificultyLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dificultyLabelActionPerformed

    private void editSetLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSetLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editSetLabelActionPerformed

    private void prevCardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevCardBtnActionPerformed
        // Go to the previous flashcard
    currentIndex--;

    // Ensure index stays within bounds
    if (currentIndex >= 0) {
        displayCard(currentFlashcards); // Display the previous flashcard

        // Reset and enable the correct number of answer fields based on difficulty
        if (currentFlashcards.get(currentIndex).getDifficulty().equalsIgnoreCase("Easy")) {
            enableAnswerFields(2); // Only enable 2 fields for Easy
        } else if (currentFlashcards.get(currentIndex).getDifficulty().equalsIgnoreCase("Moderate")) {
            enableAnswerFields(3); // Enable 3 fields for Moderate
        } else if (currentFlashcards.get(currentIndex).getDifficulty().equalsIgnoreCase("Difficult")) {
            enableAnswerFields(4); // Enable all 4 fields for Difficult
        }
    } else {
        // If the user tries to go before the first card
        currentIndex = 0; // Reset index to the first card
        javax.swing.JOptionPane.showMessageDialog(this, "You're already at the first card!");
    }
    }//GEN-LAST:event_prevCardBtnActionPerformed

    private void displayQuestionLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayQuestionLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_displayQuestionLabelActionPerformed

    private void displayAnswerLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayAnswerLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_displayAnswerLabelActionPerformed

    private void RetryCardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetryCardBtnActionPerformed
        // Reset the flashcard display for retry
    if (!currentFlashcards.isEmpty()) {
        displayCard(currentFlashcards); // Redisplay the current flashcard
    }
    nextCardBtn.setEnabled(false); // Disable the "Next" button again
    questionAnswered = false; // Reset the answered state
    RetryCardBtn.setEnabled(false); // Disable the Retry button after use
    }//GEN-LAST:event_RetryCardBtnActionPerformed

    private void nextCardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextCardBtnActionPerformed
        if (currentFlashcards != null && currentIndex < currentFlashcards.size() - 1) {
        currentIndex++; // Move to the next card
        displayCard(currentFlashcards); // Display the new card
        
        // Reapply the state of enabled/disabled fields based on the difficulty of the current section
        String currentDifficulty = currentFlashcards.get(currentIndex).getDifficulty();
        switch (currentDifficulty.toLowerCase()) {
            case "easy" -> enableAnswerFields(2); // Enable only two fields
            case "moderate" -> enableAnswerFields(3); // Enable three fields
            case "difficult" -> enableAnswerFields(4); // Enable all fields
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "No more flashcards in this section!");
    }
    }//GEN-LAST:event_nextCardBtnActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(studySetPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new studySetPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RetryCardBtn;
    private javax.swing.JPanel addNewSetPanel;
    private javax.swing.JLabel answerFieldLabel;
    private javax.swing.JLabel answerFieldLabel1;
    private javax.swing.JLabel answerFieldLabel2;
    private javax.swing.JLabel answerFieldLabel3;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton chooseSetBtn;
    private javax.swing.JButton difficultBtn;
    private javax.swing.JPanel difficultyPanel;
    private javax.swing.JTextField dificultyLabel;
    private javax.swing.JTextField displayAnswerLabel;
    private javax.swing.JTextField displayQuestionLabel;
    private javax.swing.JButton easyBtn;
    private javax.swing.JPanel editSetHeaderPanel;
    private javax.swing.JTextField editSetLabel;
    private javax.swing.JButton finishSetBtn;
    private javax.swing.JButton flashcardsBtn;
    private javax.swing.JButton flashcardsIcon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JButton logOutIcon;
    private javax.swing.JButton logOutIcon1;
    private javax.swing.JButton moderateBtn;
    private javax.swing.JButton nextCardBtn;
    private javax.swing.JButton overviewBtn;
    private javax.swing.JButton overviewIcon;
    private javax.swing.JButton prevCardBtn;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton profileIcon;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JButton studyBtn;
    // End of variables declaration//GEN-END:variables
}
