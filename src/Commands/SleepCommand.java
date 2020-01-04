package Commands;

import java.util.Map;
import Interpreter.ShuntingYard;
import test.MyInterpreter;

public class SleepCommand implements Command{
    public CommandFactory commandFactory;
    protected Map<String, Double> DoubleSymbolTable;

    public SleepCommand(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }

    @Override
    public void doCommand(String[] arguments){
        String newArgument = arguments[1].trim();

        try {
            Thread.currentThread().sleep(Long.parseLong(newArgument));
        }catch(NumberFormatException | InterruptedException exception){
            exception.printStackTrace();
        }
    }
}
