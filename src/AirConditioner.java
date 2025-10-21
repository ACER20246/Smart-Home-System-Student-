import java.util.Date;

public class AirConditioner extends Device implements EnergyReporting{
    private double currTemp; // 当前温度（℃）
    private String mode; // 模式（如"制冷"、"制热"、"自动"）
    private int windSpeed; // 风速（1-3级）
    private double targetTemp;
    private double power = 1500.0; // 默认功率1500W
    public AirConditioner(int deviceId,String name,Manufacturer manufacturer){
        super(deviceId,name,manufacturer);
    }
    public void setCurrTemp(double temp){
        this.currTemp=temp;
    }
    public void setTargetTemp(double temp){
        this.targetTemp=temp;
    }

    public double getCurrTemp() {
        return currTemp;
    }

    public double getTargetTemp(){
        return targetTemp;
    }

    @Override
    public double getPower() {
        return power;
    }

    @Override
    public double getReport(Date startTime, Date endTime) {
        return power;
    }
}
