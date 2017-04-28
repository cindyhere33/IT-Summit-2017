package edu.utdallas.itsummit2017.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit2017.models.jsonmodels.config;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sxk159231 on 3/24/2017.
 */

public class Configuration extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("config")
    public config config;

    public String getID() {
        return ID;
    }

    public config getConfig() {
        return config;
    }

    public void setConfig(config config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "event=" + config +
                '}';
    }
}
