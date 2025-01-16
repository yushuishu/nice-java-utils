package com.shuishu.base.utils.page;


import cn.hutool.core.util.ReflectUtil;
import jakarta.validation.constraints.Min;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2025/1/15 16:39
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
public class PageDto {

    @Min(value = 1, message = "页码从1开始")
    private long pageNumber = 1;
    @Min(value = 1, message = "页大小从1开始")
    private long pageSize = 5;
    private PageEnums.SortRule sortRule;
    private String sortField1;
    private String sortField2;


    @Min(value = 1, message = "页码从1开始")
    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(@Min(value = 1, message = "页码从1开始") long pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Min(value = 1, message = "页大小从1开始")
    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(@Min(value = 1, message = "页大小从1开始") long pageSize) {
        this.pageSize = pageSize;
    }

    public PageEnums.SortRule getSortRule() {
        return sortRule;
    }

    public void setSortRule(PageEnums.SortRule sortRule) {
        this.sortRule = sortRule;
    }

    public String getSortField1() {
        return sortField1;
    }

    public void setSortField1(String sortField1) {
        this.sortField1 = sortField1;
    }

    public String getSortField2() {
        return sortField2;
    }

    public void setSortField2(String sortField2) {
        this.sortField2 = sortField2;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", sortRule=" + sortRule +
                ", sortField1='" + sortField1 + '\'' +
                ", sortField2='" + sortField2 + '\'' +
                '}';
    }

    public PageDto() {
    }

    public PageDto(long pageNumber, long pageSize, PageEnums.SortRule sortRule, String sortField1, String sortField2) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortRule = sortRule;
        this.sortField1 = sortField1;
        this.sortField2 = sortField2;
    }

    public PageDto(long pageNumber, long pageSize, PageEnums.SortRule sortRule, String sortField1) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortRule = sortRule;
        this.sortField1 = sortField1;
    }

    public PageDto(long pageNumber, long pageSize, PageEnums.SortRule sortRule) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortRule = sortRule;
    }

    public PageDto(long pageNumber, long pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
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

    /**
     * 装换 PageVo<T>类
     */
    public <T> PageVo<T> toPageVo(Class<T> cl) {
        return new PageVo<T>(pageNumber, pageSize);
    }


}
