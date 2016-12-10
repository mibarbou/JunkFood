package com.mibarbou.junkfood.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by michel on 08/12/2016.
 */

public class Table implements Serializable{
    private Integer mId;
    private String mName;
    private LinkedList<Order> mOrders;

    public Table(Integer id, LinkedList<Order> order) {
        mId = id;
        mName = String.format("MESA %d", id);
        mOrders = order;
    }

    public Table(Integer id) {
        mId = id;
        mName = String.format("MESA %d", id);
        mOrders = new LinkedList<Order>();
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public LinkedList<Order> getOrders() {
        return mOrders;
    }

    @Override
    public String toString() {
        return getName();
    }
}
