package utility;

import ast.NodeBracket;

import java.util.ArrayList;
import java.util.HashMap;

public class Type {
    public enum Genre {
        VOID, BOOL, INT, STRING, IDENTIFIER;
    }

    public Genre genre = null;

    public String name = null;
    public int dimension = 0;
}
