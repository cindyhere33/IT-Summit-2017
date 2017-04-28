package edu.utdallas.itsummit2017.models.jsonmodels;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class map extends RealmObject implements Parcelable {

    public String Map_Image_URL = "";
    public String SubTitle = "";
    public String ID = "";
    public String Title = "";
    public String Added = "";

    public String getAdded() {
        return Added;
    }

    public String getMap_Image_URL() {
        return Map_Image_URL;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return Title;
    }

    @Override
    public String toString() {
        return "map{" +
                "Map_Image_URL='" + Map_Image_URL + '\'' +
                ", SubTitle='" + SubTitle + '\'' +
                ", ID='" + ID + '\'' +
                ", Title='" + Title + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Map_Image_URL);
        dest.writeString(this.SubTitle);
        dest.writeString(this.ID);
        dest.writeString(this.Title);
        dest.writeString(this.Added);
    }

    public map() {
    }

    protected map(Parcel in) {
        this.Map_Image_URL = in.readString();
        this.SubTitle = in.readString();
        this.ID = in.readString();
        this.Title = in.readString();
        this.Added = in.readString();
    }

    public static final Parcelable.Creator<map> CREATOR = new Parcelable.Creator<map>() {
        @Override
        public map createFromParcel(Parcel source) {
            return new map(source);
        }

        @Override
        public map[] newArray(int size) {
            return new map[size];
        }
    };
}
