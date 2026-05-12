package cmsc330_project1;

//Tate Prodigalidad
//07-08-2025
// CMSC 330 Advanced Programming Languages
// Project 1

import java.awt.*;
import java.util.*;
import javax.swing.*;

// Class that defines the panel for drawing the images

class DrawingPanel extends JPanel {

    private ArrayList<Image> images = new ArrayList<>();

    // Adds a graphic object to the drawing panel

    public void addImage(Image image) {

        images.add(image);
      }

    // Draws all the images on the drawing panel

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Image image : images)
      
