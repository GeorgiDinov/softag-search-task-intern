package com.georgidinov.readingstrategy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileSearchingStrategy implements FileSearchingStrategy {

    //== public methods ==
    @Override
    public boolean readFile(Path path, String stringToLookFor) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(Files.newInputStream(path)))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String fileName = entry.getName();
                long fileSize = entry.getSize();
                System.out.println("File name in zip file = " + fileName + " size = " + fileSize);
                FileSearchingStrategy reader = FileSearchingStrategyFactory.getFileSearchingStrategy(fileName);
                if (reader != null) {
                    return reader.readFile(path, stringToLookFor);
                } else {
                    System.out.println("Not supported file format!");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception while reading the zip file content: " + e.getMessage());
        }
        return false;
    }


}//end of class ZipFileSearchingStrategy
