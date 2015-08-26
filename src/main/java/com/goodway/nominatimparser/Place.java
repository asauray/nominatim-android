package com.goodway.nominatimparser;

import android.content.Context;
import android.os.Parcel;

/**
 * @author Antoine Sauray
 * @version 0.5
 * Licensed under the Apache2 license
 */
public class Place extends PhysicalAdress{
    private long place_id, osm_id;
    private float importance;
    private String license, osm_type, display_name, entityClass, type;
    private BoundingBox boundingBox;

    public Place(long place_id, long osm_id, double lat, double lon, float importance, String license, String osm_type, String display_name, String entityClass, String type, BoundingBox boundingBox){
        super(lat, lon);
        this.place_id = place_id;
        this.osm_id = osm_id;
        this.importance = importance;
        this.license = license;
        this.osm_type = osm_type;
        this.display_name = display_name;
        this.entityClass = entityClass;
        this.type = type;
        this.boundingBox = boundingBox;
    }

    public Place(Parcel in){
        super(in);
    }

    @Override
    public String getName(Context c) {
        String[] split = display_name.split(",");
        return split[0]+","+split[1];
    }

    public long getPlaceId(){return place_id;}
    public long getOsmId(){return osm_id;}
    public double getLongitude(){return lon;}
    public double getLatitude(){return lat;}
    public float getImportance(){return importance;}

    public String getLicense(){return license;}
    public String getOsmType(){return osm_type;}
    public String getDisplayName(){return display_name;}
    public String getEntityClass(){return entityClass;}
    public String getType(){return type;}

    public String getName(){
        return display_name;
    }

    @Override
    public String toString(){
        return display_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(place_id);
        dest.writeLong(osm_id);
        dest.writeFloat(importance);
        dest.writeString(license);
        dest.writeString(osm_type);
        dest.writeString(display_name);
        dest.writeString(entityClass);
        dest.writeString(license);
        dest.writeString(type);
    }
}
