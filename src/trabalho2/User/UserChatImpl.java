package trabalho2.User;

import java.io.IOException;
import java.net.MalformedURLException;
import trabalho2.Room.IRoomChat;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import trabalho2.Config;
import trabalho2.Server.IServerChat;

import java.awt.TextArea;


public class UserChatImpl extends UnicastRemoteObject implements IUserChat {
    private String usrName;
    private TextArea chatArea;


    public UserChatImpl(TextArea ta) throws RemoteException {
        super();
        this.usrName = null;
        this.chatArea = ta;
    }
    
    public void setUsrName(String usrName) {
        System.out.println(usrName);
        this.usrName = usrName;
    }
 

    public void deliverMsg(String senderName, String msg) throws RemoteException {
        
    	this.chatArea.append(String.format("%s: %s\r\n", senderName, msg));
    }
   
    
    public void listRooms(IServerChat server) throws IOException {
        ArrayList<String> allRooms = server.getRooms();
            System.out.println("Room Name");
        for(String room : allRooms) {
            System.out.println(String.format("%s", room));
        }                
    }
    
    public void createRoom(IServerChat serverInterface, String roomName) throws Exception{
                serverInterface.createRoom(roomName);
    }
    
    public String getUsrName() {
        return this.usrName;
    }
    
    public void userJoin(String roomName) throws NotBoundException, MalformedURLException, RemoteException {
        IRoomChat roomInterface = (IRoomChat) Naming.lookup(String.format("%sROOM%s", Config.URI, roomName));
        roomInterface.joinRoom(this.getUsrName(), this);
    }

}
