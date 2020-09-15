package com.georgidinov;

import com.georgidinov.util.fileinfo.FileInfoHolderList;
import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.userinput.UserInputReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String SEPARATOR = File.separator;
    private static UserInputReader userInputReader = new UserInputReader();

    public static void main(String[] args) {

//        UserInput userInput = userInputReader.getUserInput();
//        Path usrInputPath = userInput.getPath();
//        String usrStringToSearchFor = userInput.getStringToLookFor();


        Path path = FileSystems.getDefault().getPath("TestFileTree");
        Path myPathOnPC = Paths.get("C:" + SEPARATOR + "Users" + SEPARATOR + "usr" + SEPARATOR + "Desktop" + SEPARATOR + "TestFileTree");
        String stringToSearchFor = "Lorem";


        FileTraverser fileTraverser = new FileTraverser(new FileInfoHolderList(), stringToSearchFor);

        if (Files.exists(myPathOnPC)) {
            try {
                Files.walkFileTree(myPathOnPC, fileTraverser);
            } catch (IOException e) {
                System.out.println("Walk the file tree exception: " + e.getMessage());
            }
        } else {
            System.out.println("Path not found!");
        }

        List<ObjectHolder> files = fileTraverser.listAllFilesWithMatches();
        files.forEach(System.out::println);

    }//end of main method

}//end of class Main
