package com.goodway.nominatimparser;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by antoine on 8/22/15.
 */
public abstract class Adress implements Parcelable {

    protected double lat, lon;

    public Adress(){

    }

    public Adress(Parcel in){

    }

    public abstract String getName(Context c);

    public double getLongitude(){
        return lat;
    }
    public double getLatitude(){
        return lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lon);
    }

}
