package com.georgidinov;

import com.georgidinov.util.fileinfo.FileInfoHolderList;
import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.userinput.UserInput;
import com.georgidinov.util.userinput.UserInputReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ApplicationMain {


    //== public methods ==
    public static void main(String[] args) {

        UserInput userInput = getCommandLineInput(args);
        if (userInput == null) {
            userInput = new UserInputReader().getUserInput();
        }
        Path usrInputPath = userInput.getPath();
        String usrStringToSearchFor = userInput.getStringToLookFor();


        FileTraverser fileTraverser = new FileTraverser(new FileInfoHolderList(), usrStringToSearchFor);

        if (Files.exists(usrInputPath)) {
            try {
                Files.walkFileTree(usrInputPath, fileTraverser);
            } catch (IOException e) {
                System.out.println("Walk the file tree exception: " + e.getMessage());
            }
        } else {
            System.out.println("Path not found!");
        }

        List<ObjectHolder> files = fileTraverser.listAllFilesWithMatches();
        files.forEach(System.out::println);

    }//end of main method


    //== private methods ==
    private static UserInput getCommandLineInput(String[] args) {
        if (args != null && args.length >= 2) {
            return new UserInput(args[0], args[1]);
        }
        return null;
    }

}//end of class ApplicationMain
