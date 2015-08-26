package com.goodway.nominatimparser;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by antoine on 8/22/15.
 */
public abstract class PhysicalAdress extends Adress{

    public PhysicalAdress(double lat, double lon){
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public PhysicalAdress(Parcel in){
        super(in);
        lat = in.readDouble();
        lon = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

}
