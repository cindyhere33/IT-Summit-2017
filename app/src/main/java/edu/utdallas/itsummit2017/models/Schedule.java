package edu.utdallas.itsummit2017.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit2017.models.jsonmodels.session;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 3/27/2017.
 */

public class Schedule extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("session")
    public RealmList<session> sessions = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<session> getSessions() {
        return sessions;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "session=" + sessions +
                '}';
    }
}
