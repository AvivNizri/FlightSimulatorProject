package Commands;

import ClientServer.Client;

public class DisconnectCommand implements Command{
    CommandFactory commandFactory;
    Client client;

    public DisconnectCommand(Client client2){
        this.client = client2;
    }

    @Override
    public void doCommand(String[] arguments){
        client.disconnect();
    }
}
