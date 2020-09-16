package com.georgidinov.readingstrategy;

import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.fileinfo.ObjectHolderList;
import com.georgidinov.util.userinput.UserInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class TextFileSearchingStrategy implements FileSearchingStrategy {


    @Override
    public void readFile(UserInput userInput, ObjectHolderList objectHolderList, ObjectHolder objectHolder) {
        try (Scanner fileReader = new Scanner(new BufferedReader(Files.newBufferedReader(userInput.getPath())))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.contains(userInput.getStringToSearchFor())) {
                    objectHolderList.addNewObjectHolder(objectHolder);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception while reading text file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}//end of class TextFileReadingStrategy
