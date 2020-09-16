package com.georgidinov.readingstrategy;

import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.fileinfo.ObjectHolderList;
import com.georgidinov.util.userinput.UserInput;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;

public class BinaryFileSearchingStrategy implements FileSearchingStrategy {

    //== public methods ==
    @Override
    public void readFile(UserInput userInput, ObjectHolderList objectHolderList, ObjectHolder objectHolder) {
        try (DataInputStream fileReader = new DataInputStream(new BufferedInputStream(Files.newInputStream(userInput.getPath())))) {
            boolean endOfFile = false;
            while (!endOfFile) {
                try {
                    String line = fileReader.readUTF();
                    if (line.contains(userInput.getStringToSearchFor())) {
                        objectHolderList.addNewObjectHolder(objectHolder);
                        break;
                    }
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception while reading binary file: " + e.getMessage());
        }
    }

}//end of class BinaryFileSearchingStrategy
