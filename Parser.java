package cmsc330_project1;

//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class Parser {
    private Token token;
    private Lexer lexer;

    public Parser(File file) throws IOException {
        lexer = new Lexer(file);
    }

    // Parses the scene
    public Scene parseScene() throws LexicalError, SyntaxError, IOException {
        verifyNextToken(Token.SCENE);  // Expecting the SCENE token
        verifyNextToken(Token.IDENTIFIER);  // Expect the scene name
        String window = lexer.getLexeme();  // Get scene name
        int[] dimensions = getNumberList(2);  // Get width and height
        Scene scene = new Scene(window, dimensions[0], dimensions[1]);  // Create a new Scene object
        parseImages(scene, lexer.getNextToken());  // Parse the images
        verifyNextToken(Token.PERIOD);  // Expect period at the end
        return scene;
    }

    // Parse images in the scene
    private void parseImages(Scene scene, Token imageToken) throws LexicalError, SyntaxError, IOException {
        int height = 0, width = 0, offset = 0, radius = 0;
        verifyNextToken(Token.COLOR);  // Expect the "COLOR" token
        int[] colors = getColorValues();  // Get the RGB values
        Color color = new Color(colors[0], colors[1], colors[2]);  // Create a Color object

        System.out.println("Color Parsed: " + color);  // Debugging info for color

        verifyNextToken(Token.AT);  // Expect "AT" token
        int[] location = getNumberList(2);  // Get x, y coordinates
        Point point = new Point(location[0], location[1]);  // Create Point object

        // Handle different image types based on the imageToken
        if (imageToken == Token.RIGHT_TRIANGLE) {
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            RightTriangle triangle = new RightTriangle(color, point, height, width);
            scene.addImage(triangle);  // Add the triangle to the scene
        } else if (imageToken == Token.RECTANGLE) {
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            Rectangle rectangle = new Rectangle(color, point, height, width);
            scene.addImage(rectangle);
        } else if (imageToken == Token.ISOSCELES) {
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            IsoscelesTriangle triangle = new IsoscelesTriangle(color, point, height, width);
            scene.addImage(triangle);
        } else if (imageToken == Token.PARALLELOGRAM) {
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            verifyNextToken(Token.OFFSET);
            verifyNextToken(Token.NUMBER);
            offset = lexer.getNumber();
            Parallelogram parallelogram = new Parallelogram(color, point, new Point(point.x + width, point.y + height), offset);
            scene.addImage(parallelogram);
        } else if (imageToken == Token.REGULAR_POLYGON) {
            verifyNextToken(Token.SIDES);
            verifyNextToken(Token.NUMBER);
            int sides = lexer.getNumber();
            verifyNextToken(Token.RADIUS);
            verifyNextToken(Token.NUMBER);
            radius = lexer.getNumber();
            RegularPolygon regularPolygon = new RegularPolygon(color, point, sides, radius);
            scene.addImage(regularPolygon);
        } else if (imageToken == Token.TEXT) {
            verifyNextToken(Token.STRING);
            String text = lexer.getLexeme();
            Text textObject = new Text(color, point, text);
            scene.addImage(textObject);
        } else {
            throw new SyntaxError(lexer.getLineNo(), "Unexpected image type: " + imageToken);  // Error if image type is unknown
        }

        verifyNextToken(Token.SEMICOLON);  // Expect semicolon after each shape
        token = lexer.getNextToken();
        if (token != Token.END)  // Continue parsing if there are more images
            parseImages(scene, token);
    }

    // Get RGB color values enclosed in parentheses
    private int[] getColorValues() throws LexicalError, SyntaxError, IOException {
        int[] list = new int[3];  // Three components for RGB color
        verifyNextToken(Token.LEFT_PAREN);  // Expect a left parenthesis
        for (int i = 0; i < 3; i++) {
            verifyNextToken(Token.NUMBER);  // Expect a number (RGB component)
            list[i] = lexer.getNumber();  // Get the number for RGB component
            token = lexer.getNextToken();
            if (i < 2)
                verifyCurrentToken(Token.COMMA);  // Expect a comma between color components
            else
                verifyCurrentToken(Token.RIGHT_PAREN);  // Expect a right parenthesis at the end of RGB components
        }
        return list;  // Return the RGB values as an array
    }

    // Parse a list of numbers enclosed in parentheses (e.g., (100, 100))
    private int[] getNumberList(int count) throws LexicalError, SyntaxError, IOException {
        int[] list = new int[count];
        verifyNextToken(Token.LEFT_PAREN);  // Expect a left parenthesis
        for (int i = 0; i < count; i++) {
            verifyNextToken(Token.NUMBER);  // Expect a number
            list[i] = lexer.getNumber();  // Get the number
            token = lexer.getNextToken();
            if (i < count - 1)
                verifyCurrentToken(Token.COMMA);  // Expect a comma if not the last number
            else
                verifyCurrentToken(Token.RIGHT_PAREN);  // Expect a right parenthesis at the end
        }
        return list;
    }

    // Verifies the next token is the expected token
    private void verifyNextToken(Token expectedToken) throws LexicalError, SyntaxError, IOException {
        token = lexer.getNextToken();
        verifyCurrentToken(expectedToken);  // Verify the current token matches the expected
    }

    // Verifies the current token matches the expected token
    private void verifyCurrentToken(Token expectedToken) throws SyntaxError {
        if (token != expectedToken)
            throw new SyntaxError(lexer.getLineNo(), "Expecting token " + expectedToken + " but found " + token);  // Throw error if mismatch
    }
}
