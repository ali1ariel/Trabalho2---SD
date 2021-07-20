package trabalho2;

public interface IRoomChat extends java.rmi.Remote {
    public void sendMsg(String usrName, String msg);

    public void joinRoom(String usrName, UserChat user);

    public void leaveRoom(String usrName);

    public void closeRoom();

    public String getRoomName();
}