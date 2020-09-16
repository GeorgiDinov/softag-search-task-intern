package com.georgidinov.readingstrategy;

import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.fileinfo.ObjectHolderList;
import com.georgidinov.util.userinput.UserInput;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class ClassFileSearchingStrategy implements FileSearchingStrategy {


    //== public methods ==
    @Override
    public void readFile(UserInput userInput, ObjectHolderList objectHolderList, ObjectHolder objectHolder) {
        try (Scanner scanner = new Scanner(new BufferedInputStream(Files.newInputStream(userInput.getPath())))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(userInput.getStringToSearchFor())) {
                    objectHolderList.addNewObjectHolder(objectHolder);
                }
            }

        } catch (IOException e) {
            System.out.println("Exception while reading class file: " + e.getMessage());
        }
    }

}//end of class ClassFileSearchingStrategy
