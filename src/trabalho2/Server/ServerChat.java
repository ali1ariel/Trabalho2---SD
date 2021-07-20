package trabalho2.Server;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import trabalho2.Config;

public class ServerChat {
    ServerChatImpl server;

    public ServerChat() {
        try {
            ServerChatImpl s = new ServerChatImpl();
            this.server = s;
        }
        catch(Exception e) {
                System.out.println("Error: "+e);
        }
    }
    
    public static void main(String args[]) {
        try {
			System.out.println("Starting RMI...");
			Registry registry = LocateRegistry.createRegistry(Config.PORT);
			ServerChat server = new ServerChat();
			try {
				registry.bind(Config.SERVER, server.server);
				System.out.println("Server " + Config.SERVER + " executing in PORT " + Config.PORT + "...");
			} catch (AlreadyBoundException | AccessException ex) {
                System.out.println("Error: " + ex.getMessage() +" - "+ex.getClass());
                System.exit(0);
                return;
            }
		} catch(Exception ex) {
			System.out.println("ERROR: Server RMI  on PORT " + Config.PORT + "didn`t start..." + ex.getMessage());
		}
    }
}
