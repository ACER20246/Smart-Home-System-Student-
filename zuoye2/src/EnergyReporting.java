import java.util.Date;

/**
 * 能源报告接口
 * 定义设备能源监控和报告功能的标准接口
 * 
 * @author 开发者
 * @version 1.0
 * @since 2024-01-01
 */
public interface EnergyReporting {
    /**
     * 获取设备当前功率
     * 
     * @return 功率值（瓦特）
     */
    public double getPower();
    
    /**
     * 获取指定时间段的能耗报告
     * 
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 能耗值（千瓦时）
     */
    public double getReport(Date startTime, Date endTime);
}