package trabalho2;
import java.util.ArrayList;

public class RoomChat implements IRoomChat {
    private ArrayList<String> roomList;
 
    public void joinRoom(String usrName, UserChat user){};
    public void sendMsg(String usrName, String msg){};
    public void leaveRoom(String usrName){};
    public void closeRoom(){};
    public String getRoomName() {
        return "no name";
    }
}
