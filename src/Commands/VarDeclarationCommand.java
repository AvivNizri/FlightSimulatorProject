package Commands;

import java.util.Map;
import Interpreter.ShuntingYard;
import Interpreter.VarNumber;
import ClientServer.Client;

public class VarDeclarationCommand implements Command{
    public CommandFactory commandFactory;
    protected Map<String, String> StringSymbolTable;
    private Client client;
    ShuntingYard shuntingYard;
    VarNumber putInDoublemap;
    
    public VarDeclarationCommand(Client client2, CommandFactory commandFactory){
        this.client = client2;
        this.commandFactory = commandFactory;
        this.shuntingYard = new ShuntingYard();
    }

    @Override
    public void doCommand(String[] arguments){
        //var breaks = bind "/controls/flight/speedbrake"
        if(arguments.length == 2) { //var x
            this.commandFactory.DoubleSymbolTable.put(arguments[1], 1.0);
        }
        else if(arguments[2].equals("=") && arguments.length==4){
            //this.commandFactory.DoubleSymbolTable.put(arguments[1].trim(), this.commandFactory.PathTable.get(this.commandFactory.StringSymbolTable.get(arguments[(arguments.length - 1)])));
            this.commandFactory.DoubleSymbolTable.put(arguments[1].trim(), this.shuntingYard.calc(arguments[arguments.length-1], this.commandFactory).calculate());
        }
        else if(arguments[3].equals("bind")){  	//"var y = bind simX",
            this.commandFactory.StringSymbolTable.put(arguments[1].trim(), arguments[arguments.length - 1]);
        }

        //"connect 127.0.0.1 "+port,
        //"var x",
        //"x = bind simX",
        //"var y = bind simX",
        //"x = "+rand*2,
        //"disconnect",
        //"return y"
    }
}




