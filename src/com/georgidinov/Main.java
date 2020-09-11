package com.georgidinov;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        Path path = FileSystems.getDefault().getPath("TestFileTree");
        String word = "elit";

        try {
            Files.walkFileTree(path, new FileTraverser(word));
        } catch (IOException e) {
            System.out.println("Walk the file tree exception: " + e.getMessage());
        }

    }//end of main method

}//end of class Main
