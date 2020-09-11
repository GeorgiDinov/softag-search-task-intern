package com.georgidinov.util;

import java.util.ArrayList;
import java.util.List;

public class FileInfoHolderList implements ObjectHolderList {

    //== fields ==
    private List<ObjectHolder> objectHolderList;


    //== constructors ==
    public FileInfoHolderList() {
        this.objectHolderList = new ArrayList<>();
    }//end of constructor


    //== public methods ==
    @Override
    public void addNewObjectHolder(ObjectHolder objectHolder) {
        this.objectHolderList.add(objectHolder);
    }

    @Override
    public List<ObjectHolder> getObjectHolderList() {
        return this.objectHolderList;
    }

}//end of class FileInfoHolderList
