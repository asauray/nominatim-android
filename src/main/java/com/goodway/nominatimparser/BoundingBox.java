package com.goodway.nominatimparser;

import android.location.Location;

/**
 * Created by antoine on 8/19/15.
 */
public class BoundingBox {

    private double[] boundingbox;

    public BoundingBox(){
        this.boundingbox = new double[4];
    }
    public double[] getBoundingBox(){return boundingbox;}

    public void setBound(int i, double l){
        boundingbox[i]= l;
    }
}
