package com.goodway.nominatimparser;

/**
 * @author Antoine Sauray
 * @version 0.5
 * Licensed under the Apache2 license
 */
public class Place {
    private long place_id, osm_id;
    private double lat, lon;
    private float importance;
    private String license, osm_type, display_name, entityClass, type;
    private BoundingBox boundingBox;

    public Place(long place_id, long osm_id, double lat, double lon, float importance, String license, String osm_type, String display_name, String entityClass, String type, BoundingBox boundingBox){
        this.place_id = place_id;
        this.osm_id = osm_id;
        this.lat = lat;
        this.lon = lon;
        this.importance = importance;
        this.license = license;
        this.osm_type = osm_type;
        this.display_name = display_name;
        this.entityClass = entityClass;
        this.type = type;
        this.boundingBox = boundingBox;
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

    @Override
    public String toString(){
        return display_name;
    }

}
