import java.util.Date;

public class LightBulb extends Device implements EnergyReporting{
    private int brightness; // 亮度（0-100）
    private int colorTemp; // 颜色（如"白色"、"暖黄"）
    private double power = 10.0; // 默认功率10W
    public LightBulb(int deviceId, String name, Manufacturer manufacturer){
        super(deviceId,name,manufacturer);
    }
    public void setBrightness(int brightness){
        this.brightness=brightness;
    }
    public void setColorTemp(int color){
        this.colorTemp=color;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getColorTemp() {
        return colorTemp;
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
