package com.mibarbou.junkfood.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by michel on 08/12/2016.
 */

public class Tables implements Serializable {
    private LinkedList<Table>mTables;

    public Tables(LinkedList<Table> tables) {
        mTables = tables;
    }

    public Tables() {
        mTables = new LinkedList<>();
        // tables to add
        mTables.add(new Table(1));
        mTables.add(new Table(2));
        mTables.add(new Table(3));
        mTables.add(new Table(4));
        mTables.add(new Table(5));
        mTables.add(new Table(6));
        mTables.add(new Table(7));
        mTables.add(new Table(8));
        mTables.add(new Table(9));
        mTables.add(new Table(10));
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public void setTables(LinkedList<Table> tables) {
        mTables = tables;
    }

    public Table getTable(int position) {
        return mTables.get(position);
    }

    public int getCount() { return mTables.size(); }
}
