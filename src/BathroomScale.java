public class BathroomScale extends Device{
    private double bodyMass; // 当前体重（kg）
    private int batteryLevel;
    public BathroomScale(int deviceId,String name,Manufacturer manufacturer){
        super(deviceId,name,manufacturer);
    }
    public void setBodyMass(double currentWeight){
        this.bodyMass=currentWeight;
    }
    public void setBatteryLevel(int batteryLevel){
        this.batteryLevel=batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public double getBodyMass(){
        return bodyMass;
    }
}
