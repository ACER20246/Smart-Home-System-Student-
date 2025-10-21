import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomId; // 房间ID
    private String roomName; // 房间名称（如"客厅"、"卧室"）
    private double area;
    private List<Device> devices = new ArrayList<>(); // 房间内的设备
    public Room(int roomId,String roomName,double area){
        this.roomName=roomName;
        this.roomId=roomId;
        this.area=area;
    }
    public String getName() {
        return roomName;
    }
    public int getRoomId(){
        return roomId;
    }
    public void addDevice(Device device){
        devices.add(device);
    }
    public double getArea(){
        return area;
    }
    public void removeDevice(int deviceId){
        devices.removeIf(device -> device.getDeviceId()==deviceId);
    }
    public List<Device> getDevices(){
        return devices;
    }
    public String toString(){
        return "Room={roomId='"+roomId+"',roomName='"+roomName+"'}";
    }
    public boolean equals(Object obj){
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        Room other=(Room)obj;
        return roomId==other.roomId;
    }
}
