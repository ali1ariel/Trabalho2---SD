package trabalho2.Server;

import trabalho2.Room.RoomChat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ServerChatImpl extends UnicastRemoteObject implements IServerChat {
    private ArrayList<String> roomList;
    private static String SERVER = "localhost";
    private static Integer PORT = 2020;
    private static String SERVICE = "ServerChat";
    
    
    public static String getURI() {
        String uri = String.format("rmi://%s:%d/$s", SERVER, PORT, SERVICE);
        return uri;
    }

    
    
    public ServerChatImpl() throws RemoteException {
        super();
    }
    
    public ArrayList<String> getRooms() throws RemoteException {
        return roomList;
    }
    
    public void createRoom(String roomName) throws RemoteException{
        RoomChat created = new RoomChat(roomName);
        Integer size = this.roomList.size(); 
        try {
            Naming.rebind("Room"+(size+1), created);
        } catch (MalformedURLException e) {
        }
        this.roomList.add(roomName);
    }
    
    public static void main(String args[]) {
        try {
            ServerChatImpl server = new ServerChatImpl();
            Naming.rebind("Server", server);
            System.out.println("Ligado no registro");
        } catch(MalformedURLException | RemoteException ex) {
            System.err.println("error: " + ex.getMessage());
        }
    }
}
