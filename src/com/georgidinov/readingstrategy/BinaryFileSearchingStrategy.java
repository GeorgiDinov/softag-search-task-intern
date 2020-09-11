package com.georgidinov.readingstrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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


    // for the creation of a mock binary file
    public static void main(String[] args) {

        List<String> loremText = new ArrayList<>();
        loremText.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        loremText.add("Mauris lacinia, tortor non efficitur fringilla, diam justo tincidunt nibh, eu gravida arcu arcu a metus.");
        loremText.add("Morbi imperdiet libero vel tellus sollicitudin euismod.");
        loremText.add("Quisque auctor magna sit amet urna pellentesque, vitae venenatis mauris scelerisque.");
        loremText.add("Praesent ac ipsum vehicula, varius lorem id, luctus justo.");

        Path path = FileSystems
                .getDefault()
                .getPath("TestFileTree" + File.separator + "Dir2" + File.separator + "Dir3" + File.separator + "file3.dat");
        try (DataOutputStream fileWriter = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(path)))) {
            for (String s : loremText) {
                fileWriter.writeUTF(s + "\n");
            }
        } catch (IOException e) {
            System.out.println("Exception while writing binary file \\'file3.dat\\': " + e.getMessage());
        }
    }//end of main method

}//end of class BinaryFileSearchingStrategy
