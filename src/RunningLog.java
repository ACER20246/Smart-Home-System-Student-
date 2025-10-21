import java.util.Date;

public class RunningLog {
    private Date dateTime;
    private String event;
    private Type type;
    private String note;
    public enum Type {
        INFO,  // 信息级
        WARN,  // 警告级
        ERROR  // 错误级
    }
    public RunningLog(Date dateTime,String event,Type type,String note){
        this.dateTime=dateTime;
        this.type=type;
        this.note =note;
        this.event=event;
    }

    public String getEvent() {
        return event;
    }

    public String getNote() {
        return note;
    }

    public Type getType() {
        return type;
    }

    public Date getDateTime() {
        return dateTime;
    }

}
