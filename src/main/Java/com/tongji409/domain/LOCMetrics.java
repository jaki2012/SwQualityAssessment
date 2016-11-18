package com.tongji409.domain;

/**
 * LOC类型软件度量
 * Created by lijiechu on 16/11/18.
 */
public class LOCMetrics extends SoftwareMetrics{

    private int LOC_BLANK;

    private int LOC_CODE_AND_COMMENT;

    private int LOC_COMMENTS;

    private int LOC_EXECUTABLE;

    private int NUMBER_OF_LINES;

    //注释比例
    private float PERCENT_COMMENTS;

    private int LOC_TOTAL;

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
}
