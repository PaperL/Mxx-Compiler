package utility;

import java.util.ArrayList;
import java.util.List;

public class CmdArgument {
    public ArrayList<String> arguments;

    public enum ArgumentType{
        LOCAL,
        SEMANTIC, IR, CODEGEN
    }

    public CmdArgument(String[] args) {
        arguments = new ArrayList<>(List.of(args));
    }

    public boolean contains(ArgumentType arg) {
        return arguments.contains(trans(arg));
    }

    public String trans(ArgumentType arg){
        switch(arg){
            case LOCAL -> {
                return "-local";
            }
            case SEMANTIC -> {
                return "-semantic";
            }
            // todo
        }
        return null;
    }
}
