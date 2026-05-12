package cmsc330_project1;

//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1

public enum Token {
    AT,
    COLOR,
    END,
    HEIGHT,
    RECTANGLE,
    RIGHT_TRIANGLE,
    SCENE,
    WIDTH,
    COMMA,
    SEMICOLON,
    PERIOD,
    LEFT_PAREN,
    RIGHT_PAREN,
    IDENTIFIER,
    NUMBER,
    EOF,
    // New tokens for added shape types
    TEXT,                // Token for Text shapes
    SOLID_POLYGON,       // Token for Solid Polygon shapes
    ISOSCELES,           // Token for Isosceles Triangle shapes
    PARALLELOGRAM,       // Token for Parallelogram shapes
    REGULAR_POLYGON,     // Token for Regular Polygon shapes
    SIDES,               // Token for the number of sides (used for polygons)
    RADIUS,              // Token for the radius (used for polygons)
    OFFSET,              // Token for offset (used for parallelograms)
    STRING               // Token for string literals (e.g., for Text)
}
