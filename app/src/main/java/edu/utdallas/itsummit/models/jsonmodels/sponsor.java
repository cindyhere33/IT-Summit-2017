package edu.utdallas.itsummit.models.jsonmodels;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class sponsor extends RealmObject implements Parcelable, Comparable<sponsor> {

    private String Name = "";
    private String Description = "";
    public String ID = "";
    private String Logo_URL = "";
    private String Level = "";
    private String Web_URL = "";
    public String Added = "";

    public void setSponsorLevel(String sponsorLevel) {
        SponsorLevel = sponsorLevel;
    }

    private String SponsorLevel = "";

    public String getAdded() {
        return Added;
    }


    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getID() {
        return ID;
    }

    public String getLogo_URL() {
        return Logo_URL;
    }

    public String getLevel() {
        return Level;
    }

    public String getWeb_URL() {
        return Web_URL;
    }

    public String getSponsorLevel() {
        return SponsorLevel;
    }

    @Override
    public String toString() {
        return "sponsor{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", ID='" + ID + '\'' +
                ", Logo_URL='" + Logo_URL + '\'' +
                ", Level='" + Level + '\'' +
                ", Web_URL='" + Web_URL + '\'' +
                ", Added='" + Added + '\'' +
                ", SponsorLevels='" + SponsorLevel + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.Description);
        dest.writeString(this.ID);
        dest.writeString(this.Logo_URL);
        dest.writeString(this.Level);
        dest.writeString(this.Web_URL);
        dest.writeString(this.Added);
        dest.writeString(this.SponsorLevel);
    }

    public sponsor() {
    }

    protected sponsor(Parcel in) {
        this.Name = in.readString();
        this.Description = in.readString();
        this.ID = in.readString();
        this.Logo_URL = in.readString();
        this.Level = in.readString();
        this.Web_URL = in.readString();
        this.Added = in.readString();
        this.SponsorLevel = in.readString();
    }

    public static final Parcelable.Creator<sponsor> CREATOR = new Parcelable.Creator<sponsor>() {
        @Override
        public sponsor createFromParcel(Parcel source) {
            return new sponsor(source);
        }

        @Override
        public sponsor[] newArray(int size) {
            return new sponsor[size];
        }
    };

    @Override
    public int compareTo(@NonNull sponsor o) {
        return this.getName().compareTo(o.getName());
    }
}
