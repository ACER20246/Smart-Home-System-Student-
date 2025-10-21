import java.util.Date;

/**
 * 空调设备类
 * 继承自Device，实现EnergyReporting接口，支持温度控制和能源监控
 * 
 * @author 开发者
 * @version 1.0
 */
public class AirConditioner extends Device implements EnergyReporting{
    /** 当前温度（℃） */
    private double currTemp;
    
    /** 模式（如"制冷"、"制热"、"自动"） */
    private String mode;
    
    /** 风速（1-3级） */
    private int windSpeed;
    
    /** 目标温度 */
    private double targetTemp;
    
    /** 设备功率（瓦特） */
    private double power = 1500.0;
    // 默认功率1500W
    
    /**
     * 构造函数，创建空调设备实例
     * 
     * @param deviceId 设备ID
     * @param name 设备名称
     * @param manufacturer 制造商
     */
    public AirConditioner(int deviceId,String name,Manufacturer manufacturer){
        super(deviceId,name,manufacturer);
    }
    
    /**
     * 设置当前温度
     * 
     * @param temp 当前温度值
     */
    public void setCurrTemp(double temp){
        this.currTemp=temp;
    }
    
    /**
     * 设置目标温度
     * 
     * @param temp 目标温度值
     */
    public void setTargetTemp(double temp){
        this.targetTemp=temp;
    }

    /**
     * 获取当前温度
     * 
     * @return 当前温度值
     */
    public double getCurrTemp() {
        return currTemp;
    }

    /**
     * 获取目标温度
     * 
     * @return 目标温度值
     */
    public double getTargetTemp(){
        return targetTemp;
    }

    /**
     * 获取设备当前功率
     * 
     * @return 功率值（瓦特）
     */
    @Override
    public double getPower() {
        return power;
    }

    /**
     * 获取指定时间段的能耗报告
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 能耗值（千瓦时）
     */
    @Override
    public double getReport(Date startTime, Date endTime) {
        return power;
    }
}