package com.shuishu.base.utils.cache.redis.dto;



import java.util.Map;
import java.util.Objects;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/27 9:55
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：缓存队列，封装数据dto
 * <br>
 */
public class CacheDataDto {
    /**
     * 延迟时间（秒）
     */
    private Long delaySeconds;
    /**
     * 消息内容（业务数据）
     */
    private String dataString;
    /**
     * 消息内容（业务数据）
     */
    private Map<String, Object> dataMap;


    public Long getDelaySeconds() {
        return delaySeconds;
    }

    public void setDelaySeconds(Long delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public CacheDataDto(Long delaySeconds, String dataString) {
        this.delaySeconds = delaySeconds;
        this.dataString = dataString;
    }

    public CacheDataDto(Long delaySeconds, Map<String, Object> dataMap) {
        this.delaySeconds = delaySeconds;
        this.dataMap = dataMap;
    }

    public CacheDataDto(Long delaySeconds, String dataString, Map<String, Object> dataMap) {
        this.delaySeconds = delaySeconds;
        this.dataString = dataString;
        this.dataMap = dataMap;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof CacheDataDto that)) {return false;}
        return Objects.equals(getDelaySeconds(), that.getDelaySeconds()) && Objects.equals(getDataString(), that.getDataString()) && Objects.equals(getDataMap(), that.getDataMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDelaySeconds(), getDataString(), getDataMap());
    }

    @Override
    public String toString() {
        return "CacheDataDto{" +
                "delaySeconds=" + delaySeconds +
                ", dataString='" + dataString + '\'' +
                ", dataMap=" + dataMap +
                '}';
    }

}
