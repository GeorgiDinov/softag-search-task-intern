package com.georgidinov.util.userinput;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UserInput {

    //== fields ==
    private String pathString;
    private String stringToLookFor;

    //== constructors ==
    public UserInput(String pathString, String stringToLookFor) {
        this.pathString = pathString;
        this.stringToLookFor = stringToLookFor;
    }//end of constructor


    //== public methods ==
    public String getPathString() {
        return pathString;
    }

    public String getStringToLookFor() {
        return stringToLookFor;
    }

    public Path getPath() {
        return Paths.get(pathString);
    }

}//end of class UserInput
