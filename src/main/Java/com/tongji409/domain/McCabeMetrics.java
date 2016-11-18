package com.tongji409.domain;

/**
 * Created by lijiechu on 16/11/18.
 */
public class McCabeMetrics extends SoftwareMetrics{

    private int CONDITION_COUNT;

    private int CYCLOMATIC_COMPLEXITY;

    private float CYCLOMATIC_DENSITY;

    private int DECISION_COUNT;

    private float DECISION_DENSITY;

    private int DESIGN_COMPLEXITY;

    private float DESIGN_DENSITY;

    private int EDGE_COUNT;

    private int ESSENTIAL_COMPLEXITY;

    private float ESSENTIAL_DENSITY;

    private int GLOBAL_DATA_COMPLEXITY;

    private float GLOBAL_DATA_DENSITY;

    private float MAINTENANCE_SEVERITY;

    private int MODIFIED_CONDITION_COUNT;

    private int MULTIPLE_CONDITION_COUNT;

    private int NODE_COUNT;

    private float NORMALIZED_CYCLOMATIC_COMPLEXITY;

    public int getCONDITION_COUNT() {
        return CONDITION_COUNT;
    }

    public void setCONDITION_COUNT(int CONDITION_COUNT) {
        this.CONDITION_COUNT = CONDITION_COUNT;
    }

    public int getCYCLOMATIC_COMPLEXITY() {
        return CYCLOMATIC_COMPLEXITY;
    }

    public void setCYCLOMATIC_COMPLEXITY(int CYCLOMATIC_COMPLEXITY) {
        this.CYCLOMATIC_COMPLEXITY = CYCLOMATIC_COMPLEXITY;
    }

    public float getCYCLOMATIC_DENSITY() {
        return CYCLOMATIC_DENSITY;
    }

    public void setCYCLOMATIC_DENSITY(float CYCLOMATIC_DENSITY) {
        this.CYCLOMATIC_DENSITY = CYCLOMATIC_DENSITY;
    }

    public int getDECISION_COUNT() {
        return DECISION_COUNT;
    }

    public void setDECISION_COUNT(int DECISION_COUNT) {
        this.DECISION_COUNT = DECISION_COUNT;
    }

    public float getDECISION_DENSITY() {
        return DECISION_DENSITY;
    }

    public void setDECISION_DENSITY(float DECISION_DENSITY) {
        this.DECISION_DENSITY = DECISION_DENSITY;
    }

    public int getDESIGN_COMPLEXITY() {
        return DESIGN_COMPLEXITY;
    }

    public void setDESIGN_COMPLEXITY(int DESIGN_COMPLEXITY) {
        this.DESIGN_COMPLEXITY = DESIGN_COMPLEXITY;
    }

    public float getDESIGN_DENSITY() {
        return DESIGN_DENSITY;
    }

    public void setDESIGN_DENSITY(float DESIGN_DENSITY) {
        this.DESIGN_DENSITY = DESIGN_DENSITY;
    }

    public int getEDGE_COUNT() {
        return EDGE_COUNT;
    }

    public void setEDGE_COUNT(int EDGE_COUNT) {
        this.EDGE_COUNT = EDGE_COUNT;
    }

    public int getESSENTIAL_COMPLEXITY() {
        return ESSENTIAL_COMPLEXITY;
    }

    public void setESSENTIAL_COMPLEXITY(int ESSENTIAL_COMPLEXITY) {
        this.ESSENTIAL_COMPLEXITY = ESSENTIAL_COMPLEXITY;
    }

    public float getESSENTIAL_DENSITY() {
        return ESSENTIAL_DENSITY;
    }

    public void setESSENTIAL_DENSITY(float ESSENTIAL_DENSITY) {
        this.ESSENTIAL_DENSITY = ESSENTIAL_DENSITY;
    }

    public int getGLOBAL_DATA_COMPLEXITY() {
        return GLOBAL_DATA_COMPLEXITY;
    }

    public void setGLOBAL_DATA_COMPLEXITY(int GLOBAL_DATA_COMPLEXITY) {
        this.GLOBAL_DATA_COMPLEXITY = GLOBAL_DATA_COMPLEXITY;
    }

    public float getGLOBAL_DATA_DENSITY() {
        return GLOBAL_DATA_DENSITY;
    }

    public void setGLOBAL_DATA_DENSITY(float GLOBAL_DATA_DENSITY) {
        this.GLOBAL_DATA_DENSITY = GLOBAL_DATA_DENSITY;
    }

    public float getMAINTENANCE_SEVERITY() {
        return MAINTENANCE_SEVERITY;
    }

    public void setMAINTENANCE_SEVERITY(float MAINTENANCE_SEVERITY) {
        this.MAINTENANCE_SEVERITY = MAINTENANCE_SEVERITY;
    }

    public int getMODIFIED_CONDITION_COUNT() {
        return MODIFIED_CONDITION_COUNT;
    }

    public void setMODIFIED_CONDITION_COUNT(int MODIFIED_CONDITION_COUNT) {
        this.MODIFIED_CONDITION_COUNT = MODIFIED_CONDITION_COUNT;
    }

    public int getMULTIPLE_CONDITION_COUNT() {
        return MULTIPLE_CONDITION_COUNT;
    }

    public void setMULTIPLE_CONDITION_COUNT(int MULTIPLE_CONDITION_COUNT) {
        this.MULTIPLE_CONDITION_COUNT = MULTIPLE_CONDITION_COUNT;
    }

    public int getNODE_COUNT() {
        return NODE_COUNT;
    }

    public void setNODE_COUNT(int NODE_COUNT) {
        this.NODE_COUNT = NODE_COUNT;
    }

    public float getNORMALIZED_CYCLOMATIC_COMPLEXITY() {
        return NORMALIZED_CYCLOMATIC_COMPLEXITY;
    }

    public void setNORMALIZED_CYCLOMATIC_COMPLEXITY(float NORMALIZED_CYLOMATIC_COMPLEXITY) {
        this.NORMALIZED_CYCLOMATIC_COMPLEXITY = NORMALIZED_CYLOMATIC_COMPLEXITY;
    }
}
