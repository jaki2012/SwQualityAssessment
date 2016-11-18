package com.tongji409.domain;

/**
 * 将39个软件维度划分为三大类型
 * 避免同一张数据库表表项太多
 * Created by lijiechu on 16/11/18.
 */
public class SoftwareMetrics {

    private int swMetricsIndex;

    //所属模块的编号
    private int moduleID;

    public int getSwMetricsIndex() {
        return swMetricsIndex;
    }

    public void setSwMetricsIndex(int swMetricsIndex) {
        this.swMetricsIndex = swMetricsIndex;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }
}
