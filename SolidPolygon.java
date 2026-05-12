package cmsc330_project1;


//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class SolidPolygon extends Polygon_ {
    private int sides;
    private int radius;
    private Point center;
    private int[] xCoords;
    private int[] yCoords;

    // Constructor for a regular polygon
    public SolidPolygon(int sides, Color color, Point center, int radius) {
        super(color, radius);  // Call the constructor of the Polygon_ class
        this.sides = sides;
        this.center = center;
        this.radius = radius;
        this.xCoords = new int[sides];
        this.yCoords = new int[sides];

        // Calculate the coordinates for the vertices of the polygon
        for (int i = 0; i < sides; i++) {
            this.xCoords[i] = (int) (center.x + radius * Math.cos(i * 2 * Math.PI / sides)); // X-coordinate
            this.yCoords[i] = (int) (center.y + radius * Math.sin(i * 2 * Math.PI / sides)); // Y-coordinate
        }
    }

    @Override
    public void drawPolygon(Graphics g) {
        colorDrawing(g);  // Set the color for drawing
        g.fillPolygon(xCoords, yCoords, sides);  // Fill the polygon with the calculated vertices
    }

	@Override
	public void drawPolygon(Graphics graphics, Polygon polygon) {
		// TODO Auto-generated method stub
		
	}
}
