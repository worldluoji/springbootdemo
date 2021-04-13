package com.example.eslearn.models;

import java.time.LocalDateTime;

public class TestRequestInfo {
    /**
    * 哪个系统
    */
    private String system;

    /**
     * 交易码|用例名称
     */
    private String transCode;
    /**
     * 流水号
     */
    private String flowId;

    /**
     * 发送请求的时间
     */
    private LocalDateTime sendTime;

    /**
     * 发送内容 
     */
    private String content;

    private String serverAddr;

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

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TestRequestInfo [content=" + content + ", flowId=" + flowId + ", sendTime=" + sendTime + ", system="
                + system + ", transCode=" + transCode + "]";
    }

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

}
