package com.mibarbou.junkfood.model;

import java.io.Serializable;

/**
 * Created by michel on 08/12/2016.
 */

public class Food implements Serializable{
    private String mName;
    private int icon;
    private String mAllergens;
    private float mPrice;
    private int mId;

    public Food(String name, int photoURL, String allergens, float price, int id) {
        mName = name;
        icon = photoURL;
        mPrice = price;
        mAllergens = allergens;
        mId = id;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public String getAllergens() {
        return mAllergens;
    }

    public void setAllergens(String allergens) {
        mAllergens = allergens;
    }

    public String getName() {
        return mName;
    }

    public int getIcon() {
        return icon;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Override
    public String toString() {
        return getName();
    }
}
