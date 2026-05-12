package cmsc330_project1;

//Tate Prodigalidad
//07-08-2025
//CMSC 330 Advanced Programming Languages
//Project 1

// Class that defines a lexical error

class LexicalError extends Exception
{
    // Constructor that creates a lexical error object given the line number and error

    public LexicalError(int line, String description)    {
        super("Lexical Error on Line: " + line + " " + description);
    }
}
