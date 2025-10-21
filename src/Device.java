import java.util.List;

public abstract class Device {
    private int deviceId; // 设备ID
    private String name; // 设备名称
    private boolean isOnline; // 是否在线
    private boolean powerStatus; // 电源状态（true为开，false为关）
    private Manufacturer manufacturer; // 设备制造商
    private Room belongToRoom; // 所属房间
    private List<RunningLog> runningLogs;
    public Device(int deviceId,String name,Manufacturer manufacturer){
        this.deviceId=deviceId;
        this.name=name;
        this.manufacturer=manufacturer;
    }
    public int getDeviceId(){
        return deviceId;
    }
    public String getName(){
        return name;
    }
    public boolean isOnline(){
        return isOnline;
    }
    public boolean getPowerStatus(){
        return powerStatus;
    }
    public void powerOn(){
        this.powerStatus=true;
    }
    public void powerOff(){
        this.powerStatus=false;
    }

    public boolean isPowerStatus() {
        return powerStatus;
    }

    public List<RunningLog> getRunningLogs() {
        return runningLogs;
    }

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
