package com.georgidinov.readingstrategy;

public class FileSearchingStrategyFactory {

    public FileSearchingStrategy getFileSearchingStrategy(String type) {
        if (type == null) {
            return null;
        } else if (type.endsWith(".txt")) {
            return new TextFileSearchingStrategy();
        }
        return null;
    }

}//end of class FileReaderFactory
