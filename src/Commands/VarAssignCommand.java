package Commands;

import Interpreter.ShuntingYard;
import ClientServer.Client;

public class VarAssignCommand  implements Command{
    public CommandFactory commandFactory;
    private Client client;
    ShuntingYard shuntingYard;

    public VarAssignCommand(Client client, CommandFactory commandFactory){
        this.commandFactory = commandFactory;
        this.client = client;
        this.shuntingYard = new ShuntingYard();
    }

    //set /controls/engines/engine/throttle 1
    //breaks = 8	

    @Override
    public void doCommand(String[] arguments){
        //"x = bind simX",
        //"var h0 = heading",
        if(!this.commandFactory.StringSymbolTable.containsKey(arguments[0])){
            this.commandFactory.DoubleSymbolTable.put(arguments[0], this.shuntingYard.calc(arguments[arguments.length-1], this.commandFactory).calculate());
        }
        else{
            this.commandFactory.PathTable.put(this.commandFactory.StringSymbolTable.get(arguments[0]), this.shuntingYard.calc(arguments[arguments.length-1], this.commandFactory).calculate()); //x = 10
            if(this.client.GetSocket()!= null) {
                this.client.setPathWithValue(this.commandFactory.StringSymbolTable.get(arguments[0]), this.shuntingYard.calc(arguments[arguments.length-1], this.commandFactory).calculate());
            }
        }
    }
}
