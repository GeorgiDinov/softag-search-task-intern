package com.georgidinov.readingstrategy;

import java.nio.file.Path;

public interface FileSearchingStrategy {

    boolean readFile(Path path, String wordToLookFor);

}//end of interface FileReadingStrategy
