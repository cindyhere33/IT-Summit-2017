package edu.utdallas.itsummit2017.models.jsonmodels;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class building extends RealmObject {

    public String Name = "";
    public String Description = "";
    public String ID = "";
    public String Floors = "";

    public String getAdded() {
        return Added;
    }

    public String Added = "";


    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getID() {
        return ID;
    }

    public String getFloors() {
        return Floors;
    }

    @Override
    public String toString() {
        return "building{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", ID='" + ID + '\'' +
                ", Floors='" + Floors + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }
}
