package com.tongji409.domain;

/**
 * Created by lijiechu on 16/11/26.
 */
public class StaticDefect {

    private int staticDefectID;

    private int staticDefectInfoID;

    private int moduleID;

    private String location;

    private String description;

    public int getStaticDefectID() {
        return staticDefectID;
    }

    public void setStaticDefectID(int staticDefectID) {
        this.staticDefectID = staticDefectID;
    }

    public int getStaticDefectInfoID() {
        return staticDefectInfoID;
    }

    public void setStaticDefectInfoID(int staticDefectInfoID) {
        this.staticDefectInfoID = staticDefectInfoID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
