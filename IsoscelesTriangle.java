package cmsc330_project1;


//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class IsoscelesTriangle extends Image {
    private Point topVertex;
    private int height;
    private int width;
    private int[] xCoords;
    private int[] yCoords;

    public IsoscelesTriangle(Color color, Point topVertex, int height, int width) {
        super(color);  // Initialize the color using the parent class constructor
        this.topVertex = topVertex;
        this.height = height;
        this.width = width;

        this.xCoords = new int[3];  // Three vertices (x coordinates)
        this.yCoords = new int[3];  // Three vertices (y coordinates)

        // Compute the coordinates of the isosceles triangle
        this.xCoords[0] = topVertex.x;
        this.yCoords[0] = topVertex.y;

        this.xCoords[1] = topVertex.x - width / 2;
        this.yCoords[1] = topVertex.y + height;

        this.xCoords[2] = topVertex.x + width / 2;
        this.yCoords[2] = topVertex.y + height;
    }

    @Override
    public void draw(Graphics g) {
        colorDrawing(g);  // Set the color for drawing
        g.fillPolygon(xCoords, yCoords, 3);  // Fill the triangle with the calculated vertices
    }
}
