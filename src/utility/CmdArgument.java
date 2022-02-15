package utility;

import java.util.ArrayList;
import java.util.List;

public class CmdArgument {
    public ArrayList<String> arguments;

    public enum ArgumentType {
        DEBUG, LOCAL,
        SEMANTIC,
        IR, IR_SOURCE_CODE,
        CODEGEN
    }

    public CmdArgument(String[] args) {
        arguments = new ArrayList<>(List.of(args));
    }

    public boolean contains(ArgumentType arg) {
        return arguments.contains(trans(arg));
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
            case IR_SOURCE_CODE -> {
                return "--ir-source-code";
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return arguments.toString();
    }
}
