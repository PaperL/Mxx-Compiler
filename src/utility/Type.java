package utility;

import ast.NodeBracket;

import java.util.ArrayList;
import java.util.HashMap;

public class Type {
    public enum Genre {
        BOOLEAN, INTEGER, STRING,   // built-in type
        CLASS_NAME,                 // class name
        VOID,                       // function return type
        NULL,                       // expression value
    }

    public Genre genre = null;

    public String className = null;      // class name
    public int dimension = 0;

    public Type() {
    }

    public Type(Genre genre_) {
        assert genre_ != Genre.CLASS_NAME;
        genre = genre_;
    }

    public Type(String className_) {
        genre = Genre.CLASS_NAME;
        className = className_;
    }
}
