package com.georgidinov;

import com.georgidinov.readingstrategy.FileSearchingStrategy;
import com.georgidinov.readingstrategy.FileSearchingStrategyFactory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

public class FileTraverser extends SimpleFileVisitor<Path> {

    //== fields ==
    private String word;

    //== constructors ==
    public FileTraverser() {
    }

    public FileTraverser(String word) {
        this.word = Objects.requireNonNull(word, "defaultWord");
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing file: " + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }//end of method visitFileFailed

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileSearchingStrategyFactory fileReaderFactory = new FileSearchingStrategyFactory();
        FileSearchingStrategy reader = fileReaderFactory.getFileSearchingStrategy(file.getFileName().toString());
        if (reader.readFile(file, this.word)) {
            System.out.println("Successfully found keyword in " + file.getFileName() + " with size " + attrs.size());
        }

        return FileVisitResult.CONTINUE;
    }//end of method visitFile

}//end fo class FileTraverser
