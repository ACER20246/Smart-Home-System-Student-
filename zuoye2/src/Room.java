import java.util.ArrayList;
import java.util.List;

/**
 * 房间类
 * 管理房间的基本信息和设备列表
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class Room {
    /** 房间ID */
    private int roomId;
    
    /** 房间名称（如"客厅"、"卧室"） */
    private String roomName;
    
    /** 房间面积（平方米） */
    private double area;
    
    /** 房间内的设备列表 */
    private List<Device> devices = new ArrayList<>();
    
    /**
     * 构造函数，创建房间实例
     * 
     * @param roomId 房间ID
     * @param roomName 房间名称
     * @param area 房间面积
     */
    public Room(int roomId,String roomName,double area){
        this.roomName=roomName;
        this.roomId=roomId;
        this.area=area;
    }
    
    /**
     * 获取房间名称
     * 
     * @return 房间名称
     */
    public String getName() {
        return roomName;
    }
    
    /**
     * 获取房间ID
     * 
     * @return 房间ID
     */
    public int getRoomId(){
        return roomId;
    }
    
    /**
     * 添加设备到房间
     * 
     * @param device 要添加的设备
     */
    public void addDevice(Device device){
        devices.add(device);
    }
    
    /**
     * 获取房间面积
     * 
     * @return 房间面积（平方米）
     */
    public double getArea(){
        return area;
    }
    
    /**
     * 从房间移除设备
     * 
     * @param deviceId 要移除的设备ID
     */
    public void removeDevice(int deviceId){
        devices.removeIf(device -> device.getDeviceId() == deviceId);
    }
    
    /**
     * 获取房间内的设备列表
     * 
     * @return 设备列表
     */
    public List<Device> getDevices(){
        return devices;
    }
    
    /**
     * 返回房间的字符串表示
     * 
     * @return 房间信息字符串
     */
    public String toString(){
        return "Room={roomId='"+roomId+"',roomName='"+roomName+"'}";
    }
    
    /**
     * 判断两个房间是否相等
     * 基于房间ID进行比较
     * 
     * @param obj 要比较的对象
     * @return 相等返回true，否则返回false
     */
    public boolean equals(Object obj){
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        Room other=(Room)obj;
        return roomId==other.roomId;
    }
}