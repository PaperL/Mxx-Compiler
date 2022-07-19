package utility;

import java.util.ArrayList;
import java.util.List;

public class CmdArgument {
    public ArrayList<String> arguments;

    public CmdArgument(String[] args) {
        arguments = new ArrayList<>(List.of(args));
    }

    public boolean contains(ArgumentType arg) {
        return arguments.contains(trans(arg));
    }

    public int gets(ArgumentType arg) {
        return Integer.parseInt(arguments.get(arguments.indexOf(trans(arg)) + 1));
    }

    public String trans(ArgumentType arg) {
        switch (arg) {
            case DEBUG -> {
                return "--debug";
            }
            case LOCAL -> {
                return "--local";
            }
            case SEMANTIC -> {
                return "--semantic";
            }
            case IR -> {
                return "--ir";
            }
            case IR_SOURCE_CODE -> {
                return "--ir-source-code";
            }
            case ASSEMBLY_COMMENT -> {
                return "--assembly-comment";
            }
            case STACK_SIZE -> {
                return "--stack-size";
            }
            case OPTIMIZATION -> {
                return "-O";
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return arguments.toString();
    }

    public enum ArgumentType {
        DEBUG, LOCAL,
        SEMANTIC,
        IR, IR_SOURCE_CODE,
        ASSEMBLY_COMMENT,
        STACK_SIZE,
        OPTIMIZATION,
    }
}
