/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package petsim;
//p
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
/**
 *
 * @author yuan
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     * 
     */
    
    // Global Variable Declaration
    
    public boolean cleanClick = false; // This is a boolean for the Clean button
    public boolean cleanInformation = false; // Information for cleaning
    public boolean sleepStatus = false;
    private SpriteSheetRenderer spriteRenderer;
    private JLabel broomLabel; // JLabel to hold the broom image
    private ImageIcon broomIcon;

    // Method to update the progress bars
    private void updateProgressBars() {
        jProgressBar1.setOrientation(SwingConstants.VERTICAL);
        jProgressBar2.setOrientation(SwingConstants.VERTICAL);
        jProgressBar3.setOrientation(SwingConstants.VERTICAL);
        jProgressBar4.setOrientation(SwingConstants.VERTICAL);
        jProgressBar5.setOrientation(SwingConstants.VERTICAL);
        jProgressBar1.setValue((int) PetSim.obj1.getHappinessLevel());
        jProgressBar2.setValue((int) PetSim.obj1.getHungerLevel());
        jProgressBar3.setValue((int) PetSim.obj1.getEnergyLevel());
        jProgressBar4.setValue((int) PetSim.obj1.getCleanlinessLevel());
        jProgressBar5.setValue((int) PetSim.obj1.getHealthLevel());
    }
    
    public void printStats() {
        System.out.println("\n\n|| " + PetSim.obj1.getName() + " stats ||");
        System.out.println("Hunger: " + PetSim.obj1.getHungerLevel());
        System.out.println("Happiness: " + PetSim.obj1.getHappinessLevel());
        System.out.println("Energy: " + PetSim.obj1.getEnergyLevel());
        System.out.println("Health: " + PetSim.obj1.getHealthLevel());
        System.out.println("Cleanliness: " + PetSim.obj1.getCleanlinessLevel());
        
        updateProgressBar(jProgressBar1, PetSim.obj1.getHungerLevel());
        updateProgressBar(jProgressBar2, PetSim.obj1.getHappinessLevel());
        updateProgressBar(jProgressBar3, PetSim.obj1.getEnergyLevel());
        updateProgressBar(jProgressBar4, PetSim.obj1.getHealthLevel());
        updateProgressBar(jProgressBar5, PetSim.obj1.getCleanlinessLevel());
    }
    // This method is to limit each stats to 100 and 0 only.
    public void limiter() {
        // Limits the Energy Level to 100 and 0 only.
        if(PetSim.obj1.getEnergyLevel() > 100) {
            PetSim.obj1.setEnergyLevel(100);
        } else if (PetSim.obj1.getEnergyLevel() < 0) {
            PetSim.obj1.setEnergyLevel(0);
        }
        
        // Limits the Happiness Level to 100 and 0 only.
        if(PetSim.obj1.getHappinessLevel() > 100) {
            PetSim.obj1.setHappinessLevel(100);
        } else if (PetSim.obj1.getHappinessLevel() < 0) {
            PetSim.obj1.setHappinessLevel(0);
        }
        
        // Limits the Cleanliness Level to 100 and 0 only.
        if(PetSim.obj1.getCleanlinessLevel() > 100) {
            PetSim.obj1.setCleanlinessLevel(100);
        } else if (PetSim.obj1.getCleanlinessLevel() < 0) {
            PetSim.obj1.setCleanlinessLevel(0);
        }
        
        // Limits the Health Level to 100 and 0 only.
        if(PetSim.obj1.getHealthLevel() > 100) {
            PetSim.obj1.setHealthLevel(100);
        } else if (PetSim.obj1.getHealthLevel() < 0) {
            PetSim.obj1.setHealthLevel(0);
        }
        
        // Limits the Hunger Level to 100 and 0 only.
        if(PetSim.obj1.getHungerLevel() > 100) {
            PetSim.obj1.setHungerLevel(100);
        } else if (PetSim.obj1.getHungerLevel() < 0) {
            PetSim.obj1.setHungerLevel(0);
        }
    }
    
    public static void updateProgressBar(JProgressBar progressBar, double valueDouble) {
        // Ensure the value is within the progress bar's range
        int value = (int) Math.round(valueDouble);
        if (value < 0) {
            value = 0;
        } else if (value > 100) {
            value = 100;
        }
        progressBar.setValue(value); // Update the progress bar value
        progressBar.setString(value + "%"); // Update the displayed string
        progressBar.setStringPainted(true);
    }

    public NewJFrame() {
        initComponents();
        //broom cursor for  clean
        try {
            URL broomUrl = getClass().getResource("/assets/broom.png");
            if (broomUrl == null) {
                System.err.println("Broom image not found!");
            } else {
                 broomIcon = new ImageIcon(broomUrl);  // Using URL directly for ImageIcon
                 broomLabel = new JLabel(broomIcon);
                 broomLabel.setVisible(false);
                 jPanel1.add(broomLabel); // Now it's safe to add
            }
        } catch (Exception ex) {
            System.err.println("Error loading broom image: " + ex.getMessage());
        }
          // Setup progress bars (including initial borders)
         setupVerticalProgressBar(jProgressBar1); // No title needed here
        setupVerticalProgressBar(jProgressBar2);
        setupVerticalProgressBar(jProgressBar3);
        setupVerticalProgressBar(jProgressBar4);
        setupVerticalProgressBar(jProgressBar5);

        // Add ChangeListeners to update text dynamically
        addProgressBarChangeListener(jProgressBar1); // No title needed
        addProgressBarChangeListener(jProgressBar2);
        addProgressBarChangeListener(jProgressBar3);
        addProgressBarChangeListener(jProgressBar4);
        addProgressBarChangeListener(jProgressBar5);
        
        // Disregard this
        // PetSim.obj1.loadStatsFromCSV(); // Load stats on startup ---------------------------------------------> LOAD
        //updateProgressBars();
        
        printStats();
 try {
            URL spriteSheetUrl = getClass().getResource("/assets/Tile.png");
            if (spriteSheetUrl == null) {
                System.err.println("Tile.png not found!"); // More helpful error message
            } else {
                // Create spriteRenderer AFTER initComponents()!
                spriteRenderer = new SpriteSheetRenderer(spriteSheetUrl.toExternalForm(), 32, 32);

                jPanel1.add(spriteRenderer); // Add to the panel NOW

                // Set the layout AFTER adding the renderer!
                jPanel1.setLayout(new BorderLayout());
                jPanel1.add(spriteRenderer, BorderLayout.CENTER); // Ensure layout and add work correctly
            }
         } catch (Exception ex) {
            System.err.println("Error setting up sprite sheet renderer " + ex);
        }
        jPanel1.add(spriteRenderer); // Important: Add to the panel.

        //Use BorderLayout for panel layout
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(spriteRenderer, BorderLayout.CENTER);
    
        jButton2.addActionListener(e -> doPlayAnimation());  // Use doPlayAnimation instead
        jButton1.addActionListener(e -> updateSprite(49,56));  //feed animation frames
        jButton3.addActionListener(e -> updateSprite(177,179)); //clean animation frames
        jButton4.addActionListener(e->doSleepAnimation());

       //Ensure correct idle animation on load
       spriteRenderer.setAnimationRanges(new int[][]{{17,23}});
    }
    

    private void setupVerticalProgressBar(JProgressBar progressBar) {
        progressBar.setOrientation(SwingConstants.VERTICAL);
        progressBar.setPreferredSize(new Dimension(25, 100));
        progressBar.setStringPainted(false);  // Make sure string is painted

        Border bottomBorder = new MatteBorder(0, 0, 1, 0, Color.GRAY);
        Border emptyBorder = new EmptyBorder(0, 0, 15, 0);  // Adjust bottom padding
        CompoundBorder compoundBorder = new CompoundBorder(emptyBorder, bottomBorder);
        progressBar.setBorder(compoundBorder);

        // Set initial string (percentage)
        progressBar.setString(progressBar.getValue() + "%");  // Set string here
        progressBar.setStringPainted(false);

    }




    private void addProgressBarChangeListener(JProgressBar progressBar) {

       progressBar.addChangeListener(e -> {
            // Other actions if needed (e.g., updating other components)

            progressBar.repaint(); // Repaint is usually still necessary
            progressBar.revalidate(); 
        });
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar(0, 100);
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        jProgressBar5 = new javax.swing.JProgressBar();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));
        setForeground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jButton1.setText("Feed");
        jButton1.setPreferredSize(new java.awt.Dimension(72, 21));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 312, 80, 23));

        jButton2.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jButton2.setText("Play");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 312, -1, -1));

        jButton3.setText("Clean");
        jButton3.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jButton3.setLabel("Clean");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 312, -1, -1));

        jButton4.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jButton4.setText("Sleep");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 312, -1, -1));

        jLabel1.setFont(new java.awt.Font("PKMN RBYGSC", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(PetSim.obj1.getName());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 73, -1, -1));

        jLabel3.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jLabel3.setText("Cat Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 51, 90, -1));

        jButton6.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jButton6.setText("Set Name");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 111, -1, -1));

        jProgressBar1.setValue(0);
        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setBorder(null);
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 72, 60));

        jProgressBar2.setValue(0);
        jProgressBar2.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar2.setBorder(null);
        getContentPane().add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 72, 60));

        jProgressBar3.setValue(0);
        jProgressBar3.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 72, 60));

        jProgressBar4.setValue(0);
        jProgressBar4.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jProgressBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 72, 60));

        jProgressBar5.setValue(0);
        jProgressBar5.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jProgressBar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 72, 60));

        jButton5.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jButton5.setText("Save Stats");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 130, -1));

        jLabel9.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cleanliness.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(523, 348, -1, -1));

        jLabel8.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/health.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 348, -1, -1));

        jLabel7.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/energy.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 348, -1, -1));

        jLabel6.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/happiness.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 348, -1, -1));

        jLabel5.setFont(new java.awt.Font("PKMN RBYGSC", 0, 12)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/hunger.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 348, 30, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/menu.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 338, 470, 350));

        jPanel1.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        jPanel1.setOpaque(false);
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/room.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 440));

        setSize(new java.awt.Dimension(720, 460));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        JDialog dialog = new JDialog(this, "Select Option", true);
        dialog.setSize(500, 100);
        dialog.setLayout(new FlowLayout());
        dialog.setLocationRelativeTo(this);

        // Create radio buttons
        JRadioButton option1 = new JRadioButton("Isaw");
        JRadioButton option2 = new JRadioButton("Biscuit ni Bob");
        JRadioButton option3 = new JRadioButton("Spanish Latte");
        JRadioButton option4 = new JRadioButton("Ice Cream");
        JRadioButton option5 = new JRadioButton("Beef Steak");

        // Add radio buttons to a ButtonGroup
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);
        group.add(option5);

        // Add buttons to the dialog
        dialog.add(option1);
        dialog.add(option2);
        dialog.add(option3);
        dialog.add(option4);
        dialog.add(option5);

        // OK button to confirm selection
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option1.isSelected()) {
                    PetSim.obj1.addHunger("isaw");
                } else if (option2.isSelected()) {
                    PetSim.obj1.addHunger("biscuit");
                } else if (option3.isSelected()) {
                    PetSim.obj1.addHunger("spanishLatte");
                } else if (option4.isSelected()) {
                    PetSim.obj1.addHunger("iceCream");
                } else if (option5.isSelected()) {
                    PetSim.obj1.addHunger("beefSteak");
                }
                PetSim.obj1.addHappiness(5);
                PetSim.obj1.addHealth(2);
                PetSim.obj1.addCleanliness(-10);
                limiter();
                printStats();
                dialog.dispose(); // Close dialog after selection
            }
        });
        
        // Cancel button to close dialog without action
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(okButton);
        dialog.add(cancelButton);

        // Show the dialog
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // Every click of the mouse.
        // PLAY ---- Increases Happiness, Decreases Energy
        if (!(PetSim.obj1.calculator(PetSim.obj1.getEnergyLevel(), 5))) { // 5 is the default usage of the Energy. That would mean (CurrentEnergyLevel - 5)
        PetSim.obj1.addHappiness(8);
        PetSim.obj1.addEnergy(-5);
        PetSim.obj1.addCleanliness(-10);
        PetSim.obj1.addHealth(-10);
        PetSim.obj1.hungerIncrease(-2);
        limiter();
        printStats();
        } else {
            JOptionPane.showMessageDialog(this, (PetSim.obj1.getName() + " cannot play anymore. Energy is low. \nSleep to regain energy."), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // Every click of the mouse.
        // CLEAN ---- Increases Cleanliness, Decreases Energy
       if (cleanClick) {
            cleanClick = false;
            jButton3.setLabel("Clean");
            hideBroom();
            spriteRenderer.setAnimationRanges(new int[][]{{17, 23}}); // Hide the broom when cleaning stops

        } else {
            cleanClick = true;
            jButton3.setLabel("Stop");
            showBroom(); // Show broom and set cursor when cleaning starts

            if (!cleanInformation) {
                cleanInformation = true;
                JOptionPane.showMessageDialog(this, "Drag your cursor across your pet to clean.", "Tip", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        System.out.println(cleanClick);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this, "Enter a new name for your pet:");
    
        // If the user entered something (input is not null), update the label with the new name
        if (input != null && !input.trim().isEmpty()) {
            PetSim.obj1.setName(input);
            jLabel1.setText(PetSim.obj1.getName());
        } else {
            JOptionPane.showMessageDialog(this, "No name entered!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton6MouseClicked
    private Thread sleepThread;
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
       if (sleepStatus) {
        // Stop sleeping
        sleepStatus = false;
        jButton4.setText("Sleep");
        SwingUtilities.invokeLater(() -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/room.png"))));

        // Interrupt the running thread, if it exists
        if (sleepThread != null && sleepThread.isAlive()) {
            sleepThread.interrupt();
        }
    } else {
        // Start sleeping
        sleepStatus = true;
        jButton4.setText("Stop");
        SwingUtilities.invokeLater(() -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/darkroom.jpg"))));

        // Create a new thread for sleep actions
        sleepThread = new Thread(() -> {
            while (sleepStatus) {
                try {
                    Thread.sleep(1000);

                    if (PetSim.obj1 != null) {
                        PetSim.obj1.addEnergy(2);
                        PetSim.obj1.addHealth(2);
                        PetSim.obj1.hungerIncrease(-1); // Increase Hunger
                        PetSim.obj1.addHappiness(-6);
                        limiter();

                        SwingUtilities.invokeLater(this::printStats);
                    } else {
                        System.err.println("PetSim cannot be found");
                    }
                } catch (InterruptedException e) {
                    // Thread was interrupted; exit the loop
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        sleepThread.start();
    }
    }//GEN-LAST:event_jButton4MouseClicked

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        // TODO add your handling code here: 
         if (cleanClick) { 
            Point mousePosition = evt.getPoint();
            broomLabel.setLocation(mousePosition.x - broomIcon.getIconWidth() / 2, mousePosition.y - broomIcon.getIconHeight() / 2);
         }
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        // TODO add your handling code here:
        System.out.println("Clean status: " + cleanClick);
       if (cleanClick) {
            showBroom(); // Only track and show broom if cleaning is active
                        spriteRenderer.setAnimationRanges(new int[][]{{177, 179}}); // Cleaning animation 
            Point mousePosition = evt.getPoint();
            broomLabel.setLocation(mousePosition.x - broomIcon.getIconWidth() / 2, mousePosition.y - broomIcon.getIconHeight() / 2);

            if (!(PetSim.obj1.calculator(PetSim.obj1.getEnergyLevel(), 1))) { // 20 is the default usage of the Energy. That would mean (CurrentEnergyLevel - 20)
                PetSim.obj1.addCleanliness(2);
                PetSim.obj1.hungerIncrease(-1);
                PetSim.obj1.addEnergy(-1);
                limiter();
                printStats();
            } else {
                JOptionPane.showMessageDialog(this, (PetSim.obj1.getName() + " cannot play anymore. Energy is low. \nSleep to regain energy."), "Warning", JOptionPane.WARNING_MESSAGE);
                        spriteRenderer.setAnimationRanges(new int[][]{{17, 23}}); // Default animation
            }
        }
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        if (PetSim.obj1.getHappinessLevel() < 20 || PetSim.obj1.getHungerLevel() < 20) {
        JOptionPane.showMessageDialog(this,
                "Conditions not met for saving stats.\n"
                        + "Ensure happiness level is above 20 and hunger level is above 20.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    } else {
        saveStatsToCSV(); // ----------------------------------------------------- > Save stats
        JOptionPane.showMessageDialog(this,
                "Cat stats saved successfully to CSV.",
                "Save Successful",
                JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_jButton5MouseClicked

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited

        if(cleanClick) {
             hideBroom();
        }if (cleanClick) {
            hideBroom();
            spriteRenderer.setAnimationRanges(new int[][]{{17, 23}}); // Revert to default
        }
    }//GEN-LAST:event_jPanel1MouseExited

    
    /**
     * @param args the command line arguments
     */
   public class PetSim {
    public static cat obj1 = new cat();

    public static void main(String[] args) {
        // Load stats from CSV when the application starts
        loadStatsFromCSV();

        // Create an instance of the NewJFrame class
        NewJFrame frame = new NewJFrame();
        
        // Set the frame to be visible
        frame.setVisible(true);
    }
}

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    // End of variables declaration//GEN-END:variables
    public void saveStatsToCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("catStats.csv", true))) {
            writer.println(PetSim.obj1.getName() + "," + PetSim.obj1.getHappinessLevel() + "," + PetSim.obj1.getHungerLevel() + "," + PetSim.obj1.getEnergyLevel() + "," + PetSim.obj1.getCleanlinessLevel() + "," + PetSim.obj1.getHealthLevel());
            System.out.println("Stats saved to catStats.csv");
        } catch (IOException e) {
            System.err.println("Error saving stats: " + e.getMessage());
        }
    }
    
    public static void loadStatsFromCSV() {
        File file = new File("catStats.csv");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split(",");
                    if (data.length == 6) {
                        PetSim.obj1.setName(data[0]);
                        PetSim.obj1.setHappinessLevel(Double.parseDouble(data[1]));
                        PetSim.obj1.setHungerLevel(Double.parseDouble(data[2]));
                        PetSim.obj1.setEnergyLevel(Double.parseDouble(data[3]));
                        PetSim.obj1.setCleanlinessLevel(Double.parseDouble(data[4]));
                        PetSim.obj1.setHealthLevel(Double.parseDouble(data[5]));
                    }
                }
                System.out.println("Stats loaded from catStats.csv");
            } catch (IOException e) {
                System.err.println("Error loading stats: " + e.getMessage());
            }
            
            System.out.println("Loaded Stats from CSV");
            System.out.println("Energy: " + PetSim.obj1.getEnergyLevel());
            System.out.println("Happiness: " + PetSim.obj1.getHappinessLevel());
            System.out.println("Hunger: " + PetSim.obj1.getHungerLevel());
            System.out.println("Cleanliness: " + PetSim.obj1.getCleanlinessLevel());
            System.out.println("Health: " + PetSim.obj1.getHealthLevel());
            System.out.println("Name: " + PetSim.obj1.getName());
            
        }
    }

    // ANIMATION FUNCTIONS
     private Cursor createBroomCursor() {
        // Create a custom cursor from the broom image
        Toolkit toolkit = Toolkit.getDefaultToolkit();
         // Create a blank cursor
         Image blankImage = new BufferedImage(16,16, BufferedImage.TYPE_INT_ARGB);
         Cursor blankCursor = toolkit.createCustomCursor(blankImage, new Point(0,0), "blank cursor");

         jPanel1.setCursor(blankCursor);

        return toolkit.createCustomCursor(broomIcon.getImage(), new Point(10, 25), "broom cursor"); // Adjust hotspot as needed

    }
     
     private void showBroom() {
        jPanel1.setCursor(createBroomCursor());
        broomLabel.setVisible(true);
    }

    private void hideBroom() {
        jPanel1.setCursor(Cursor.getDefaultCursor());
        broomLabel.setVisible(false);
    }
    
    private void doSleepAnimation() {
        if (sleepStatus) {
              spriteRenderer.setAnimationRanges(new int[][]{{17,23}});  //Set animation back to idle

        } else {
              spriteRenderer.setAnimationRanges(new int[][]{{98,103}});//sleep sprite
    }
    }
    
    //Simplified function to set new sprite range based on button clicks
   private void updateSprite(int start, int end){

    spriteRenderer.setAnimationRanges(new int[][]{{start,end}});
}
   
   private void doPlayAnimation() {
        //Check for energy usage
         if (!(PetSim.obj1.calculator(PetSim.obj1.getEnergyLevel(), 5))) {

            updateSprite(25,40); // Play animation

        } else {
            JOptionPane.showMessageDialog(this, (PetSim.obj1.getName() + " cannot play anymore. Energy is low. \nSleep to regain energy."), "Warning", JOptionPane.WARNING_MESSAGE);
           
        }
    }
}
