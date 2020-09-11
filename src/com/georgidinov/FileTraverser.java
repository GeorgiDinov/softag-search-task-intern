package com.georgidinov;

import com.georgidinov.readingstrategy.FileSearchingStrategy;
import com.georgidinov.readingstrategy.FileSearchingStrategyFactory;
import com.georgidinov.util.FileInfoHolder;
import com.georgidinov.util.ObjectHolder;
import com.georgidinov.util.ObjectHolderList;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class FileTraverser extends SimpleFileVisitor<Path> {

    //== comparators ==
    Comparator<ObjectHolder> onFileSize = Comparator.comparing(ObjectHolder::getFileSize);

    //== fields ==
    private String wordToSearchFor;
    private ObjectHolderList objectHolderList;

    //== constructors ==
    public FileTraverser(ObjectHolderList objectHolderList, String wordToSearchFor) {
        this.objectHolderList = objectHolderList;
        this.wordToSearchFor = Objects.requireNonNull(wordToSearchFor, "defaultWord");
    }//end of constructor


    //== public methods ==
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing file: " + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }//end of method visitFileFailed

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileSearchingStrategyFactory fileReaderFactory = new FileSearchingStrategyFactory();
        FileSearchingStrategy reader = fileReaderFactory.getFileSearchingStrategy(file.getFileName().toString());
        if (reader.readFile(file, this.wordToSearchFor)) {
//            System.out.println("Successfully found keyword in " + file.getFileName() + " with size " + attrs.size());
            this.objectHolderList.addNewObjectHolder(new FileInfoHolder(file.getFileName().toString(), attrs.size()));
        }
        return FileVisitResult.CONTINUE;
    }//end of method visitFile

    public List<ObjectHolder> listAllFilesWithMatches() {
        List<ObjectHolder> foundFiles = this.objectHolderList.getObjectHolderList();
        foundFiles.sort(onFileSize);
        return foundFiles;
    }


}//end fo class FileTraverser
