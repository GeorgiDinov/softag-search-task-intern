package com.georgidinov.readingstrategy;

import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.fileinfo.ObjectHolderList;
import com.georgidinov.util.userinput.UserInput;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class DocFileSearchingStrategy implements FileSearchingStrategy {

    //== public methods ==
    @Override
    public void readFile(UserInput userInput, ObjectHolderList objectHolderList, ObjectHolder objectHolder) {
        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(userInput.getPath()))) {
            HWPFDocument document = new HWPFDocument(inputStream);
            WordExtractor extractor = new WordExtractor(document);
            String text = extractor.getText();
            if (text.contains(userInput.getStringToSearchFor())) {
                objectHolderList.addNewObjectHolder(objectHolder);
            }
        } catch (IOException e) {
            System.out.println("Exception while reading from doc file: " + e.getMessage());
        }
    }

}//end of class DocFileSearchingStrategy
