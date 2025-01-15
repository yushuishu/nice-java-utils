package com.shuishu.base.utils.statistics;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 9:53
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：常见统计，时间操作
 * <br>
 * 参考：
 */
public class NiceStatisticsDate {
    /**
     * 获取最近 {yearNum} 年的年份范围
     *
     * @param yearNum 最近 {yearNum} 年
     * @return 包含每年开始和结束日期的 List<StatisticsDate>
     */
    public static List<StatisticsDate> getYearRange(int yearNum) {
        return getYearRange(yearNum, DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * 获取最近 {yearNum} 年的年份范围
     *
     * @param yearNum 最近 {yearNum} 年
     * @param format  时间格式
     * @return 包含每年开始和结束日期的 List<StatisticsDate>
     */
    public static List<StatisticsDate> getYearRange(int yearNum, String format) {
        yearNum = Math.max(yearNum, 0);
        format = format == null || format.isEmpty() ? DatePattern.NORM_DATETIME_PATTERN : format;
        List<StatisticsDate> statisticsDateList = new ArrayList<>();
        for (int i = 1; i <= yearNum; i++) {
            DateTime beginOfYear = DateUtil.beginOfYear(DateUtil.offset(DateUtil.date(), DateField.YEAR, -i));
            DateTime endOfYear = DateUtil.endOfYear(DateUtil.offset(DateUtil.date(), DateField.YEAR, -i));
            StatisticsDate statisticsDate = new StatisticsDate();
            statisticsDate.setDateScope(DateUtil.format(beginOfYear, "yyyy"));
            statisticsDate.setNormStartDateStr(DateUtil.format(beginOfYear, DatePattern.NORM_DATETIME_PATTERN));
            statisticsDate.setNormEndDateStr(DateUtil.format(endOfYear, DatePattern.NORM_DATETIME_PATTERN));
            statisticsDate.setStartDate(DateUtil.parse(DateUtil.format(beginOfYear, format), format));
            statisticsDate.setStartDateStr(DateUtil.format(beginOfYear, format));
            statisticsDate.setEndDate(DateUtil.parse(DateUtil.format(endOfYear, format), format));
            statisticsDate.setEndDateStr(DateUtil.format(endOfYear, format));
            statisticsDateList.add(statisticsDate);
        }
        return statisticsDateList.stream().sorted(Comparator.comparing(StatisticsDate::getStartDate)).collect(Collectors.toList());
    }

    /**
     * 获取最近 {monthNum} 月的月范围
     *
     * @param monthNum 最近 {monthNum} 月
     * @return 包含每月开始和结束日期的 List<StatisticsDate>
     */
    public static List<StatisticsDate> getMonthRange(int monthNum) {
        return getMonthRange(monthNum, DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * 获取最近 {monthNum} 月的月范围
     *
     * @param monthNum 最近 {monthNum} 月
     * @param format   时间格式
     * @return 包含每月开始和结束日期的 List<StatisticsDate>
     */
    public static List<StatisticsDate> getMonthRange(int monthNum, String format) {
        monthNum = Math.max(monthNum, 0);
        format = format == null || format.isEmpty() ? DatePattern.NORM_DATETIME_PATTERN : format;
        List<StatisticsDate> statisticsDateList = new ArrayList<>();
        for (int i = 0; i <= monthNum; i++) {
            DateTime beginOfMonth = DateUtil.beginOfMonth(DateUtil.offsetMonth(DateUtil.date(), -i));
            DateTime endOfMonth = DateUtil.endOfMonth(DateUtil.offsetMonth(DateUtil.date(), -i));
            StatisticsDate statisticsDate = new StatisticsDate();
            statisticsDate.setDateScope(DateUtil.format(beginOfMonth, DatePattern.NORM_MONTH_PATTERN));
            statisticsDate.setNormStartDateStr(DateUtil.format(beginOfMonth, DatePattern.NORM_DATETIME_PATTERN));
            statisticsDate.setNormEndDateStr(DateUtil.format(endOfMonth, DatePattern.NORM_DATETIME_PATTERN));
            statisticsDate.setStartDate(DateUtil.parse(DateUtil.format(beginOfMonth, format), format));
            statisticsDate.setStartDateStr(DateUtil.format(beginOfMonth, format));
            statisticsDate.setEndDate(DateUtil.parse(DateUtil.format(endOfMonth, format), format));
            statisticsDate.setEndDateStr(DateUtil.format(endOfMonth, format));
            statisticsDateList.add(statisticsDate);
        }
        return statisticsDateList.stream().sorted(Comparator.comparing(StatisticsDate::getStartDate)).collect(Collectors.toList());
    }

    /**
     * 获取最近 {dayNum} 天的日范围
     *
     * @param dayNum 最近 {dayNum} 天
     * @return 包含每天开始和结束日期的 List<StatisticsDate>
     */
    public static List<StatisticsDate> getDayRange(int dayNum) {
        return getDayRange(dayNum, DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * 获取最近 {dayNum} 天的日范围
     *
     * @param dayNum 最近 {dayNum} 天
     * @param format   时间格式
     * @return 包含每天开始和结束日期的 List<StatisticsDate>
     */
    public static List<StatisticsDate> getDayRange(int dayNum, String format) {
        dayNum = Math.max(dayNum, 0);
        format = format == null || format.isEmpty() ? DatePattern.NORM_DATETIME_PATTERN : format;
        List<StatisticsDate> statisticsDateList = new ArrayList<>();
        for (int i = 0; i <= dayNum; i++) {
            DateTime beginOfDay = DateUtil.beginOfDay(DateUtil.offsetDay(DateUtil.date(), -i));
            DateTime endOfDay = DateUtil.endOfDay(DateUtil.offsetDay(DateUtil.date(), -i));
            StatisticsDate statisticsDate = new StatisticsDate();
            statisticsDate.setDateScope(DateUtil.format(beginOfDay, DatePattern.NORM_DATE_PATTERN));
            statisticsDate.setNormStartDateStr(DateUtil.format(beginOfDay, DatePattern.NORM_DATETIME_PATTERN));
            statisticsDate.setNormEndDateStr(DateUtil.format(endOfDay, DatePattern.NORM_DATETIME_PATTERN));
            statisticsDate.setStartDate(DateUtil.parse(DateUtil.format(beginOfDay, format), format));
            statisticsDate.setStartDateStr(DateUtil.format(beginOfDay, format));
            statisticsDate.setEndDate(DateUtil.parse(DateUtil.format(endOfDay, format), format));
            statisticsDate.setEndDateStr(DateUtil.format(endOfDay, format));
            statisticsDateList.add(statisticsDate);
        }
        return statisticsDateList.stream().sorted(Comparator.comparing(StatisticsDate::getStartDate)).collect(Collectors.toList());
    }

    /**
     * 获取最近 {weekNum} 周的周范围
     *
     * @param weekNum 最近 {weekNum} 周
     * @return 包含每周开始和结束日期的 List<StatisticsDate>
     */
    public static List<StatisticsDate> getWeekRange(int weekNum) {
        return getWeekRange(weekNum, DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * 获取最近 {weekNum} 周的周范围
     *
     * @param weekNum 最近 {weekNum} 周
     * @param format   时间格式
     * @return 包含每周开始和结束日期的 List<StatisticsDate>
     */
    public static List<StatisticsDate> getWeekRange(int weekNum, String format) {
        weekNum = Math.max(weekNum, 0);
        format = format == null || format.isEmpty() ? DatePattern.NORM_DATETIME_PATTERN : format;
        List<StatisticsDate> statisticsDateList = new ArrayList<>();
        for (int i = 0; i <= weekNum; i++) {
            DateTime beginOfWeek = DateUtil.beginOfWeek(DateUtil.offsetWeek(DateUtil.date(), -i));
            DateTime endOfWeek = DateUtil.endOfWeek(DateUtil.offsetWeek(DateUtil.date(), -i));
            StatisticsDate statisticsDate = new StatisticsDate();
            statisticsDate.setNormStartDateStr(DateUtil.format(beginOfWeek, DatePattern.NORM_DATETIME_PATTERN));
            statisticsDate.setNormEndDateStr(DateUtil.format(endOfWeek, DatePattern.NORM_DATETIME_PATTERN));
            statisticsDate.setStartDate(DateUtil.parse(DateUtil.format(beginOfWeek, format), format));
            statisticsDate.setStartDateStr(DateUtil.format(beginOfWeek, format));
            statisticsDate.setEndDate(DateUtil.parse(DateUtil.format(endOfWeek, format), format));
            statisticsDate.setEndDateStr(DateUtil.format(endOfWeek, format));
            statisticsDateList.add(statisticsDate);
        }
        return statisticsDateList.stream().sorted(Comparator.comparing(StatisticsDate::getStartDate)).collect(Collectors.toList());
    }

}
