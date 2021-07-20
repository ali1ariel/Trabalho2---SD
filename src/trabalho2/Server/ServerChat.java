package trabalho2.Server;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerChat {
    ServerChatImpl server;

    public ServerChat() {
        try {
            ServerChatImpl s = new ServerChatImpl();
            Naming.rebind(ServerChatImpl.getURI(), s);
            this.server = s;
        }
        catch(Exception e) {
                System.out.println("Erro: "+e);
        }
    }
    
    public static void main(String args[]) {
        try {
			System.out.println("Criando RMI...");
			Registry registry = LocateRegistry.createRegistry(2020);
			ServerChat server = new ServerChat();
			try {
				registry.bind("Server", server.server);
				System.out.println("Servidor executando...");
			} catch (AlreadyBoundException | AccessException ex) {
                System.out.println("Erro ao criar o servidor: " + ex.getMessage());
                return;
            }
		} catch(Exception ex) {
			System.out.println("ERRO: Servidor RMI não conseguiu iniciar..." + ex.getMessage());
		}
    }
}
