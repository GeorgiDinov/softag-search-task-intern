package com.georgidinov.util.userinput;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UserInputImpl implements UserInput {

    //== fields ==
    private String pathString;
    private String stringToSearchFor;

    //== constructors ==
    public UserInputImpl(String pathString, String stringToSearchFor) {
        this.pathString = pathString;
        this.stringToSearchFor = stringToSearchFor;
    }//end of constructor


    //== public methods ==
    @Override
    public Path getPath() {
        return Paths.get(this.pathString);
    }

    @Override
    public String getStringToSearchFor() {
        return this.stringToSearchFor;
    }

    @Override
    public String getPathString() {
        return this.pathString;
    }

}//end of class UserInput
