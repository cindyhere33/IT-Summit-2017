package edu.utdallas.itsummit.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit.models.jsonmodels.more;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 11/16/2016.
 */

public class MoreItems extends RealmObject {

    @PrimaryKey
    public String ID = "l";

    @SerializedName("more")
    public RealmList<more> mores = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<more> getMores() {
        return mores;
    }

    @Override
    public String toString() {
        return "MoreItems{" +
                "ID='" + ID + '\'' +
                ", mores=" + mores +
                '}';
    }
}
