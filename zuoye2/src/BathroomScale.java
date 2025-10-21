/**
 * 浴室秤设备类
 * 继承自Device，支持体重测量和电池电量监控
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class BathroomScale extends Device{
    /** 当前体重（kg） */
    private double bodyMass;
    
    /** 电池电量百分比 */
    private int batteryLevel;
    
    /**
     * 构造函数，创建浴室秤设备实例
     * 
     * @param deviceId 设备ID
     * @param name 设备名称
     * @param manufacturer 制造商
     */
    public BathroomScale(int deviceId,String name,Manufacturer manufacturer){
        super(deviceId,name,manufacturer);
    }
    
    /**
     * 设置体重
     * 
     * @param currentWeight 体重值（kg）
     */
    public void setBodyMass(double currentWeight){
        this.bodyMass=currentWeight;
    }
    
    /**
     * 设置电池电量
     * 
     * @param batteryLevel 电池电量百分比（0-100）
     */
    public void setBatteryLevel(int batteryLevel){
        this.batteryLevel=batteryLevel;
    }

    /**
     * 获取电池电量
     * 
     * @return 电池电量百分比（0-100）
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * 获取体重
     * 
     * @return 体重值（kg）
     */
    public double getBodyMass(){
        return bodyMass;
    }
}