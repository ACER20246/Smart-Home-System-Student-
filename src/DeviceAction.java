public class DeviceAction {
    private String command;
    private String parameters;
    private Device device;
    public DeviceAction(String command,String parameters,Device device){
        this.command=command;
        this.parameters=parameters;
        this.device=device;
    }

    public String getCommand(){
        return command;
    }

    public Device getDevice() {
        return device;
    }

    public String getParameters() {
        return parameters;
    }

    public void execute(){
        if(device == null) return;
        
        switch(command){
            case "powerOn":
                device.powerOn();
                break;
            case "powerOff":
                device.powerOff();
                break;
            case "setTemperature":
                if(device instanceof AirConditioner){
                    double temp = Double.parseDouble(parameters);
                    ((AirConditioner) device).setTargetTemp(temp);
                }
                break;
            case "setBrightness":
                if(device instanceof LightBulb){
                    int brightness = Integer.parseInt(parameters);
                    ((LightBulb) device).setBrightness(brightness);
                }
                break;
            case "setColorTemp":
                if(device instanceof LightBulb){
                    int colorTemp = Integer.parseInt(parameters);
                    ((LightBulb) device).setColorTemp(colorTemp);
                }
                break;
            case "setLocked":
                if(device instanceof SmartLock){
                    boolean locked = Boolean.parseBoolean(parameters);
                    ((SmartLock) device).setLocked(locked);
                }
                break;
        }

    }

}
