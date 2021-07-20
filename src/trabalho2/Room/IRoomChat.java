package trabalho2.Room;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import trabalho2.User.IUserChat;

public interface IRoomChat extends java.rmi.Remote {
    public void sendMsg(String usrName, String msg) throws RemoteException, NotBoundException, MalformedURLException;

    public void joinRoom(String usrName, IUserChat user) throws RemoteException, NotBoundException, MalformedURLException;

    public void leaveRoom(String usrName) throws RemoteException, NotBoundException, MalformedURLException;

    public void closeRoom() throws RemoteException, NotBoundException, MalformedURLException;
    public String getRoomName() throws RemoteException;
}