package com.tongji409.domain;

/**
 * @author lijiechu
 * @create on 16/12/29
 * @description
 */
public class Metrics {

    private int id;

    private int moduleID;

    private int LOC_BLANK;

    private int LOC_CODE_AND_COMMENT;

    private int LOC_COMMENTS;

    private int LOC_EXECUTABLE;

    private int NUMBER_OF_LINES;

    //注释比例
    private float PERCENT_COMMENTS;

    private int LOC_TOTAL;

    private int CONDITION_COUNT;

    private int HALSTEAD_ERROR_SET;

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

    private int BRANCH_COUNT;

    private int CALL_PAIRS;

    private int PARAMETER_COUNT;

    private float HALSTEAD_CONTENT;

    private float HALSTEAD_DIFFICULTY;

    private float HALSTEAD_EFFORT;

    private float HALSTEAD_EFFORT_EST;

    private float HALSTEAD_LEVEL;

    private float HALSTEAD_VOLUME;

    private int HALSTEAD_LENGTH;

    //运算元的个数
    private int NUM_OPERANDS;

    //运算符的个数
    private int NUM_OPERATORS;

    //不重复运算元的个数
    private int NUM_UNIQUE_OPERANDS;

    //不重复运算符的个数
    private int NUM_UNIQUE_OPERATORS;

    private float HALSTEAD_PROGRAM_TIME;

    public float getHALSTEAD_PROGRAM_TIME() {
        return HALSTEAD_PROGRAM_TIME;
    }

    public void setHALSTEAD_PROGRAM_TIME(float HALSTEAD_PROGRAM_TIME) {
        this.HALSTEAD_PROGRAM_TIME = HALSTEAD_PROGRAM_TIME;
    }

    public int getHALSTEAD_LENGTH() {
        return HALSTEAD_LENGTH;
    }

    public void setHALSTEAD_LENGTH(int HALSTEAD_LENGTH) {
        this.HALSTEAD_LENGTH = HALSTEAD_LENGTH;
    }

    public int getHALSTEAD_ERROR_SET() {
        return HALSTEAD_ERROR_SET;
    }

    public void setHALSTEAD_ERROR_SET(int HALSTEAD_ERROR_SET) {
        this.HALSTEAD_ERROR_SET = HALSTEAD_ERROR_SET;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getLOC_BLANK() {
        return LOC_BLANK;
    }

    public void setLOC_BLANK(int LOC_BLANK) {
        this.LOC_BLANK = LOC_BLANK;
    }

    public int getLOC_CODE_AND_COMMENT() {
        return LOC_CODE_AND_COMMENT;
    }

    public void setLOC_CODE_AND_COMMENT(int LOC_CODE_AND_COMMENT) {
        this.LOC_CODE_AND_COMMENT = LOC_CODE_AND_COMMENT;
    }

    public int getLOC_COMMENTS() {
        return LOC_COMMENTS;
    }

    public void setLOC_COMMENTS(int LOC_COMMENTS) {
        this.LOC_COMMENTS = LOC_COMMENTS;
    }

    public int getLOC_EXECUTABLE() {
        return LOC_EXECUTABLE;
    }

    public void setLOC_EXECUTABLE(int LOC_EXECUTABLE) {
        this.LOC_EXECUTABLE = LOC_EXECUTABLE;
    }

    public int getNUMBER_OF_LINES() {
        return NUMBER_OF_LINES;
    }

    public void setNUMBER_OF_LINES(int NUMBER_OF_LINES) {
        this.NUMBER_OF_LINES = NUMBER_OF_LINES;
    }

    public float getPERCENT_COMMENTS() {
        return PERCENT_COMMENTS;
    }

    public void setPERCENT_COMMENTS(float PERCENT_COMMENTS) {
        this.PERCENT_COMMENTS = PERCENT_COMMENTS;
    }

    public int getLOC_TOTAL() {
        return LOC_TOTAL;
    }

    public void setLOC_TOTAL(int LOC_TOTAL) {
        this.LOC_TOTAL = LOC_TOTAL;
    }

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

    public void setNORMALIZED_CYCLOMATIC_COMPLEXITY(float NORMALIZED_CYCLOMATIC_COMPLEXITY) {
        this.NORMALIZED_CYCLOMATIC_COMPLEXITY = NORMALIZED_CYCLOMATIC_COMPLEXITY;
    }

    public int getBRANCH_COUNT() {
        return BRANCH_COUNT;
    }

    public void setBRANCH_COUNT(int BRANCH_COUNT) {
        this.BRANCH_COUNT = BRANCH_COUNT;
    }

    public int getCALL_PAIRS() {
        return CALL_PAIRS;
    }

    public void setCALL_PAIRS(int CALL_PAIRS) {
        this.CALL_PAIRS = CALL_PAIRS;
    }

    public int getPARAMETER_COUNT() {
        return PARAMETER_COUNT;
    }

    public void setPARAMETER_COUNT(int PARAMETER_COUNT) {
        this.PARAMETER_COUNT = PARAMETER_COUNT;
    }

    public float getHALSTEAD_CONTENT() {
        return HALSTEAD_CONTENT;
    }

    public void setHALSTEAD_CONTENT(float HALSTEAD_CONTENT) {
        this.HALSTEAD_CONTENT = HALSTEAD_CONTENT;
    }

    public float getHALSTEAD_DIFFICULTY() {
        return HALSTEAD_DIFFICULTY;
    }

    public void setHALSTEAD_DIFFICULTY(float HALSTEAD_DIFFICULTY) {
        this.HALSTEAD_DIFFICULTY = HALSTEAD_DIFFICULTY;
    }

    public float getHALSTEAD_EFFORT() {
        return HALSTEAD_EFFORT;
    }

    public void setHALSTEAD_EFFORT(float HALSTEAD_EFFORT) {
        this.HALSTEAD_EFFORT = HALSTEAD_EFFORT;
    }

    public float getHALSTEAD_EFFORT_EST() {
        return HALSTEAD_EFFORT_EST;
    }

    public void setHALSTEAD_EFFORT_EST(float HALSTEAD_EFFORT_EST) {
        this.HALSTEAD_EFFORT_EST = HALSTEAD_EFFORT_EST;
    }

    public float getHALSTEAD_LEVEL() {
        return HALSTEAD_LEVEL;
    }

    public void setHALSTEAD_LEVEL(float HALSTEAD_LEVEL) {
        this.HALSTEAD_LEVEL = HALSTEAD_LEVEL;
    }

    public float getHALSTEAD_VOLUME() {
        return HALSTEAD_VOLUME;
    }

    public void setHALSTEAD_VOLUME(float HASTEAD_VOLUME) {
        this.HALSTEAD_VOLUME = HASTEAD_VOLUME;
    }

    public int getNUM_OPERANDS() {
        return NUM_OPERANDS;
    }

    public void setNUM_OPERANDS(int NUM_OPERANDS) {
        this.NUM_OPERANDS = NUM_OPERANDS;
    }

    public int getNUM_OPERATORS() {
        return NUM_OPERATORS;
    }

    public void setNUM_OPERATORS(int NUM_OPERATORS) {
        this.NUM_OPERATORS = NUM_OPERATORS;
    }

    public int getNUM_UNIQUE_OPERANDS() {
        return NUM_UNIQUE_OPERANDS;
    }

    public void setNUM_UNIQUE_OPERANDS(int NUM_UNIQUE_OPERANDS) {
        this.NUM_UNIQUE_OPERANDS = NUM_UNIQUE_OPERANDS;
    }

    public int getNUM_UNIQUE_OPERATORS() {
        return NUM_UNIQUE_OPERATORS;
    }

    public void setNUM_UNIQUE_OPERATORS(int NUM_UNIQUE_OPERATORS) {
        this.NUM_UNIQUE_OPERATORS = NUM_UNIQUE_OPERATORS;
    }
}
