package com.mibarbou.junkfood.model;

import java.io.Serializable;

/**
 * Created by michel on 08/12/2016.
 */

public class Food implements Serializable{
    private String mName;
    private String mPhotoURL;
    private Integer mId;

    public Food(String name, String photoURL) {
        mName = name;
        mPhotoURL = photoURL;
    }

    public String getName() {
        return mName;
    }

    public String getPhotoURL() {
        return mPhotoURL;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPhotoURL(String photoURL) {
        mPhotoURL = photoURL;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    @Override
    public String toString() {
        return getName();
    }
}
