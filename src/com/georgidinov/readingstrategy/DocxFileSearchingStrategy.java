package com.georgidinov.readingstrategy;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocxFileSearchingStrategy implements FileSearchingStrategy {

    @Override
    public boolean readFile(Path path, String stringToLookFor) {
        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(path))) {
            XWPFDocument document = new XWPFDocument(inputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            String text = extractor.getText();
            System.out.println(text);
            return text.contains(stringToLookFor);
        } catch (IOException e) {
            System.out.println("Exception while reading from docx file: " + e.getMessage());
            return false;
        }
    }

}//end of class WordFileSearchingStrategy
