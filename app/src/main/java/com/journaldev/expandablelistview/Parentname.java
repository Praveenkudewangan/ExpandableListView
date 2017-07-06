package com.journaldev.expandablelistview;

import java.util.ArrayList;

/**
 * Created by praveendewangan on 01/07/17.
 */

public class Parentname {

    String Name;
    int serparid;
    ArrayList<Childname> childnameArrayList;

    public Parentname(String name, int serparid, ArrayList<Childname> childnameArrayList) {
        Name = name;
        this.serparid = serparid;
        this.childnameArrayList = childnameArrayList;
    }

    public String getName() {
        return Name;
    }

    public int getSerparid() {
        return serparid;
    }

    public ArrayList<Childname> getChildnameArrayList() {
        return childnameArrayList;
    }


}
