package com.mibarbou.junkfood.model;

import java.util.LinkedList;

/**
 * Created by michel on 08/12/2016.
 */

public class Foods {

    private LinkedList<Food>mFoods;

    public Foods(LinkedList<Food> foods) {
        mFoods = foods;
    }

    public LinkedList<Food> getFoods() {
        return mFoods;
    }

    public void setFoods(LinkedList<Food> foods) {
        mFoods = foods;
    }

    public int getCount() { return mFoods.size(); }
}
