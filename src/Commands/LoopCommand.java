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
    public void doCommand(String[] arguments){
        if (arguments.length<4){
            return;
        }

        ShuntingYard shuntingYard = new ShuntingYard();
        String condition = arguments[0];
        condition.split(" ");
        String variable = condition.substring(6, 9);
        String operator = condition.substring(10, 11);
        String value = condition.substring(12, (condition.length()-3));
        String[] NewArgument = Arrays.copyOfRange(arguments, 1, (arguments.length-1));

        int  i =0;
        int j = 0;

        while(condition(variable, operator, value)){
            Lexer lex = new Lexer();
            Parser parse = new Parser(this.commandFactory);
            for (String str : NewArgument){
                parse.parse(lex.processLine(str));
            }
        }
        /*
         * else if (NewArgument[i].trim().equals("sleep") ||
         * NewArgument[j].equals("sleep")){ String[] goSleep =
         * Arrays.copyOfRange(NewArgument, i, NewArgument.length); Command command =
         * commandFactory.getCommand(NewArgument[i]); command.doCommand(goSleep); if
         * ((NewArgument.length-1) == i+1 ) { i =2; j=0; } } else if
         * (NewArgument[i].trim().equals("print")) { String[] goPrint =
         * Arrays.copyOfRange(NewArgument, i, NewArgument.length); Command command =
         * commandFactory.getCommand(NewArgument[i]); command.doCommand(goPrint); }
         */

        /*
         * commandFactory.PathTable.put(commandFactory.StringshuntingYardmbolTable.get(NewArgument
         * [j].trim()), shuntingYard.calc(NewArgument[i],commandFactory).calculate() ); i = i+3; j
         * = j+3;
         */
    }

    private boolean condition(String variable, String operator, String value){
        switch (operator){
            case "<":
                return shuntingYard.calc(variable,commandFactory).calculate() < shuntingYard.calc(value, commandFactory).calculate();
            case ">":
                return shuntingYard.calc(variable,commandFactory).calculate() > shuntingYard.calc(value, commandFactory).calculate();
            case "<=":
                return shuntingYard.calc(variable,commandFactory).calculate() <= shuntingYard.calc(value, commandFactory).calculate();
            case ">=":
                return shuntingYard.calc(variable,commandFactory).calculate() >= shuntingYard.calc(value, commandFactory).calculate();
            case "==":
                return shuntingYard.calc(variable,commandFactory).calculate() == shuntingYard.calc(value, commandFactory).calculate();
            case "!=":
                return shuntingYard.calc(variable,commandFactory).calculate() != shuntingYard.calc(value, commandFactory).calculate();
            default:
                return false;
        }
    }
}