package edu.utdallas.itsummit2017.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit2017.models.jsonmodels.sponsorLevel;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 3/8/2016.
 */
public class SponsorLevels extends RealmObject {


    @PrimaryKey
    public String ID = "1";

    @SerializedName("sponsorLevel")
    public RealmList<sponsorLevel> sponsorLevels = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<sponsorLevel> getSponsorLevels() {
        return sponsorLevels;
    }

    @Override
    public String toString() {
        return "SponsorLevels{" +
                "ID='" + ID + '\'' +
                ", sponsorLevels=" + sponsorLevels +
                '}';
    }
}
