package edu.utdallas.itsummit.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit.models.jsonmodels.map;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 2/9/2016.
 */
public class Maps extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("map")
    public RealmList<map> maps = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<map> getMaps() {
        return maps;
    }

    @Override
    public String toString() {
        return "Maps{" +
                "ID='" + ID + '\'' +
                ", maps=" + maps +
                '}';
    }
}
