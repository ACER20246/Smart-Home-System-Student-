import java.util.*;
public class AutomationScene {
    private int sceneId; // 场景ID
    private String name; // 场景名称（如"回家模式"、"睡眠模式"）
    private List<DeviceAction> actions = new ArrayList<>(); // 场景包含的设备动作
    private String description;
    public AutomationScene(int sceneId,String name,String description){
        this.sceneId=sceneId;
        this.name=name;
        this.description=description;
    }
    public String getName() {
        return name;
    }

    public int getSceneId() {
        return sceneId;
    }

    public String getDescription(){
        return description;
    }

    public void addAction(DeviceAction action){
        actions.add(action);
    }

    public void removeAction(DeviceAction action){
        actions.remove(action);
    }

    public List<DeviceAction> getActions(){
        return actions;
    }

    public void manualTrig(){
        System.out.println("执行场景: " + name);
        for(DeviceAction action : actions){
            action.execute();
        }
    }

    public String toString(){
        return "Automation{sceneId'"+sceneId+"',name'"+name+"',action'"+actions+"',description'"+description+"'}";
    }
}
