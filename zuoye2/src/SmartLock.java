/**
 * 智能门锁设备类
 * 继承自Device，支持锁定状态控制和电池电量监控
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class SmartLock extends Device{
    /** 是否锁定 */
    private boolean isLocked;
    
    /** 电池电量百分比 */
    private int batteryLevel;
    
    /**
     * 构造函数，创建智能门锁设备实例
     * 
     * @param deviceId 设备ID
     * @param name 设备名称
     * @param manufacturer 制造商
     */
    public SmartLock(int deviceId,String name,Manufacturer manufacturer){
        super(deviceId,name,manufacturer);
    }
    
    /**
     * 设置锁定状态
     * 
     * @param isLocked 锁定状态，true为锁定，false为解锁
     */
    public void setLocked(boolean isLocked){
        this.isLocked=isLocked;
    }
    
    /**
     * 检查是否锁定
     * 
     * @return 锁定返回true，解锁返回false
     */
    public boolean isLocked(){
        return isLocked;
    }
    
    /**
     * 获取电池电量
     * 
     * @return 电池电量百分比（0-100）
     */
    public int getBatteryLevel(){
        return batteryLevel;
    }
    
    /**
     * 设置电池电量
     * 
     * @param batteryLevel 电池电量百分比（0-100）
     */
    public void setBatteryLevel(int batteryLevel){
        this.batteryLevel=batteryLevel;
    }
}