package Commands;

import ClientServer.Server;

public class PrintCommand implements Command{
    private Server server;
    public CommandFactory commandFactory;

    public PrintCommand(Server server2, CommandFactory commandFactory){
        this.server = server2;
        this.commandFactory = commandFactory;
    }

    @Override
    public void doCommand(String[] arguments){
        // validate arguments
        if(this.commandFactory.StringSymbolTable.get(arguments[1]) == null && !this.commandFactory.DoubleSymbolTable.containsKey(arguments[1])){
            System.out.println(arguments[1]);
            return;
        }
        else if((this.commandFactory.StringSymbolTable.get(arguments[1]) != null) && this.commandFactory.PathTable.containsKey(this.commandFactory.StringSymbolTable.get(arguments[1]))){
            System.out.println(this.commandFactory.PathTable.get(this.commandFactory.StringSymbolTable.get(arguments[1])));
            return;
        }
        else if(this.commandFactory.DoubleSymbolTable.containsKey(arguments[1])){
            System.out.println(this.commandFactory.DoubleSymbolTable.get(arguments[1]));
            return;
        }
        System.out.println(arguments[1]);
    }
}
