package edu.utdallas.itsummit2017.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit2017.models.jsonmodels.support;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 3/15/2016.
 */
public class SupportItems extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("support")
    public support support;

    public String getID() {
        return ID;
    }

    public support getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return "SupportItems{" +
                "ID='" + ID + '\'' +
                ", support=" + support +
                '}';
    }
}
