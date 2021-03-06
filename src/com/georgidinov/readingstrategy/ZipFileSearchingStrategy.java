package com.georgidinov.readingstrategy;

import com.georgidinov.util.FileManipulator;
import com.georgidinov.util.fileinfo.FileInfoHolder;
import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.fileinfo.ObjectHolderList;
import com.georgidinov.util.userinput.UserInput;
import com.georgidinov.util.userinput.UserInputImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class ZipFileSearchingStrategy implements FileSearchingStrategy {

    //== constants ==
    private static final String IN_ZIP = "InZip/";
    private static final String TEMP_FOLDER = "Temp" + File.separator;


    //== public methods ==
    @Override
    public void readFile(UserInput userInput, ObjectHolderList objectHolderList, ObjectHolder objectHolder) {

        String searchFor = userInput.getStringToSearchFor();
        Path tempFilePath = this.unzip(userInput);

        if (Files.exists(tempFilePath)) {
            try {
                Files.walkFileTree(tempFilePath, new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        String fileName = file.getFileName().toString();
                        long fileSize = attrs.size();
                        ObjectHolder holder = new FileInfoHolder(IN_ZIP + fileName, fileSize);
                        FileSearchingStrategy strategy = FileSearchingStrategyFactory.getFileSearchingStrategy(fileName);
                        if (strategy != null) {
                            strategy.readFile(new UserInputImpl(file.toString(), searchFor), objectHolderList, holder);
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });

            } catch (IOException e) {
                System.out.println("Exception while reading the extracted content: " + e.getMessage());
            }
        }
        FileManipulator.deleteFile(tempFilePath);
    }//end of method readFile


    //== private methods ==
    private Path unzip(UserInput userInput) {


        Path tempFilePath = FileSystems.getDefault().getPath(TEMP_FOLDER);
        //FileManipulator.deleteFile(tempFilePath);

        try (ZipFile zipFile = new ZipFile(userInput.getPathString())) {
            Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
            zipEntries.asIterator().forEachRemaining(entry -> {
                try {
                    if (entry.isDirectory()) {
                        Path dirToCreate = tempFilePath.resolve(entry.getName());
                        Files.createDirectories(dirToCreate);
                    } else {
                        Path fileToCreate = tempFilePath.resolve(entry.getName());
                        Files.copy(zipFile.getInputStream(entry), fileToCreate);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFilePath;
    }

}//end of class ZipFileSearchingStrategy
