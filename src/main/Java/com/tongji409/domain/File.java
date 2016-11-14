package com.tongji409.domain;

/**
 * Created by lijiechu on 16/11/14.
 */
public class File {

    private int fileID;

    //所属作业的ID
    private int taskID;

    private String fileName;

    private String fileType;

    private String fileCoding;

    private String filePath;

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileCoding() {
        return fileCoding;
    }

    public void setFileCoding(String fileCoding) {
        this.fileCoding = fileCoding;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
