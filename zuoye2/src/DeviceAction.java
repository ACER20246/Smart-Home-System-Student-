/**
 * 设备动作类
 * 定义设备执行的具体动作，包括命令、参数和目标设备
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class DeviceAction {
    /** 动作命令 */
    private String command;
    
    /** 动作参数 */
    private String parameters;
    
    /** 目标设备 */
    private Device device;
    
    /**
     * 构造函数，创建设备动作实例
     * 
     * @param command 动作命令
     * @param parameters 动作参数
     * @param device 目标设备
     */
    public DeviceAction(String command,String parameters,Device device){
        this.command=command;
        this.parameters=parameters;
        this.device=device;
    }

    /**
     * 获取动作命令
     * 
     * @return 动作命令
     */
    public String getCommand(){
        return command;
    }

    /**
     * 获取目标设备
     * 
     * @return 目标设备对象
     */
    public Device getDevice() {
        return device;
    }

    /**
     * 获取动作参数
     * 
     * @return 动作参数
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * 执行设备动作
     * 根据命令类型执行相应的设备操作
     * 支持的命令包括：
     * - powerOn: 开启设备
     * - powerOff: 关闭设备
     * - setTemperature: 设置温度（空调）
     * - setBrightness: 设置亮度（灯泡）
     * - setColorTemp: 设置色温（灯泡）
     * - setLocked: 设置锁定状态（门锁）
     */
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