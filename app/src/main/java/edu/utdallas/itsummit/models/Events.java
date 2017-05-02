package edu.utdallas.itsummit.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit.models.jsonmodels.event;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class Events extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("event")
    public event event;

    public String getID() {
        return ID;
    }

    public event getEvent() {
        return event;
    }

    public void setEvent(event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event=" + event +
                '}';
    }
}
