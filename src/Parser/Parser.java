package Parser;

import Commands.CommandFactory;
import java.util.Arrays;

public class Parser {
    private CommandFactory commandFactory;
    private String reservedSymbols = "{}()<>=+-*/";

    public Parser(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void parse(String[] line) {
        String[] str2 = Arrays.stream(line)
                .filter(value -> !value.isEmpty())
                .filter((string)->!string.equals(" "))
                .toArray(String[]::new);
        this.commandFactory.getCommand(str2[0]).doCommand(str2);
    }
}
