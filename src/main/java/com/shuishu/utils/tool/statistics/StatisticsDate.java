package com.shuishu.utils.tool.statistics;


import java.util.Date;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/19 8:27
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：统计时间实体
 * <p></p>
 */
public class StatisticsDate {
    /**
     * 时间范围，格式：年：yyyy  月：yyyy-MM  日：yyyy-MM-dd
     * 示例值月：2024-03
     */
    private String dateScope;

    /**
     * 开始时间：常用标准格式 yyyy-MM-dd HH:mm:ss
     * 示例值：2024-03-01 00:00:00
     */
    private String normStartDateStr;

    /**
     * 结束时间：常用标准格式 yyyy-MM-dd HH:mm:ss
     * 示例值：2024-03-31 23:59:59
     */
    private String normEndDateStr;

    /**
     * 开始时间：具体业务的时间格式
     * 示例值：2024-03-01
     */
    private Date startDate;
    private String startDateStr;

    /**
     * 结束时间：具体业务的时间格式
     * 示例值：2024-03-31
     */
    private Date endDate;
    private String endDateStr;


    public String getDateScope() {
        return dateScope;
    }

    public void setDateScope(String dateScope) {
        this.dateScope = dateScope;
    }

    public String getNormStartDateStr() {
        return normStartDateStr;
    }

    public void setNormStartDateStr(String normStartDateStr) {
        this.normStartDateStr = normStartDateStr;
    }

    public String getNormEndDateStr() {
        return normEndDateStr;
    }

    public void setNormEndDateStr(String normEndDateStr) {
        this.normEndDateStr = normEndDateStr;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    @Override
    public String toString() {
        return "StatisticsDate{" +
                "dateScope='" + dateScope + '\'' +
                ", normStartDateStr='" + normStartDateStr + '\'' +
                ", normEndDateStr='" + normEndDateStr + '\'' +
                ", startDate=" + startDate +
                ", startDateStr='" + startDateStr + '\'' +
                ", endDate=" + endDate +
                ", endDateStr='" + endDateStr + '\'' +
                '}';
    }

}
