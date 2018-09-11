package com.tongji409.website.vo;

import com.tongji409.domain.Metrics;

/**
 * @author lijiechu
 * @create on 2018/9/7
 * @description vo类
 */
public class MetricsInfo {

    private String moduleName;

    private String fileName;

    private boolean defective;

    private Metrics metrics;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isDefective() {
        return defective;
    }

    public void setDefective(boolean defective) {
        this.defective = defective;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public MetricsInfo() {}


}
