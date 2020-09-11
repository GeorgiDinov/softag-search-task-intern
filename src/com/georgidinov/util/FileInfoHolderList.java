package com.georgidinov.util;

import java.util.ArrayList;
import java.util.List;

public class FileInfoHolderList implements ObjectHolderList {

    //== fields ==
    private List<ObjectHolder> fileHolders;


    //== constructors ==
    public FileInfoHolderList() {
        this.fileHolders = new ArrayList<>();
    }//end of constructor


    //== public methods ==
    @Override
    public void addNewObjectHolder(ObjectHolder objectHolder) {
        this.fileHolders.add(objectHolder);
    }

    @Override
    public List<ObjectHolder> getObjectHolderList() {
        return this.fileHolders;
    }

}//end of class FileInfoHolderList
