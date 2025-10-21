import java.util.List;

/**
 * 设备抽象基类
 * 所有智能设备的基础类，提供设备的基本属性和功能
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public abstract class Device {
    /** 设备ID */
    private int deviceId;
    
    /** 设备名称 */
    private String name;
    
    /** 是否在线 */
    private boolean isOnline;
    
    /** 电源状态（true为开，false为关） */
    private boolean powerStatus;
    
    /** 设备制造商 */
    private Manufacturer manufacturer;
    
    /** 所属房间 */
    private Room belongToRoom;
    
    /** 运行日志列表 */
    private List<RunningLog> runningLogs;
    
    /**
     * 构造函数，创建设备实例
     * 
     * @param deviceId 设备ID
     * @param name 设备名称
     * @param manufacturer 制造商
     */
    public Device(int deviceId,String name,Manufacturer manufacturer){
        this.deviceId=deviceId;
        this.name=name;
        this.manufacturer=manufacturer;
    }
    
    /**
     * 获取设备ID
     * 
     * @return 设备ID
     */
    public int getDeviceId(){
        return deviceId;
    }
    
    /**
     * 获取设备名称
     * 
     * @return 设备名称
     */
    public String getName(){
        return name;
    }
    
    /**
     * 检查设备是否在线
     * 
     * @return 在线返回true，离线返回false
     */
    public boolean isOnline(){
        return isOnline;
    }
    
    /**
     * 获取电源状态
     * 
     * @return 开启返回true，关闭返回false
     */
    public boolean getPowerStatus(){
        return powerStatus;
    }
    
    /**
     * 开启设备电源
     */
    public void powerOn(){
        this.powerStatus=true;
    }
    
    /**
     * 关闭设备电源
     */
    public void powerOff(){
        this.powerStatus=false;
    }

    /**
     * 检查电源状态
     * 
     * @return 开启返回true，关闭返回false
     */
    public boolean isPowerStatus() {
        return powerStatus;
    }

    /**
     * 获取运行日志列表
     * 
     * @return 运行日志列表
     */
    public List<RunningLog> getRunningLogs() {
        return runningLogs;
    }

    /**
     * 判断两个设备是否相等
     * 基于设备ID进行比较
     * 
     * @param obj 要比较的对象
     * @return 相等返回true，否则返回false
     */
    public boolean equals(Object obj) {
        // 1. 自反性：自身与自身相等
        if (this == obj) return true;
        // 2. 非空校验：obj为null则不相等
        if (obj == null) return false;
        // 3. 类型校验：不同类的对象不相等（避免父类与子类误判）
        if (getClass() != obj.getClass()) return false;
        // 4. 强制转换为Device类型
        Device other = (Device) obj;
        // 5. 核心判断：基于唯一标识deviceId是否相等
        return deviceId == other.deviceId;
    }
    
    /**
     * 返回设备的字符串表示
     * 
     * @return 设备信息字符串
     */
    public String toString() {
        return "Device{" +
             "deviceId=" + deviceId + ", name='" + name + '\'' +
                ", 状态=" + (isOnline ? "在线" : "离线") +
                ", 电源=" + (powerStatus ? "开启" : "关闭") +
                ", 制造商=" + (manufacturer != null ? manufacturer.getName() : "未知") +
                ", 所属房间=" + (belongToRoom != null ? belongToRoom.getName() : "未分配") +
                '}';
    }
}