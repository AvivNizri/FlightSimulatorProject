package Commands;

import ClientServer.Client;

public class ConnectToClientCommand implements Command {
    private Client client;

    public ConnectToClientCommand(Client client2) {
        this.client = client2;
    }

    @Override
    public void doCommand(String[] arguments) {
        //"connect 127.0.0.1 "+port,
        String ConnectIP = arguments[1];
        int ConnectPORT = Integer.parseInt(arguments[2]);
        try {
            client.connect(ConnectIP, ConnectPORT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
