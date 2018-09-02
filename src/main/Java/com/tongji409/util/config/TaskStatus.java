package com.tongji409.util.config;

/**
 * @author lijiechu
 * @create on 2018/9/2
 * @description
 */
public enum TaskStatus {

    ENQUEUING (2, "排队中"),
    FINISHED(1, "已完成"),
    ANALYZING(3, "分析中"),
    FAILED(4, "已失败");

    private int state;
    private String description;

    TaskStatus(int state, String description) {
        this.state = state;
        this.description = description;
    }

    public void setFailedDescription(String failedDescription) {
        if(this.state != FAILED.state) {
            return;
        }
        this.description = failedDescription;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
