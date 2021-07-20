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
    private ArrayList<String> roomNames = new ArrayList();
    private ArrayList<String> usersInTheRoom = new ArrayList();
    private TextArea chatArea = new TextArea(20,70);
    private TextArea setNameArea = new TextArea(2,70);
    private TextArea entryArea = new TextArea(5,90);
    private List clientList = new List(20, true);
    private List roomsList = new List(18, true);
    private TextArea createroomArea = new TextArea(2,10);
    private Button sendButton = new Button("Send");
    private Button logoutButton = new Button("Logout");
    private Button changeUserNameButton = new Button("Change your username");
    private Button newRoomButton = new Button("create room");
    
    public UserFrame(IServerChat server, String clientName) throws RemoteException {
        super(String.format("Chat Client - %s", clientName));
        this.server = server;
        this.roomNames = server.getRooms();
        this.name = clientName;
        this.user = new UserChatImpl(chatArea);
           this.setBounds(0,0,700,500);
           this.setupComponents();
           this.setupEvents();
    }
    
    private void setupComponents() {
        this.setLayout(new FlowLayout());
        this.chatArea.setEditable(false);
        this.add(roomsList);
        this.add(createroomArea);
        this.add(chatArea);
        this.add(clientList);
        this.add(entryArea);
        this.add(setNameArea);
        Panel p = new Panel();
        p.add(sendButton);
        p.add(logoutButton);
        p.add(changeUserNameButton);
        p.add(newRoomButton);
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
        
        this.changeUserNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent we) {
                try {            
                    user.setUsrName(setNameArea.getText());
                    System.out.println("Oi!");
                } 
                catch(Exception re) {
                    re.printStackTrace();
                }
                setNameArea.setText("");
            }
        });     
        
        this.newRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent we) {
                try {            
                    user.createRoom(server, createroomArea.getText());
                    System.out.println("Oi!");
                } 
                catch(Exception re) {
                    re.printStackTrace();
                }
                createroomArea.setText("");
            }
        });
        
        
        
    }
    
    private void createRoom(String roomName) throws Exception {
        user.createRoom(server, roomName);
        this.roomNames = server.getRooms();
                            System.out.println("Ok!");

    }
    
    private void logout() {
//        try{
////            this.server.logout(this.name);
//        }catch(RemoteException re) {
//            re.printStackTrace();
//        }
        this.dispose();
        System.exit(0);
    }
    
    private void login(String roomName) throws NotBoundException, MalformedURLException {
        try{
            this.user.userJoin(roomName);
        }catch(RemoteException re) {
        }
    }


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        
    String clientName = "tester";
    IServerChat serverInterface = (IServerChat) Naming.lookup(""+Config.URI+""+Config.SERVER+"");
    
    UserFrame frame = new UserFrame(serverInterface, clientName);
    frame.setVisible(true);
    }
}

