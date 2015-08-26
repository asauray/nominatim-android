package com.goodway.nominatimparser;

import android.os.Parcel;

/**
 * Created by antoine on 8/22/15.
 */
public abstract class VirtualAdress extends Adress{

    public VirtualAdress(){

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
