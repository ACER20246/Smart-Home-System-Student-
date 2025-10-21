import java.util.Date;

/**
 * 智能灯泡设备类
 * 继承自Device，实现EnergyReporting接口，支持亮度、色温控制和能源监控
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class LightBulb extends Device implements EnergyReporting{
    /** 亮度（0-100） */
    private int brightness;
    
    /** 色温（开尔文） */
    private int colorTemp;
    
    /** 设备功率（瓦特） */
    private double power = 10.0; // 默认功率10W
    
    /**
     * 构造函数，创建智能灯泡设备实例
     * 
     * @param deviceId 设备ID
     * @param name 设备名称
     * @param manufacturer 制造商
     */
    public LightBulb(int deviceId, String name, Manufacturer manufacturer){
        super(deviceId,name,manufacturer);
    }
    
    /**
     * 设置亮度
     * 
     * @param brightness 亮度值
     */
    public void setBrightness(int brightness){
        this.brightness=brightness;
    }
    
    /**
     * 设置色温
     * 
     * @param color 色温值（开尔文）
     */
    public void setColorTemp(int color){
        this.colorTemp=color;
    }

    /**
     * 获取亮度
     * 
     * @return 亮度值
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * 获取色温
     * 
     * @return 色温值
     */
    public int getColorTemp() {
        return colorTemp;
    }

    /**
     * 获取设备当前功率
     * 
     * @return 功率值
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