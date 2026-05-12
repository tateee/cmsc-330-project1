package cmsc330_project1;


//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Text extends Image {
    private Point location;
    private String text;

    public Text(Color color, Point location, String text) {
        super(color);  // Initialize the color using the parent class constructor
        this.location = location;
        this.text = text;
    }

    @Override
    public void draw(Graphics g) {
        colorDrawing(g);  // Set the color for drawing
        g.drawString(text, location.x, location.y);  // Draw the text at the specified location
    }
}
