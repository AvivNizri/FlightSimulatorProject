package Commands;

import java.util.Map;
import Interpreter.ShuntingYard;
import test.MyInterpreter;

public class ReturnCommand implements Command{
    public CommandFactory commandFactory;
    protected Map<String, Double> DoubleSymbolTable;
    Double answer;

    public ReturnCommand(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }

    @Override
    public void doCommand(String[] arguments){
        // "return y*x+z"
        // "return "+rand+" * 5 - (8+2)"
        try {
            Thread.sleep(1000);
        }catch(InterruptedException exception){
            exception.printStackTrace();
        }

        ShuntingYard shuntingYard = new ShuntingYard();
        MyInterpreter.Value = shuntingYard.calc(arguments[1], this.commandFactory).calculate();
    }
}
