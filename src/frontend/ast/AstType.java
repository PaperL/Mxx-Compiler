package frontend.ast;

/**
 * Data type in AST
 */
public class AstType {
    public boolean isVariable;
    public Genre genre = null;
    public String className = null;      // class name
    public int dimension = 0;

    public AstType(Genre genre_, boolean isVariable_) {
        // SemanticChecker::checkAtom()::IDENTIFIER uses genre == CLASS_NAME
        // assert genre_ != Genre.CLASS_NAME;
        genre = genre_;
        isVariable = isVariable_;
    }

    public AstType(String className_) {
        isVariable = true;
        genre = Genre.CLASS_NAME;
        className = className_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstType type = (AstType) o;
        // ? 在处理内建类的过程中, 可能会出现 genre 相等但 className 不相等的情况
        // ? 可以通过将所有 'string' 和 'array' 类型变量保证 className 为正确值解决该问题
        if ((genre == type.genre) && (dimension == type.dimension)) {
            if (genre == Genre.CLASS_NAME) return className.equals(type.className);
            else return true;
        }
        return false;
    }

    public enum Genre {
        BOOLEAN, INTEGER, STRING,   // built-in type
        CLASS_NAME,                 // class name
        VOID,                       // function return type
        NULL,                       // expression value
    }
}
