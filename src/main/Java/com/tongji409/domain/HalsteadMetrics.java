package com.tongji409.domain;

/**
 * Created by lijiechu on 16/11/18.
 */
public class HalsteadMetrics extends SoftwareMetrics {

    private int BRANCH_COUNT;

    private int CALL_PAIRS;

    private int PARAMETER_COUNT;

    private float HALSTEAD_CONTENT;

    private float HALSTEAD_DIFFICULTY;

    private float HALSTEAD_EFFORT;

    private float HALSTEAD_EFFORT_EST;

    private int HALSETAD_LENGTH;

    private float HALSTEAD_LEVEL;

    private float HALSTEAD_PROG_TIME;

    private float HASTEAD_VOLUME;

    //运算元的个数
    private int NUM_OPERANDS;

    //运算符的个数
    private int NUM_OPERATORS;

    //不重复运算元的个数
    private int NUM_UNIQUE_OPERANDS;

    //不重复运算符的个数
    private int NUM_UNIQUE_OPERATORS;

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

    public int getHALSETAD_LENGTH() {
        return HALSETAD_LENGTH;
    }

    public void setHALSETAD_LENGTH(int HALSETAD_LENGTH) {
        this.HALSETAD_LENGTH = HALSETAD_LENGTH;
    }

    public float getHALSTEAD_LEVEL() {
        return HALSTEAD_LEVEL;
    }

    public void setHALSTEAD_LEVEL(float HALSTEAD_LEVEL) {
        this.HALSTEAD_LEVEL = HALSTEAD_LEVEL;
    }

    public float getHALSTEAD_PROG_TIME() {
        return HALSTEAD_PROG_TIME;
    }

    public void setHALSTEAD_PROG_TIME(float HALSTEAD_PROG_TIME) {
        this.HALSTEAD_PROG_TIME = HALSTEAD_PROG_TIME;
    }

    public float getHASTEAD_VOLUME() {
        return HASTEAD_VOLUME;
    }

    public void setHASTEAD_VOLUME(float HASTEAD_VOLUME) {
        this.HASTEAD_VOLUME = HASTEAD_VOLUME;
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
