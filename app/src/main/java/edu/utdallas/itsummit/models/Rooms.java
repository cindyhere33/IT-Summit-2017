package edu.utdallas.itsummit.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit.models.jsonmodels.room;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 2/9/2016.
 */
public class Rooms extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("room")
    public RealmList<room> rooms = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "ID='" + ID + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
