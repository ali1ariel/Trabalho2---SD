package trabalho2.Room;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import trabalho2.User.IUserChat;
import trabalho2.User.UserChatImpl;

public class RoomChat extends UnicastRemoteObject implements IRoomChat {
    private ArrayList<IUserChat> usrList;
    private String roomName;

    public RoomChat() throws RemoteException {
        super();
    }

    public RoomChat(String roomName) throws RemoteException {
        super();
        this.roomName = roomName;
    }
 
    public void joinRoom(String usrName, IUserChat user) throws RemoteException{
//        this.usrList.add(user);
        System.out.println(usrName);
    };
    public void sendMsg(String usrName, String msg) throws RemoteException {
        for(IUserChat user : usrList) {
            user.deliverMsg(usrName, msg);
        }
    };
    public void leaveRoom(String usrName) throws RemoteException {
        for(IUserChat user : usrList) {
            user.deliverMsg(usrName, "is leaving the room.");
        }
    };
    public void closeRoom() throws RemoteException {        
        for(IUserChat user : usrList) {
            user.deliverMsg("SYSTEM", "is closing the room.");
        }
    };
    public String getRoomName() throws RemoteException {
        return this.roomName;
    }
}
