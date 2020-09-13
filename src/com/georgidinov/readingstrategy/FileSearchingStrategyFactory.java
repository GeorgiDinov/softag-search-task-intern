package com.georgidinov.readingstrategy;

public class FileSearchingStrategyFactory {

    public static FileSearchingStrategy getFileSearchingStrategy(String fileName) {
        if (fileName == null) {
            return null;
        } else if (fileName.endsWith(".txt") || fileName.endsWith(".pdf") ||
                fileName.endsWith(".docx") || fileName.endsWith(".html")) {
            return new TextFileSearchingStrategy();
        } else if (fileName.endsWith(".dat")) {
            return new BinaryFileSearchingStrategy();
        } else if (fileName.endsWith(".zip") || fileName.endsWith(".jar") || fileName.endsWith(".rar")) {
            return new ZipFileSearchingStrategy();
        }
        return null;
    }

}//end of class FileSearchingStrategyFactory
