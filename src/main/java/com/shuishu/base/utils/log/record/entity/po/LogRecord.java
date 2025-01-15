package com.shuishu.base.utils.log.record.entity.po;


import java.util.Date;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2024/9/13 9:07
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <br>
 * @description ：
 * <br>
 */
public class LogRecord {

    /**
     * 日志记录主键id
     */
    private Long logRecordId;

    /**
     * 接口名称，列如：用户-添加
     */
    private String apiName;

    /**
     * 接口描述，列如：用户注册账号
     */
    private String apiDescription;

    /**
     * 接口方法名称，例如：userAdd
     */
    private String apiMethodName;

    /**
     * 接口url
     */
    private String apiRequestUrl;

    /**
     * 请求方式：GET / POST，列如：GET
     */
    private String apiRequestMethod;

    /**
     * 请求时间，格式 yyyy-MM-dd HH:mm:ss
     */
    private Date apiRequestTime;

    /**
     * 响应时间，格式yyyy-MM-dd HH:mm:ss
     */
    private Date apiResponseTime;

    /**
     * 请求数据
     */
    private String apiRequestData;

    /**
     * 响应数据
     */
    private String apiResponseData;

    /**
     * 处理状态，SUCCESS / FAIL，列如：SUCCESS
     */
    private String apiProcessStatus;

    /**
     * 接口请求，管理员id
     */
    private Long apiRequestAdminId;

    /**
     * 接口请求，普通用户id
     */
    private Long apiRequestUserId;

    /**
     * 客户端地址
     */
    private String apiRemoteAddress;

    

    public Long getLogRecordId() {
        return logRecordId;
    }

    public LogRecord setLogRecordId(Long logRecordId) {
        this.logRecordId = logRecordId;
        return this;
    }

    public String getApiName() {
        return apiName;
    }

    public LogRecord setApiName(String apiName) {
        this.apiName = apiName;
        return this;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public LogRecord setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
        return this;
    }

    public String getApiMethodName() {
        return apiMethodName;
    }

    public LogRecord setApiMethodName(String apiMethodName) {
        this.apiMethodName = apiMethodName;
        return this;
    }

    public String getApiRequestUrl() {
        return apiRequestUrl;
    }

    public LogRecord setApiRequestUrl(String apiRequestUrl) {
        this.apiRequestUrl = apiRequestUrl;
        return this;
    }

    public String getApiRequestMethod() {
        return apiRequestMethod;
    }

    public LogRecord setApiRequestMethod(String apiRequestMethod) {
        this.apiRequestMethod = apiRequestMethod;
        return this;
    }

    public Date getApiRequestTime() {
        return apiRequestTime;
    }

    public LogRecord setApiRequestTime(Date apiRequestTime) {
        this.apiRequestTime = apiRequestTime;
        return this;
    }

    public Date getApiResponseTime() {
        return apiResponseTime;
    }

    public LogRecord setApiResponseTime(Date apiResponseTime) {
        this.apiResponseTime = apiResponseTime;
        return this;
    }

    public String getApiRequestData() {
        return apiRequestData;
    }

    public LogRecord setApiRequestData(String apiRequestData) {
        this.apiRequestData = apiRequestData;
        return this;
    }

    public String getApiResponseData() {
        return apiResponseData;
    }

    public LogRecord setApiResponseData(String apiResponseData) {
        this.apiResponseData = apiResponseData;
        return this;
    }

    public String getApiProcessStatus() {
        return apiProcessStatus;
    }

    public LogRecord setApiProcessStatus(String apiProcessStatus) {
        this.apiProcessStatus = apiProcessStatus;
        return this;
    }

    public Long getApiRequestAdminId() {
        return apiRequestAdminId;
    }

    public LogRecord setApiRequestAdminId(Long apiRequestAdminId) {
        this.apiRequestAdminId = apiRequestAdminId;
        return this;
    }

    public Long getApiRequestUserId() {
        return apiRequestUserId;
    }

    public LogRecord setApiRequestUserId(Long apiRequestUserId) {
        this.apiRequestUserId = apiRequestUserId;
        return this;
    }

    public String getApiRemoteAddress() {
        return apiRemoteAddress;
    }

    public LogRecord setApiRemoteAddress(String apiRemoteAddress) {
        this.apiRemoteAddress = apiRemoteAddress;
        return this;
    }


    public LogRecord() {
    }

    public LogRecord(Long logRecordId, String apiName, String apiDescription, String apiMethodName, String apiRequestUrl, String apiRequestMethod,
                     Date apiRequestTime, Date apiResponseTime, String apiRequestData, String apiResponseData, String apiProcessStatus,
                     Long apiRequestAdminId, Long apiRequestUserId, String apiRemoteAddress) {
        this.logRecordId = logRecordId;
        this.apiName = apiName;
        this.apiDescription = apiDescription;
        this.apiMethodName = apiMethodName;
        this.apiRequestUrl = apiRequestUrl;
        this.apiRequestMethod = apiRequestMethod;
        this.apiRequestTime = apiRequestTime;
        this.apiResponseTime = apiResponseTime;
        this.apiRequestData = apiRequestData;
        this.apiResponseData = apiResponseData;
        this.apiProcessStatus = apiProcessStatus;
        this.apiRequestAdminId = apiRequestAdminId;
        this.apiRequestUserId = apiRequestUserId;
        this.apiRemoteAddress = apiRemoteAddress;
    }


}
