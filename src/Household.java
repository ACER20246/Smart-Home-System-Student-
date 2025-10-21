import java.lang.*;
import java.util.*;
public class Household {
    private int houseHoldId;
    private String address;
    private List<User> users=new ArrayList<>();
    private List<Room> rooms=new ArrayList<>();
    private User admin;
    private List<AutomationScene> autoSences = new ArrayList<>();
    public Household(int houseHoldId,String address){
        this.houseHoldId=houseHoldId;
        this.address=address;
    }
    public int getHouseholdId(){
        return houseHoldId;
    }
    public String getAddress(){
        return address;
    }
    public void addRoom(Room room){
        rooms.add(room);
    }
    public void removeRoom(int roomId){
        rooms.removeIf(room -> room.getRoomId() == roomId);
    }
    public List<Room> getRooms(){
        return rooms;
    }
    public void addUser(User user){
        users.add(user);
    }
    public void removeUser(int userId){
        users.removeIf(user -> user.getUserId() == userId);
    }
    public List<User> getUsers(){
        return users;
    }
    public void addAutoScene(AutomationScene autoScene){
        autoSences.add(autoScene);
    }
    public void removeAutoScene(int sceneId){
        autoSences.removeIf(scene -> scene.getSceneId() == sceneId);
    }
    public List<AutomationScene> getAutoScenes(){
        return autoSences;
    }
    public List<Device> listAllDevices(){
        List<Device> devices = new ArrayList<>();
        for(Room room : rooms){
            devices.addAll(room.getDevices());
        }
        return devices;
    }
    public void setAdmin(User admin) {
        admin.setAdmin();
        this.admin=admin;
    }
}
