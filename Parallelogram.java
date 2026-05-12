package cmsc330_project1;


//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Parallelogram extends Image {
    private Point upperLeft;
    private Point lowerRight;
    private int offset;
    private int[] xCoords;
    private int[] yCoords;

    public Parallelogram(Color color, Point upperLeft, Point lowerRight, int offset) {
        super(color);  // Initialize the color using the parent class constructor
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
        this.offset = offset;

        this.xCoords = new int[4];  // Four vertices for a parallelogram
        this.yCoords = new int[4];  // Four vertices for a parallelogram

        // Compute coordinates for the parallelogram
        this.xCoords[0] = upperLeft.x;
        this.yCoords[0] = upperLeft.y;

        this.xCoords[1] = lowerRight.x;
        this.yCoords[1] = upperLeft.y;

        this.xCoords[2] = lowerRight.x + offset;
        this.yCoords[2] = lowerRight.y;

        this.xCoords[3] = upperLeft.x + offset;
        this.yCoords[3] = lowerRight.y;
    }

    @Override
    public void draw(Graphics g) {
        colorDrawing(g);  // Set the color for drawing
        g.fillPolygon(xCoords, yCoords, 4);  // Fill the parallelogram with the calculated vertices
    }
}
