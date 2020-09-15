package com.georgidinov.readingstrategy;

public class FileSearchingStrategyFactory {

    //== public methods ==
    public static FileSearchingStrategy getFileSearchingStrategy(String fileName) {
        if (fileName == null) {
            return null;
        } else if (fileName.endsWith(".txt") || fileName.endsWith(".pdf") || fileName.endsWith(".html")) {
            return new TextFileSearchingStrategy();
        } else if (fileName.endsWith(".docx")) {
            return new DocxFileSearchingStrategy();
        } else if (fileName.endsWith(".doc")) {
            return new DocFileSearchingStrategy();
        } else if (fileName.endsWith(".dat") || fileName.endsWith(".class")) {
            return new BinaryFileSearchingStrategy();
        } else if (fileName.endsWith(".zip") || fileName.endsWith(".jar")) {
            return new ZipFileSearchingStrategy();
        }
        return null;
    }

}//end of class FileSearchingStrategyFactory
