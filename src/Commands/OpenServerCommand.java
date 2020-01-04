package Commands;

import ClientServer.Server;

public class OpenServerCommand implements Command {
    private Server server;

    public OpenServerCommand(Server server2) {
        this.server = server2;
    }

    public Server getServer() {
        return this.server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public void doCommand(String[] arguments) {
        //"openDataServer "+ (port+1)+" 10",
        int Port = Integer.parseInt(arguments[1]);
        int hz = Integer.parseInt(arguments[2]);
        server.OpenServer(Port,hz);
    }
}
