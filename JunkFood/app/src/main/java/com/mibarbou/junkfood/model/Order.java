package com.mibarbou.junkfood.model;

import java.io.Serializable;

/**
 * Created by michel on 10/12/2016.
 */

public class Order implements Serializable {
    private Food mFood;
    private String mObservation;

    public Order(Food food, String observation) {
        mFood = food;
        mObservation = observation;
    }

    public Food getFood() {
        return mFood;
    }

    public void setFood(Food food) {
        mFood = food;
    }

    public String getObservation() {
        return mObservation;
    }

    public void setObservation(String observation) {
        mObservation = observation;
    }

    @Override
    public String toString() {
        return mFood.getName();
    }
}
