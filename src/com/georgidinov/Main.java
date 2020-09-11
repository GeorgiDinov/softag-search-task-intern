package com.georgidinov;

import com.georgidinov.util.FileInfoHolderList;
import com.georgidinov.util.ObjectHolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static String SEPARATOR = File.separator;

    public static void main(String[] args) {


        Path path = FileSystems.getDefault().getPath("TestFileTree");
        Path myPathOnPC = Paths.get("C:" + SEPARATOR + "Users" + SEPARATOR + "usr" + SEPARATOR + "Desktop" + SEPARATOR + "TestFileTree");
        String stringToSearchFor = "Lorem";

        FileTraverser fileTraverser = new FileTraverser(new FileInfoHolderList(), stringToSearchFor);

        try {
            Files.walkFileTree(myPathOnPC, fileTraverser);
        } catch (IOException e) {
            System.out.println("Walk the file tree exception: " + e.getMessage());
        }

        List<ObjectHolder> files = fileTraverser.listAllFilesWithMatches();
        for (ObjectHolder objectHolder : files) {
            System.out.println(objectHolder);
        }

    }//end of main method

}//end of class Main
