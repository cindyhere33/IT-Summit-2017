package edu.utdallas.itsummit2017.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit2017.models.jsonmodels.exhibitor;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 3/25/2016.
 */
public class Exhibitors extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("exhibitor")
    public RealmList<exhibitor> exhibitors = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<exhibitor> getExhibitors() {
        return exhibitors;
    }

    @Override
    public String toString() {
        return "Exhibitors{" +
                "ID='" + ID + '\'' +
                ", exhibitors=" + exhibitors +
                '}';
    }
}
