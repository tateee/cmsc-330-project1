package cmsc330_project1;

//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class RegularPolygon extends Polygon_ {
    private Point center;
    private int sides;
    private int radius;
    private int[] xCoords;
    private int[] yCoords;

    // Constructor for a regular polygon
    public RegularPolygon(Color color, Point center, int sides, int radius) {
        super(color, radius);  // Initialize the Polygon_ constructor with the color
        this.center = center;
        this.sides = sides;
        this.radius = radius;

        this.xCoords = new int[sides];  // Array for x-coordinates of vertices
        this.yCoords = new int[sides];  // Array for y-coordinates of vertices

        // Calculate the coordinates for the regular polygon vertices
        for (int i = 0; i < sides; i++) {
            this.xCoords[i] = (int) (center.x + radius * Math.cos(i * 2 * Math.PI / sides));
            this.yCoords[i] = (int) (center.y + radius * Math.sin(i * 2 * Math.PI / sides));
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
