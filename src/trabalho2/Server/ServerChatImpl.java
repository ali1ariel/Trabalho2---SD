package trabalho2.Server;

import trabalho2.Room.RoomChat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import trabalho2.Config;


public class ServerChatImpl extends UnicastRemoteObject implements IServerChat {
    private ArrayList<String> roomList = new ArrayList<String>();
     
    
    public ServerChatImpl() throws RemoteException {
        super();
    }
    
    public ArrayList<String> getRooms() throws RemoteException {
        return roomList;
    }
    
    public void createRoom(String roomName) throws RemoteException{
        try {
            synchronized (roomList) {
                RoomChat created = new RoomChat(roomName);
                roomList.add(roomName);
                Naming.rebind("ROOM"+roomName, created);
            }
        } catch (MalformedURLException e) {
        }
        roomList.add(roomName);
    }
}
