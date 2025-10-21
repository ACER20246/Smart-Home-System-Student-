import java.time.*;
import java.util.Date;
import java.util.List;

/**
 * 智能家居系统主控制器
 * 提供用户管理、设备控制、场景管理、能源监控等核心功能
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class HomeSphereSystem {
    /** 家庭管理对象 */
    private Household household;
    
    /** 当前登录用户 */
    private User currentUser;
    
    /**
     * 构造函数，创建智能家居系统实例
     * 
     * @param household 家庭管理对象
     */
    public HomeSphereSystem(Household household){
        this.household=household;
    }
    /**
     * 用户登录功能
     * 
     * @param loginName 登录用户名
     * @param loginPassword 登录密码
     * @return 登录成功返回true，失败返回false
     */
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
    /**
     * 用户登出功能
     */
    public void logoff(){
        if(currentUser != null){
            System.out.println("用户 " + currentUser.getUserName() + " 已登出");
            currentUser = null;
        }
    }
    
    /**
     * 获取当前登录用户
     * 
     * @return 当前用户对象，未登录时返回null
     */
    public User getCurrentUser(){
        return currentUser;
    }
    /**
     * 用户注册功能
     * 
     * @param loginName 登录用户名
     * @param loginPassword 登录密码
     * @param userName 用户显示名称
     * @param email 用户邮箱
     * @return 新创建的用户对象
     */
    public User register(String loginName, String loginPassword, String userName, String email){
        int newUserId = household.getUsers().size() + 1;
        User newUser = new User(newUserId, loginName, loginPassword, userName, email);
        household.addUser(newUser);
        System.out.println("用户 " + userName + " 注册成功");
        return newUser;
    }
    /**
     * 显示所有用户列表
     */
    public void displayUsers(){
        System.out.println("=== 用户列表 ===");
        for(User user : household.getUsers()){
            System.out.println(user);
        }
    }
    
    /**
     * 显示所有房间列表
     */
    public void displayRooms(){
        System.out.println("=== 房间列表 ===");
        for(Room room : household.getRooms()){
            System.out.println(room);
        }
    }
    
    /**
     * 显示所有设备列表
     */
    public void displayDevices(){
        System.out.println("=== 设备列表 ===");
        for(Device device : household.listAllDevices()){
            System.out.println(device);
        }
    }
    
    /**
     * 显示所有自动化场景列表
     */
    public void displayAutoScenes(){
        System.out.println("=== 自动化场景列表 ===");
        for(AutomationScene scene : household.getAutoScenes()){
            System.out.println(scene);
        }
    }
    /**
     * 显示能源使用报告
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    public void displayEnergyReportings(Date startTime, Date endTime){
        System.out.println("=== 能源报告 ===");
        System.out.println("时间范围: " + startTime + " 到 " + endTime);
        for(Device device : household.listAllDevices()){
            if(device instanceof EnergyReporting energyDevice){
                double power = energyDevice.getPower();
                double report = energyDevice.getReport(startTime, endTime);
                System.out.println("设备: " + device.getName() + ", 功率: " + power + "W, 能耗: " + report + "kWh");
            }
        }
    }
    /**
     * 手动触发指定ID的自动化场景
     * 
     * @param sceneId 场景ID
     */
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
