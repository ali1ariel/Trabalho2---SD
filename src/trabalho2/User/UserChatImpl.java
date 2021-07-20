package trabalho2.User;

import java.io.IOException;
import java.net.MalformedURLException;
import trabalho2.Room.IRoomChat;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
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
        this.usrName = usrName;
    }
 

    public void deliverMsg(String senderName, String msg) throws RemoteException {
        
    	this.chatArea.append(String.format("%s: %s\r\n", senderName, msg));
    }
    
        public void start(Scanner sc) throws IOException {
//    	//new ChatWindow();
//    	do {
//	        LoginWindow loginWindow = new LoginWindow();
//	        username = loginWindow.Login();
//    	} while (username == null || username.isEmpty());
//        this.setUsername(username);
//        new ButtonMessage("Bem-vindo " + username + "!", 250, 100);
    }
    
    
    public void listRooms(IServerChat server) throws IOException {
        ArrayList<String> allRooms = server.getRooms();
            System.out.println("index - Room Name");
        int index = 1;
        for(String room : allRooms) {
            System.out.println(String.format("%i - %s", index, room));
            index ++;
        }                
    }
    
    public String getUsrName() {
        return this.usrName;
    }
    
    public void userJoin(String roomName) throws NotBoundException, MalformedURLException, RemoteException {
        IRoomChat roomInterface = (IRoomChat) Naming.lookup(String.format("%sROOM%s", Config.URI, roomName));
        roomInterface.joinRoom(this.getUsrName(), this);
    }

}
