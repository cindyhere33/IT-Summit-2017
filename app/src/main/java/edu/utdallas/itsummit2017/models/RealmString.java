package edu.utdallas.itsummit2017.models;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/6/2017.
 */

public class RealmString extends RealmObject {

    private String data = "";

    public RealmString(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public RealmString() {
    }


}
