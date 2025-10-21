import java.util.ArrayList;
import java.util.List;

/**
 * 制造商类
 * 管理设备制造商的基本信息和设备列表
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class Manufacturer {
    /** 制造商ID */
    private int manufacturerId;
    
    /** 制造商名称 */
    private String name;
    
    /** 支持的通信协议 */
    private String protocols;
    
    /** 制造商生产的设备列表 */
    private List<Device> devices=new ArrayList<>();
    
    /**
     * 构造函数，创建制造商实例
     * 
     * @param manufacturerId 制造商ID
     * @param name 制造商名称
     * @param protocols 支持的通信协议
     */
    public Manufacturer(int manufacturerId,String name,String protocols){
        this.manufacturerId=manufacturerId;
        this.name=name;
        this.protocols=protocols;
    }
    
    /**
     * 获取制造商ID
     * 
     * @return 制造商ID
     */
    public int getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 获取支持的通信协议
     * 
     * @return 通信协议字符串
     */
    public String getProtocols() {
        return protocols;
    }

    /**
     * 获取制造商名称
     * 
     * @return 制造商名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取制造商生产的设备列表
     * 
     * @return 设备列表
     */
    public List<Device> getDevices(){
        return devices;
    }

    /**
     * 添加设备到制造商
     * 
     * @param device 要添加的设备
     */
    public void addDevice(Device device) {
        devices.add(device);
    }

    /**
     * 从制造商移除设备
     * 
     * @param device 要移除的设备
     */
    public void removeDevice(Device device){
        devices.remove(device);
    }
}