package com.mibarbou.junkfood.model;

import java.util.HashMap;

/**
 * Created by michel on 08/12/2016.
 */

public class Table {
    private Integer mId;
    private HashMap<Food,Integer> mOrder;

    public Table(Integer id, HashMap<Food, Integer> order) {
        mId = id;
        mOrder = order;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public HashMap<Food, Integer> getOrder() {
        return mOrder;
    }

    public void setOrder(HashMap<Food, Integer> order) {
        mOrder = order;
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}
