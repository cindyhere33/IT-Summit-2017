package edu.utdallas.itsummit.models;

import com.google.gson.annotations.SerializedName;

import edu.utdallas.itsummit.models.jsonmodels.news;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by sxk159231 on 2/9/2016.
 */
public class NewsItems extends RealmObject {

    @PrimaryKey
    public String ID = "1";

    @SerializedName("news")
    public RealmList<news> news = new RealmList<>();

    public String getID() {
        return ID;
    }

    public RealmList<edu.utdallas.itsummit.models.jsonmodels.news> getNews() {
        return news;
    }

    @Override
    public String toString() {
        return "NewsItems{" +
                "ID='" + ID + '\'' +
                ", news=" + news +
                '}';
    }
}
