package com.georgidinov.readingstrategy;

public class FileSearchingStrategyFactory {

    public static FileSearchingStrategy getFileSearchingStrategy(String fileName) {
        if (fileName == null) {
            return null;
        } else if (fileName.endsWith(".txt") || fileName.endsWith(".pdf")|| fileName.endsWith(".docx")) {
            return new TextFileSearchingStrategy();
        } else if (fileName.endsWith(".dat")) {
            return new BinaryFileSearchingStrategy();
        }
        return null;
    }

}//end of class FileReaderFactory
