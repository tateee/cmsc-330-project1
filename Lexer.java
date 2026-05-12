package cmsc330_project1;


//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1

import java.io.*;
import java.util.*;

class Lexer {
    private StreamTokenizer tokenizer;
    private String punctuation = ",;.()";
    private Token[] punctuationTokens = {Token.COMMA, Token.SEMICOLON, Token.PERIOD, Token.LEFT_PAREN, Token.RIGHT_PAREN};

    public Lexer(File file) throws FileNotFoundException {
        tokenizer = new StreamTokenizer(new FileReader(file));
        tokenizer.ordinaryChar('.');  // Handle period as ordinary character
        tokenizer.quoteChar('"');  // Handle quote character for string literals
    }

    // Returns the next token in the input stream
    public Token getNextToken() throws LexicalError, IOException {
        int token = tokenizer.nextToken();
        System.out.println("Token: " + token);  // Print debug output for token

        switch (token) {
            case StreamTokenizer.TT_NUMBER:
                System.out.println("Number token: " + tokenizer.nval);  // Debugging number tokens
                return Token.NUMBER;
            case StreamTokenizer.TT_WORD:
                String sval = tokenizer.sval.toUpperCase();  // Convert word to uppercase
                System.out.println("Word token: " + sval);  // Debugging word tokens

                // Match specific keywords and return the corresponding token
                switch (sval) {
                    case "SCENE": return Token.SCENE;
                    case "RIGHTTRIANGLE": return Token.RIGHT_TRIANGLE; // Match "RIGHTTRIANGLE"
                    case "RECTANGLE": return Token.RECTANGLE;
                    case "PARALLELOGRAM": return Token.PARALLELOGRAM;
                    case "REGULARPOLYGON": return Token.REGULAR_POLYGON; // Match "REGULARPOLYGON"
                    case "ISOSCELES": return Token.ISOSCELES;
                    case "TEXT": return Token.TEXT;
                    case "COLOR": return Token.COLOR;
                    case "AT": return Token.AT;
                    case "HEIGHT": return Token.HEIGHT;
                    case "WIDTH": return Token.WIDTH;
                    case "SIDES": return Token.SIDES;
                    case "RADIUS": return Token.RADIUS;
                    case "OFFSET": return Token.OFFSET;
                    case "END": return Token.END;
                    default: return Token.IDENTIFIER;  // If it's not a keyword, return as an identifier
                }
            case StreamTokenizer.TT_EOF:
                return Token.EOF;
            default:
                // Handle punctuation tokens
                for (int i = 0; i < punctuation.length(); i++) {
                    if (token == punctuation.charAt(i))
                        return punctuationTokens[i];  // Return punctuation tokens like commas, semicolons, etc.
                }
        }
        return Token.EOF;
    }

    // Returns the lexeme associated with the current token
    public String getLexeme() {
        return tokenizer.sval;
    }

    // Returns the numeric value of the current token for numeric tokens
    public int getNumber() {
        return (int) tokenizer.nval;
    }

    // Returns the current line of the input file
    public int getLineNo() {
        return tokenizer.lineno();
    }
}
