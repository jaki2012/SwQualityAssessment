package com.tongji409.domain;

/**
 * Created by lijiechu on 16/11/14.
 */
public class Module {

    private int moduleID;

    //所属文件的id
    private int fileID;

    private String moduleName;

    //机器学习预测缺陷的结果
    private boolean defective;

    //备用项
    private String spareItem;

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isDefective() {
        return defective;
    }

    public void setDefective(boolean defective) {
        this.defective = defective;
    }

    public String getSpareItem() {
        return spareItem;
    }

    public void setSpareItem(String spareItem) {
        this.spareItem = spareItem;
    }

}
