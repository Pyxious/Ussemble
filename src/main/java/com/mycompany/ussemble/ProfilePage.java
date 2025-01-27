/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.ussemble;

import static com.mycompany.ussemble.OverviewPage.displayUsername;

/**
 *
 * @author ACER
 */
public class ProfilePage extends javax.swing.JFrame {

    /**
     * Creates new form 
     */
    public ProfilePage() {
        initComponents();
        displayUsername.setText(Login.currentUsername);
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
        jDialog1 = new javax.swing.JDialog();
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
        jPanel4 = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JTextField();
        displayUsername = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        backIcon = new javax.swing.JButton();
        addNewSetLabel = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        headerprofileIcon = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profile");
        setResizable(false);

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

        studyBtn.setBackground(new java.awt.Color(229, 238, 255));
        studyBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        studyBtn.setForeground(new java.awt.Color(0, 55, 158));
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

        profileBtn.setBackground(new java.awt.Color(0, 55, 158));
        profileBtn.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        profileBtn.setForeground(new java.awt.Color(229, 238, 255));
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
                .addContainerGap(238, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        usernameLabel.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(0, 55, 158));
        usernameLabel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        usernameLabel.setText("Username");
        usernameLabel.setBorder(null);

        displayUsername.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        displayUsername.setForeground(new java.awt.Color(0, 55, 158));
        displayUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        displayUsername.setText("-");
        displayUsername.setBorder(null);
        displayUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayUsernameActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(229, 238, 255));

        backIcon.setBackground(new java.awt.Color(255, 255, 204));
        backIcon.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        backIcon.setForeground(new java.awt.Color(0, 55, 158));
        backIcon.setBorder(null);
        backIcon.setBorderPainted(false);
        backIcon.setContentAreaFilled(false);
        backIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backIconActionPerformed(evt);
            }
        });

        addNewSetLabel.setBackground(new java.awt.Color(229, 238, 255));
        addNewSetLabel.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        addNewSetLabel.setForeground(new java.awt.Color(0, 55, 158));
        addNewSetLabel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        addNewSetLabel.setText("Profile");
        addNewSetLabel.setBorder(null);
        addNewSetLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewSetLabelActionPerformed(evt);
            }
        });

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 55, 158), 2, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(headerprofileIcon)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(headerprofileIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addGap(4, 4, 4)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(backIcon)
                        .addGap(269, 269, 269)
                        .addComponent(addNewSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(addNewSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(displayUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(300, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(sidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(692, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

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
    }//GEN-LAST:event_flashcardsBtnActionPerformed

    private void studyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studyBtnActionPerformed
        // TODO add your handling code here:
        studySetPage study = new studySetPage();
        study.setVisible(true);
        setVisible(false);
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

    }//GEN-LAST:event_logOutIcon1ActionPerformed

    private void backIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backIconActionPerformed
        // TODO add your handling code here:
        OverviewPage overview = new OverviewPage();
        overview.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_backIconActionPerformed

    private void addNewSetLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewSetLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addNewSetLabelActionPerformed

    private void displayUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_displayUsernameActionPerformed

   public void setDisplayUsername(String username) {
    displayUsername.setText(username);
}
    
    
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            public void run() {
                new ProfilePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addNewSetLabel;
    private javax.swing.JButton backIcon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField displayUsername;
    private javax.swing.JButton flashcardsBtn;
    private javax.swing.JButton flashcardsIcon;
    private javax.swing.JLabel headerprofileIcon;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JButton logOutIcon;
    private javax.swing.JButton logOutIcon1;
    private javax.swing.JButton overviewBtn;
    private javax.swing.JButton overviewIcon;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton profileIcon;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JButton studyBtn;
    private javax.swing.JTextField usernameLabel;
    // End of variables declaration//GEN-END:variables
}
