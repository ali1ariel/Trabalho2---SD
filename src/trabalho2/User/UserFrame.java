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

import java.awt.List;
import java.awt.TextArea;
import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;


public class UserFrame extends Frame {
    private UserChatImpl user;
    private IServerChat server;
    private IRoomChat room;
    private String name;
    private ArrayList<String> roomNames = this.server.getRooms();
    private ArrayList<String> usersInTheRoom = new ArrayList();
    private TextArea chatArea = new TextArea(20,70);
    private TextArea entryArea = new TextArea(5,70);
    private TextArea roomsArea = new TextArea(5,70);
    private List clientList = new List(20, true);
    private Button sendButton = new Button("Send");
    private Button logoutButton = new Button("Logout");
    
    public UserFrame(IServerChat server, String clientName) throws RemoteException {
        super(String.format("Chat Client - %s", clientName));
        this.server = server;
        this.name = clientName;
        this.user = new UserChatImpl(chatArea);
           this.setBounds(0,0,700,500);
           this.setupComponents();
           this.setupEvents();
    }
    
    private void setupComponents() {
        this.setLayout(new FlowLayout());
        this.chatArea.setEditable(false);
        this.add(roomsArea);
        this.add(chatArea);
        this.add(clientList);
        this.add(entryArea);
        Panel p = new Panel();
        p.add(sendButton);
        p.add(logoutButton);
        this.add(p);
    }
    
    private void setupEvents() {
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we) {
                logout();
            }
        });
        
        this.logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent we) {
                logout();
            }
        });
        
        this.sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent we) {
                try {            
                    room.sendMsg(user.getUsrName(), entryArea.getText());
                } 
                catch(Exception re) {
                    re.printStackTrace();
                }
                entryArea.setText("");
            }
        });
        
        
        
    }
    
    private void logout() {
//        try{
//            this.server.logout(this.name);
//        }catch(RemoteException re) {
//            re.printStackTrace();
//        }
//        this.dispose();
//        System.exit(0);
    }
    
    private void login(String roomName) throws NotBoundException, MalformedURLException {
        try{
            this.user.userJoin(roomName);
        }catch(RemoteException re) {
        }
        this.dispose();
        System.exit(0);
    }


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        
    String clientName = "tester";
    IServerChat serverInterface = (IServerChat) Naming.lookup(String.format("%s%s", Config.URI, Config.SERVER));
    UserFrame frame = new UserFrame(serverInterface, clientName);
    }
}

