package com.georgidinov.readingstrategy;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocFileSearchingStrategy implements FileSearchingStrategy {

    //== public methods ==
    @Override
    public boolean readFile(Path path, String stringToLookFor) {
        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(path))) {
            HWPFDocument document = new HWPFDocument(inputStream);
            WordExtractor extractor = new WordExtractor(document);
            String text = extractor.getText();
            //System.out.println(text);
            return text.contains(stringToLookFor);
        } catch (IOException e) {
            System.out.println("Exception while reading from doc file: " + e.getMessage());
            return false;
        }
    }

}//end of class DocFileSearchingStrategy
