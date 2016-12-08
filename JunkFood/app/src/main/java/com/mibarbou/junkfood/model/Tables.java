package com.mibarbou.junkfood.model;

import java.util.LinkedList;

/**
 * Created by michel on 08/12/2016.
 */

public class Tables {
    private LinkedList<Table>mTables;

    public Tables(LinkedList<Table> tables) {
        mTables = tables;
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public void setTables(LinkedList<Table> tables) {
        mTables = tables;
    }

    public int getCount() { return mTables.size(); }
}
