package com.georgidinov.readingstrategy;

import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.fileinfo.ObjectHolderList;
import com.georgidinov.util.userinput.UserInput;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class DocxFileSearchingStrategy implements FileSearchingStrategy {

    //== public methods ==
    @Override
    public void readFile(UserInput userInput, ObjectHolderList objectHolderList, ObjectHolder objectHolder) {
        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(userInput.getPath()))) {
            XWPFDocument document = new XWPFDocument(inputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            String text = extractor.getText();
            if (text.contains(userInput.getStringToSearchFor())) {
                objectHolderList.addNewObjectHolder(objectHolder);
            }
        } catch (IOException | POIXMLException e) {
            System.out.println("Exception while reading from docx file: " + e.getMessage());
        }
    }

}//end of class WordFileSearchingStrategy
