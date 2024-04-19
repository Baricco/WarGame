package com.assets.gameAssets.basics;

public class City {
    
    String name;
    boolean trainStation; 
    String stateId;
    int latitude, longitude;

    public City(String name, boolean trainStation, String stateId, int latitude, int longitude) {
        this.name = name;
        this.trainStation = trainStation;
        this.stateId = stateId;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public int getLatitude() {
        return this.latitude;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasTrainStation() {
        return this.trainStation;
    }

    public String getStateId() {
        return this.stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    

    @Override
    public String toString() {
        return "{" +
            " name: '" + getName() + "'" +
            ", trainStation: '" + hasTrainStation() + "'" +
            ", stateId: '" + getStateId() + "'" +
            ", latitude: '" + getLatitude() + "'" +
            ", longitude: '" + getLongitude() + "'" +
            "}";
    }

}
