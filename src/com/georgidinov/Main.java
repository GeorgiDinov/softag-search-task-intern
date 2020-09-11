package com.georgidinov;

import com.georgidinov.util.FileInfoHolderList;
import com.georgidinov.util.ObjectHolder;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Path path = FileSystems.getDefault().getPath("TestFileTree");
        String wordToSearchFor = "elit";

        FileTraverser fileTraverser = new FileTraverser(new FileInfoHolderList(), wordToSearchFor);

        try {
            Files.walkFileTree(path, fileTraverser);
        } catch (IOException e) {
            System.out.println("Walk the file tree exception: " + e.getMessage());
        }

        List<ObjectHolder> files = fileTraverser.listAllFilesWithMatches();
        for (ObjectHolder objectHolder : files) {
            System.out.println(objectHolder);
        }

    }//end of main method

}//end of class Main
