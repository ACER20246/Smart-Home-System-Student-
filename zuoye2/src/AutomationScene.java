import java.util.*;

/**
 * 自动化场景类
 * 管理自动化场景的配置和执行，包含多个设备动作
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class AutomationScene {
    /** 场景ID */
    private int sceneId;
    
    /** 场景名称（如"回家模式"、"睡眠模式"） */
    private String name;
    
    /** 场景包含的设备动作列表 */
    private List<DeviceAction> actions = new ArrayList<>();
    
    /** 场景描述 */
    private String description;
    
    /**
     * 构造函数，创建自动化场景实例
     * 
     * @param sceneId 场景ID
     * @param name 场景名称
     * @param description 场景描述
     */
    public AutomationScene(int sceneId,String name,String description){
        this.sceneId=sceneId;
        this.name=name;
        this.description=description;
    }
    
    /**
     * 获取场景名称
     * 
     * @return 场景名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取场景ID
     * 
     * @return 场景ID
     */
    public int getSceneId() {
        return sceneId;
    }

    /**
     * 获取场景描述
     * 
     * @return 场景描述
     */
    public String getDescription(){
        return description;
    }

    /**
     * 添加设备动作到场景
     * 
     * @param action 要添加的设备动作
     */
    public void addAction(DeviceAction action){
        actions.add(action);
    }

    /**
     * 从场景中移除设备动作
     * 
     * @param action 要移除的设备动作
     */
    public void removeAction(DeviceAction action){
        actions.remove(action);
    }

    /**
     * 获取场景中的所有设备动作
     * 
     * @return 设备动作列表
     */
    public List<DeviceAction> getActions(){
        return actions;
    }

    /**
     * 手动触发场景执行
     * 执行场景中的所有设备动作
     */
    public void manualTrig(){
        System.out.println("执行场景: " + name);
        for(DeviceAction action : actions){
            action.execute();
        }
    }

    /**
     * 返回场景的字符串表示
     * 
     * @return 场景信息字符串
     */
    public String toString(){
        return "Automation{sceneId'"+sceneId+"',name'"+name+"',action'"+actions+"',description'"+description+"'}";
    }
}