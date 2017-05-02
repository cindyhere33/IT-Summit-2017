package edu.utdallas.itsummit.models.jsonmodels;

import android.support.annotation.NonNull;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class sponsorLevel extends RealmObject implements Comparable<sponsorLevel> {

    public String ID = "";
    public String Level = "";
    public String Order = "";
    public String Added = "";

    public String getID() {
        return ID;
    }

    public String getLevel() {
        return Level;
    }

    public String getOrder() {
        return Order;
    }

    public String getAdded() {
        return Added;
    }

    @Override
    public String toString() {
        return "sponsorLevel{" +
                "ID='" + ID + '\'' +
                ", Level='" + Level + '\'' +
                ", Order='" + Order + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull sponsorLevel sponsorLevel) {
        if (Integer.parseInt(this.getOrder()) > Integer.parseInt(sponsorLevel.getOrder()))
            return 1;
        else if (Integer.parseInt(this.getOrder()) < Integer.parseInt(sponsorLevel.getOrder()))
            return -1;
        return 0;
    }
}
