package com.georgidinov.readingstrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class TextFileSearchingStrategy implements FileSearchingStrategy {


    @Override
    public boolean readFile(Path path, String wordToLookFor) {
        try (Scanner fileReader = new Scanner(new BufferedReader(Files.newBufferedReader(path)))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.contains(wordToLookFor)) {
                    System.out.println("The file contains the word " + wordToLookFor);
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception while reading file: " + e.getMessage());
            return false;
        }
        return false;
    }


}//end of class TextFileReadingStrategy
