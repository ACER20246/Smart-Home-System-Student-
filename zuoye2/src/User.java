/**
 * 用户类
 * 管理用户的基本信息、登录凭据和权限
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class User {
    /** 用户ID */
    private int userId;
    
    /** 登录用户名 */
    private String logName;
    
    /** 登录密码 */
    private String password;
    
    /** 用户显示名称 */
    private String userName;
    
    /** 用户邮箱 */
    private String email;
    
    /** 是否为管理员 */
    private boolean isAdmin;
    
    /**
     * 构造函数，创建用户实例
     * 
     * @param userId 用户ID
     * @param logName 登录用户名
     * @param password 登录密码
     * @param userName 用户显示名称
     * @param email 用户邮箱
     */
    public User(int userId,String logName,String password,String userName,String email){
        this.email=email;
        this.logName=logName;
        this.password=password;
        this.userId=userId;
        this.userName=userName;
    }
    
    /**
     * 获取用户ID
     * 
     * @return 用户ID
     */
    public int getUserId(){
        return userId;
    }
    
    /**
     * 获取登录用户名
     * 
     * @return 登录用户名
     */
    public String getLoginName(){
        return logName;
    }
    
    /**
     * 获取登录密码
     * 
     * @return 登录密码
     */
    public String getLoginPassword(){
        return password;
    }
    
    /**
     * 获取用户显示名称
     * 
     * @return 用户显示名称
     */
    public String getUserName(){
        return userName;
    }
    
    /**
     * 获取用户邮箱
     * 
     * @return 用户邮箱
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * 检查是否为管理员
     * 
     * @return 管理员返回true，普通用户返回false
     */
    public boolean isAdmin(){
        return isAdmin;
    }
    
    /**
     * 设置为管理员
     */
    public void setAdmin(){
        this.isAdmin=true;
    }
    
    /**
     * 返回用户的字符串表示
     * 
     * @return 用户信息字符串
     */
    public String toString(){
        return "User{userId='"+userId+"',logName='"+logName+"'," +
                "password='"+password+"',userName='"+userName+"',email='"+email+"'}";
    }
}