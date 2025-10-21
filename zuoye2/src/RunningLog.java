import java.util.Date;

/**
 * 运行日志类
 * 记录设备运行过程中的事件和状态信息
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public class RunningLog {
    /** 日志时间 */
    private Date dateTime;
    
    /** 事件描述 */
    private String event;
    
    /** 日志类型 */
    private Type type;
    
    /** 备注信息 */
    private String note;
    
    /**
     * 日志类型枚举
     */
    public enum Type {
        /** 信息级日志 */
        INFO,
        /** 警告级日志 */
        WARN,
        /** 错误级日志 */
        ERROR
    }
    
    /**
     * 构造函数，创建运行日志实例
     * 
     * @param dateTime 日志时间
     * @param event 事件描述
     * @param type 日志类型
     * @param note 备注信息
     */
    public RunningLog(Date dateTime,String event,Type type,String note){
        this.dateTime=dateTime;
        this.type=type;
        this.note =note;
        this.event=event;
    }

    /**
     * 获取事件描述
     * 
     * @return 事件描述
     */
    public String getEvent() {
        return event;
    }

    /**
     * 获取备注信息
     * 
     * @return 备注信息
     */
    public String getNote() {
        return note;
    }

    /**
     * 获取日志类型
     * 
     * @return 日志类型
     */
    public Type getType() {
        return type;
    }

    /**
     * 获取日志时间
     * 
     * @return 日志时间
     */
    public Date getDateTime() {
        return dateTime;
    }
}