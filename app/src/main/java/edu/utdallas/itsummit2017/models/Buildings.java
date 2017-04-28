package edu.utdallas.itsummit2017.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit2017.models.jsonmodels.building;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 2/9/2016.
 */
public class Buildings extends RealmObject {
    @PrimaryKey
    public String ID = "1";

    @SerializedName("event")
    public building buildings;

    public String getID() {
        return ID;
    }

    public building getBuildings() {
        return buildings;
    }

    public void setBuildings(building buildings) {
        this.buildings = buildings;
    }

    @Override
    public String toString() {
        return "Buildings{" +
                "event=" + buildings +
                '}';
    }
}
