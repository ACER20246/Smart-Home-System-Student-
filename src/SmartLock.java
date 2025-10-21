public class SmartLock extends Device{
    private boolean isLocked; // 是否锁定
    private int batteryLevel;
    public SmartLock(int deviceId,String name,Manufacturer manufacturer){
        super(deviceId,name,manufacturer);
    }
    public void setLocked(boolean isLocked){
        this.isLocked=isLocked;
    }
    public boolean isLocked(){
        return isLocked;
    }
    public int getBatteryLevel(){
        return batteryLevel;
    }
    public void setBatteryLevel(int batteryLevel){
        this.batteryLevel=batteryLevel;
    }
}
