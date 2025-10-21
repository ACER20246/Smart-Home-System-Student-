import java.time.*;
import java.util.Date;
import java.util.List;
public class HomeSphereSystem {
    private Household household;
    private User currentUser;
    public HomeSphereSystem(Household household){
        this.household=household;
    }
    public boolean login(String loginName, String loginPassword){
        for(User user : household.getUsers()){
            if(user.getLoginName().equals(loginName) && user.getLoginPassword().equals(loginPassword)){
                currentUser = user;
                System.out.println("用户 " + user.getUserName() + " 登录成功");
                return true;
            }
        }
        System.out.println("登录失败：用户名或密码错误");
        return false;
    }
    public void logoff(){
        if(currentUser != null){
            System.out.println("用户 " + currentUser.getUserName() + " 已登出");
            currentUser = null;
        }
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public User register(String loginName, String loginPassword, String userName, String email){
        int newUserId = household.getUsers().size() + 1;
        User newUser = new User(newUserId, loginName, loginPassword, userName, email);
        household.addUser(newUser);
        System.out.println("用户 " + userName + " 注册成功");
        return newUser;
    }
    public void displayUsers(){
        System.out.println("=== 用户列表 ===");
        for(User user : household.getUsers()){
            System.out.println(user);
        }
    }
    public void displayRooms(){
        System.out.println("=== 房间列表 ===");
        for(Room room : household.getRooms()){
            System.out.println(room);
        }
    }
    public void displayDevices(){
        System.out.println("=== 设备列表 ===");
        for(Device device : household.listAllDevices()){
            System.out.println(device);
        }
    }
    public void displayAutoScenes(){
        System.out.println("=== 自动化场景列表 ===");
        for(AutomationScene scene : household.getAutoScenes()){
            System.out.println(scene);
        }
    }
    public void displayEnergyReportings(Date startTime, Date endTime){
        System.out.println("=== 能源报告 ===");
        System.out.println("时间范围: " + startTime + " 到 " + endTime);
        for(Device device : household.listAllDevices()){
            if(device instanceof EnergyReporting){
                EnergyReporting energyDevice = (EnergyReporting) device;
                double power = energyDevice.getPower();
                double report = energyDevice.getReport(startTime, endTime);
                System.out.println("设备: " + device.getName() + ", 功率: " + power + "W, 能耗: " + report + "kWh");
            }
        }
    }
    public void manualTrigSceneById(int sceneId){
        for(AutomationScene scene : household.getAutoScenes()){
            if(scene.getSceneId() == sceneId){
                System.out.println("触发场景: " + scene.getName());
                scene.manualTrig();
                return;
            }
        }
        System.out.println("未找到场景ID: " + sceneId);
    }
}
