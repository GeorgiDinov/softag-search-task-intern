package com.georgidinov.readingstrategy;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinaryFileSearchingStrategy implements FileSearchingStrategy {

    //== public methods ==
    @Override
    public boolean readFile(Path path, String stringToLookFor) {
        try (DataInputStream fileReader = new DataInputStream(new BufferedInputStream(Files.newInputStream(path)))) {
            boolean endOfFile = false;
            while (!endOfFile) {
                try {
                    String line = fileReader.readUTF();
                    if (line.contains(stringToLookFor)) {
                        return true;
                    }
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception while reading binary file: " + e.getMessage());
            return false;
        }
        return false;
    }//end of class readFile

}//end of class BinaryFileSearchingStrategy
