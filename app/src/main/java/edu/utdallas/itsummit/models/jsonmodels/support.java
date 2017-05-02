package edu.utdallas.itsummit.models.jsonmodels;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class support extends RealmObject {

    public String ID = "";
    public String Description = "";
    public String Added = "";

    public String getID() {
        return ID;
    }

    public String getDescription() {
        return Description;
    }

    public String getAdded() {
        return Added;
    }

    @Override
    public String toString() {
        return "support{" +
                "ID='" + ID + '\'' +
                ", Description='" + Description + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }
}
