package Commands;

import java.util.Arrays;

import Lexer.Lexer;
import Parser.Parser;
import Interpreter.ShuntingYard;

import ClientServer.Server;

public class LoopCommand implements Command {
    private CommandFactory commandFactory;
    ShuntingYard shuntingYard;
    private Server server;

    public LoopCommand(Server server2, CommandFactory commandFactory) {
        this.server = server2;
        this.commandFactory = commandFactory;
        this.shuntingYard = new ShuntingYard();
    }

    @Override
    public void doCommand(String[] arguments) {
        if (arguments.length<4) {
            return;
        }
        ShuntingYard sy = new ShuntingYard();
        String TheCondition= arguments[0];
        String splitter[] = TheCondition.split(" ");
        String variable = splitter[1];
        String operator = splitter[2];
        String value = splitter[3];

        String[] NewArgument = Arrays.copyOfRange(arguments, 1, (arguments.length-1));
        int i = 0;
        int j = 0;
        while (condition(variable, operator, value)) {
            Lexer lexer = new Lexer();
            Parser parse = new Parser(this.commandFactory);
            for (String str : NewArgument) {
                parse.parse(lexer.processLine(str));
            }
        }
    }

    private boolean condition(String variable, String operator, String value) {
        switch (operator) {
            case "<":
                return this.shuntingYard.calc(variable,commandFactory).calculate() < this.shuntingYard.calc(value, commandFactory).calculate();
            case ">":
                return this.shuntingYard.calc(variable,commandFactory).calculate() > this.shuntingYard.calc(value, commandFactory).calculate();
            case "<=":
                return this.shuntingYard.calc(variable,commandFactory).calculate() <= this.shuntingYard.calc(value, commandFactory).calculate();
            case ">=":
                return this.shuntingYard.calc(variable,commandFactory).calculate() >= this.shuntingYard.calc(value, commandFactory).calculate();
            case "==":
                return this.shuntingYard.calc(variable,commandFactory).calculate() == this.shuntingYard.calc(value, commandFactory).calculate();
            case "!=":
                return this.shuntingYard.calc(variable,commandFactory).calculate() != this.shuntingYard.calc(value, commandFactory).calculate();
            default:
                return false;
        }
    }
}