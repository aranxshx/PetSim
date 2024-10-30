/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petsim;

/**
 *
 * @author HP
 */

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheetRenderer extends JPanel {
    
    private BufferedImage spriteSheet;
    private BufferedImage[] sprites;
    private int spriteWidth;
    private int spriteHeight;
    private int spriteCount;
    private int currentFrame = 0;
    private int[][] frameRanges; // Array of frame ranges
    private int currentRangeIndex = 0; // Index of the current range
    private int animationDelay = 100; // Delay in milliseconds for frame update
    private Timer animationTimer;
    private Clip audioClip; // Audio clip for background music
    private boolean isPlayingAudio = false; // Track if audio is playing
    public SpriteSheetRenderer(String spritePath, int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        try {
            // Load the sprite sheet
            spriteSheet = ImageIO.read(new File(spritePath));

            // Calculate number of sprites in the sheet
            int rows = spriteSheet.getHeight() / spriteHeight;
            int cols = spriteSheet.getWidth() / spriteWidth;
            spriteCount = rows * cols;

            // Initialize the sprite array
            sprites = new BufferedImage[spriteCount];

            // Slice the sprite sheet into individual sprites
            int index = 0;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    sprites[index++] = spriteSheet.getSubimage(
                        x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight
                    );
                }
            }

            // Set up a timer for the animation
            animationTimer = new Timer(animationDelay, e -> updateAnimation());
            animationTimer.start(); // Start the animation immediately

            // Default animation range on load
            setAnimationRanges(new int[][]{{129, 136}});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMusic(String audioPath) {
        try {
            // Stop and close the previous clip if it's playing
            if (audioClip != null && audioClip.isRunning()) {
                audioClip.stop();
                audioClip.close();
            }

            // Load the audio file as a clip
            File audioFile = new File(audioPath);
            if (!audioFile.exists()) {
                System.err.println("Audio file not found: " + audioPath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);

            System.out.println("Audio loaded successfully.");

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void togglePlayPauseMusic() {
        if (audioClip == null) {
            System.err.println("Audio clip is not initialized.");
            return;
        }

        if (isPlayingAudio) {
            audioClip.stop();
        } else {
            audioClip.setFramePosition(0); // Start from the beginning
            audioClip.start();
        }
        isPlayingAudio = !isPlayingAudio;
    }

    private void updateAnimation() {
        int startFrame = frameRanges[currentRangeIndex][0];
        int endFrame = frameRanges[currentRangeIndex][1];

        // Advance to the next frame within the specified range
        if (currentFrame < endFrame) {
            currentFrame++;
        } else {
            currentRangeIndex = (currentRangeIndex + 1) % frameRanges.length;
            currentFrame = frameRanges[currentRangeIndex][0]; // Directly set to start of new range
        }

        repaint(); // Trigger a repaint to display the new frame
    }

    public void setAnimationRanges(int[][] ranges) {
        frameRanges = ranges;
        currentRangeIndex = 0; // Reset to the first range
        currentFrame = frameRanges[0][0]; // Start from the first frame in the range
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int scaleX = 4;
        int scaleY = 4;

        if (sprites[currentFrame] != null) {
            int scaledWidth = spriteWidth * scaleX;
            int scaledHeight = spriteHeight * scaleY;
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(sprites[currentFrame], 0, 0, scaledWidth, scaledHeight, null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(140, 150);
    }
}
