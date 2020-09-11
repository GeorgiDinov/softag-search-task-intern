package com.georgidinov.util;

public class FileInfoHolder implements ObjectHolder {


    //== fields ==
    private String fileName;
    private long fileSize;


    //== constructors ==
    public FileInfoHolder(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }//end pf constructor


    //== public methods ==
    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public long getFileSize() {
        return this.fileSize;
    }

    @Override
    public String toString() {
        return "File Name: " + this.fileName + " Size = " + this.fileSize;
    }

}//end of class FileInfoHolder
