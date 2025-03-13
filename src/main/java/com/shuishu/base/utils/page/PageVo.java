package com.shuishu.base.utils.page;


import cn.hutool.core.util.ReflectUtil;
import jakarta.validation.constraints.Min;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2025/1/15 16:40
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
public class PageVo<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -5112006617444802L;


    // 页码
    @Min(value = 1, message = "页码从1开始")
    private long pageNumber = 1;
    // 页大小
    @Min(value = 1, message = "页大小从1开始")
    private long pageSize = 5;
    // 数据总页数
    private long totalPage = 0;
    // 数据总数
    private long totalNumber = 0;
    // 数据分页，开始索引（包含数据）
    private Long startIndex;
    // 数据分页，结束索引（不包含数据）
    private Long endIndex;
    // 数据分页，开始行（包含数据）
    private Long startRow;
    // 数据分页，结束行（包含数据）
    private Long endRow;
    // 排序规则
    private PageEnums.SortRule sortRule;
    // 排序字段1
    private String sortField1;
    // 排序字段2
    private String sortField2;
    // 业务分页数据
    private List<T> dataList = new ArrayList<>();
    // 额外的业务数据
    private Map<String, Object> extraDataMap = new HashMap<>();



    @Min(value = 1, message = "页码从1开始")
    public long getPageNumber() {
        return pageNumber;
    }

    public PageVo<T> setPageNumber(@Min(value = 1, message = "页码从1开始") long pageNumber) {
        this.pageNumber = pageNumber;
        setStartRow();
        setEndRow();
        return this;
    }

    @Min(value = 1, message = "页大小从1开始")
    public long getPageSize() {
        return pageSize;
    }

    public PageVo<T> setPageSize(@Min(value = 1, message = "页大小从1开始") long pageSize) {
        this.pageSize = pageSize;
        setStartRow();
        setEndRow();
        return this;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public PageVo<T> setTotalPage(long totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public PageVo<T> setTotalNumber(long totalNumber) {
        // 设置总记录数
        this.totalNumber = totalNumber;
        // 设置总页数
        this.totalPage = (totalNumber + pageSize - 1) / pageSize;
        return this;
    }

    public Long getStartIndex() {
        if (startIndex != null) {
            return startIndex;
        }
        return (pageNumber - 1) * pageSize;
    }

    public PageVo<T> setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    public Long getEndIndex() {
        // 不能超过总记录数
        return Math.min(getStartIndex() + pageSize, totalNumber);
    }

    public PageVo<T> setEndIndex(Long endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    public Long getStartRow() {
        if (startRow == null) {
            startRow = (pageNumber - 1) * pageSize + 1;
        }
        return startRow;
    }

    public void setStartRow() {
        this.startRow = (pageNumber - 1) * pageSize + 1;
    }

    public Long getEndRow() {
        if (endRow == null) {
            endRow = pageNumber * pageSize;
        }
        return endRow;
    }

    public void setEndRow() {
        this.endRow = pageNumber * pageSize;
    }

    public PageEnums.SortRule getSortRule() {
        return sortRule;
    }

    public PageVo<T> setSortRule(PageEnums.SortRule sortRule) {
        this.sortRule = sortRule;
        return this;
    }

    public String getSortField1() {
        return sortField1;
    }

    public PageVo<T> setSortField1(String sortField1) {
        this.sortField1 = sortField1;
        return this;
    }

    public String getSortField2() {
        return sortField2;
    }

    public PageVo<T> setSortField2(String sortField2) {
        this.sortField2 = sortField2;
        return this;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public PageVo<T> setDataList(List<T> dataList) {
        this.dataList = dataList;
        return this;
    }

    public Map<String, Object> getExtraDataMap() {
        return extraDataMap;
    }

    public PageVo<T> setExtraDataMap(Map<String, Object> extraDataMap) {
        this.extraDataMap = extraDataMap;
        return this;
    }


    @Override
    public String toString() {
        return "PageVo{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalNumber=" + totalNumber +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", sortRule=" + sortRule +
                ", sortField1='" + sortField1 + '\'' +
                ", sortField2='" + sortField2 + '\'' +
                ", dataList=" + dataList +
                ", extraDataMap=" + extraDataMap +
                '}';
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public PageVo() {
    }

    public PageVo(long pageNumber, long pageSize, long totalNumber, List<T> dataList, Map<String, Object> extraDataMap) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        // 设置总记录数
        this.totalNumber = totalNumber;
        // 设置总页数
        this.totalPage = (totalNumber + pageSize - 1) / pageSize;
        this.dataList = dataList;
        this.extraDataMap = extraDataMap;
    }

    public PageVo(long pageNumber, long pageSize, long totalNumber, List<T> dataList) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        // 设置总记录数
        this.totalNumber = totalNumber;
        // 设置总页数
        this.totalPage = (totalNumber + pageSize - 1) / pageSize;
        this.dataList = dataList;
    }

    public PageVo(long pageNumber, long pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public PageVo<T> update(List<T> dataList, Map<String, Object> extraDataMap, long totalNumber) {
        this.dataList = dataList;
        this.extraDataMap = extraDataMap;
        // 设置总记录数
        this.totalNumber = totalNumber;
        // 设置总页数
        this.totalPage = (totalNumber + pageSize - 1) / pageSize;
        return this;
    }

    public PageVo<T> update(List<T> dataList, long totalNumber) {
        this.dataList = dataList;
        // 设置总记录数
        this.totalNumber = totalNumber;
        // 设置总页数
        this.totalPage = (totalNumber + pageSize - 1) / pageSize;
        return this;
    }

    public PageVo<T> update(List<T> dataList, Map<String, Object> extraDataMap) {
        this.dataList = dataList;
        this.extraDataMap = extraDataMap;
        return this;
    }

    public PageVo<T> update(List<T> dataList) {
        this.dataList = dataList;
        return this;
    }

    public PageVo<T> update(Map<String, Object> extraDataMap) {
        this.extraDataMap = extraDataMap;
        return this;
    }

    public PageVo<T> update(long totalNumber) {
        // 设置总记录数
        this.totalNumber = totalNumber;
        // 设置总页数
        this.totalPage = (totalNumber + pageSize - 1) / pageSize;
        return this;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    public static <T> PageVo<T> newPageVo(Class<T> cl, long pageNumber, long pageSize) {
        PageVo<T> emptyPage = new PageVo<>();
        emptyPage.setDataList(Collections.emptyList());
        emptyPage.setPageNumber(pageNumber);
        emptyPage.setPageSize(pageSize);
        return emptyPage;
    }

    public static <T> PageVo<T> newPageVo(List<T> dataList, Map<String, Object> extraDataMap, long pageNumber, long pageSize, long totalNumber) {
        return new PageVo<>(pageNumber, pageSize, totalNumber, dataList, extraDataMap);
    }

    public static <T> PageVo<T> newPageVo(List<T> dataList, long pageNumber, long pageSize, long totalNumber) {
        return new PageVo<>(pageNumber, pageSize, totalNumber, dataList);
    }

    // ---------------------------------------------------------------------------------------------------------------------

    /**
     * 检查当前页码是否超出范围
     */
    public boolean checkPageNumberOutOfRange() {
        // 判断页码是否超过总页数
        return pageNumber > getTotalPage();
    }

    /**
     * 检查排序字段名，是否存在Class中
     */
    public void checkSortFieldExists(Class<?> clazz) {
        if (sortField1 != null && !sortField1.isEmpty() && ReflectUtil.getField(clazz, sortField1) == null) {
            throw new NullPointerException("分页排序字段名【" + sortField1 + "】不存在");
        }
        if (sortField2 != null && !sortField2.isEmpty() && ReflectUtil.getField(clazz, sortField2) == null) {
            throw new NullPointerException("分页排序字段名【" + sortField2 + "】不存在");
        }
    }

}
