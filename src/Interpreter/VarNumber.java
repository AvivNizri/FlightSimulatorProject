package Interpreter;

import Commands.CommandFactory;

public class VarNumber implements Expression{
    CommandFactory commandFactory;
    String str;

    public VarNumber(String str, CommandFactory commandFactory){
        this.commandFactory = commandFactory;
        this.str = str;
    }

    @Override
    public Double calculate(){
        if(this.commandFactory.StringSymbolTable.containsKey(str)){
            return (this.commandFactory.PathTable.get(commandFactory.StringSymbolTable.get(str)));
        }
        return (this.commandFactory.DoubleSymbolTable.get(str));
    }
}
