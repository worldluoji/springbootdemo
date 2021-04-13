package com.example.eslearn.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "testresult")
public class TestResult {

    /**
    * 哪个系统
    */
    @Field(type = FieldType.Text)
    private String system;

    /**
     * 交易码|用例名称
     */
    @Field(type = FieldType.Text)
    private String transCode;
    /**
     * 流水号
     */
    @Id
    private String flowId;


    /**
     * 发送时间
     */
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private LocalDateTime sendTime;

    /**
     * 收到返回参数的时间
     */
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private LocalDateTime receivedTime;

    /**
    * 自动化测试用例返回的结果
    */
    @Field(type = FieldType.Text)
    private String content;

    /**
    * 用例是否执行成功
    */
    @Field(type = FieldType.Boolean)
    private boolean success;

    

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public LocalDateTime getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(LocalDateTime receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "TestResult [content=" + content + ", flowId=" + flowId + ", receivedTime=" + receivedTime + ", success="
                + success + ", system=" + system + ", transCode=" + transCode + "]";
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }
    
}
