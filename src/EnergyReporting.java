import java.util.Date;

public interface EnergyReporting {
    public  double getPower();
    public double getReport(Date startTime, Date endTime);
}
