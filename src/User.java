public class User {
    private int userId;
    private String logName;
    private String password;
    private String userName;
    private String email;
    private boolean isAdmin;
    public User(int userId,String logName,String password,String userName,String email){
        this.email=email;
        this.logName=logName;
        this.password=password;
        this.userId=userId;
        this.userName=userName;
    }
    public int getUserId(){
        return userId;
    }
    public String getLoginName(){
        return logName;
    }
    public String getLoginPassword(){
        return password;
    }
    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    public boolean isAdmin(){
        return isAdmin;
    }
    public void setAdmin(){
        this.isAdmin=true;
    }
    public String toString(){
        return "User{userId='"+userId+"',logName='"+logName+"'," +
                "password='"+password+"',userName='"+userName+"',email='"+email+"'}";
    }
}
