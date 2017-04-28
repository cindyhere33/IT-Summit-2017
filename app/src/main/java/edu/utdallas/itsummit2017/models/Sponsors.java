package edu.utdallas.itsummit2017.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit2017.models.jsonmodels.sponsor;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 2/9/2016.
 */
public class Sponsors extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("sponsor")
    public RealmList<sponsor> sponsors = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<sponsor> getSponsors() {
        return sponsors;
    }

    @Override
    public String toString() {
        return "Sponsors{" +
                "ID='" + ID + '\'' +
                ", sponsors=" + sponsors +
                '}';
    }
}
