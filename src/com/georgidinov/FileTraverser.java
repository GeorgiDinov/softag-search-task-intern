package com.georgidinov;

import com.georgidinov.readingstrategy.FileSearchingStrategy;
import com.georgidinov.readingstrategy.FileSearchingStrategyFactory;
import com.georgidinov.util.fileinfo.FileInfoHolder;
import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.fileinfo.ObjectHolderList;
import com.georgidinov.util.userinput.UserInput;
import com.georgidinov.util.userinput.UserInputImpl;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;

public class FileTraverser extends SimpleFileVisitor<Path> {

    //== comparators ==
    Comparator<ObjectHolder> onFileSize = Comparator.comparing(ObjectHolder::getFileSize);

    //== fields ==
    private UserInput userInput;
    private ObjectHolderList objectHolderList;

    //== constructors ==
    public FileTraverser(ObjectHolderList objectHolderList, UserInput userInput) {
        this.objectHolderList = objectHolderList;
        this.userInput = userInput;
    }//end of constructor


    //== public methods ==
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing file: " + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }//end of method visitFileFailed

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

//        System.out.println("Path = " + file.resolve(userInput.getPath()));
//        System.out.println("not resolved = " + file.toString());
        String fileName = file.getFileName().toString();
        long fileSize = attrs.size();
        ObjectHolder objectHolder = new FileInfoHolder(fileName, fileSize);

        FileSearchingStrategy readingStrategy = FileSearchingStrategyFactory.getFileSearchingStrategy(fileName);

        if (readingStrategy != null) {
            readingStrategy.readFile(
                    new UserInputImpl(file.toAbsolutePath().toString(), userInput.getStringToSearchFor()),
                    this.objectHolderList,
                    objectHolder
            );
        } else {
            System.out.println("File Format Not Supported!");
        }

        return FileVisitResult.CONTINUE;
    }//end of method visitFile

    public List<ObjectHolder> listAllFilesWithMatches() {
        List<ObjectHolder> foundFiles = this.objectHolderList.getObjectHolderList();
        foundFiles.sort(onFileSize);
        return foundFiles;
    }//end of method listAllFilesWithMatches


}//end fo class FileTraverser
