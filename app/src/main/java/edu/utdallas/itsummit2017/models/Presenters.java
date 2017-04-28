package edu.utdallas.itsummit2017.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit2017.models.jsonmodels.presenter;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class Presenters extends RealmObject {
    @PrimaryKey
    public String ID = "1";

    @SerializedName("presenter")
    public RealmList<presenter> presenters = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<presenter> getPresenters() {
        return presenters;
    }

    public void setPresenters(RealmList<presenter> presenters) {
        this.presenters = presenters;
    }

    @Override
    public String toString() {
        return "Presenters{" +
                "presenters=" + presenters +
                '}';
    }
}
