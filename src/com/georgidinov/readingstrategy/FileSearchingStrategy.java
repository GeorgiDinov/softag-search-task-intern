package com.georgidinov.readingstrategy;

import com.georgidinov.util.fileinfo.ObjectHolder;
import com.georgidinov.util.fileinfo.ObjectHolderList;
import com.georgidinov.util.userinput.UserInput;

public interface FileSearchingStrategy {

    void readFile(UserInput userInput, ObjectHolderList objectHolderList, ObjectHolder objectHolder);

}//end of interface FileReadingStrategy
