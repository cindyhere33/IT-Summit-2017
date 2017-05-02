package edu.utdallas.itsummit.models.jsonmodels;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import edu.utdallas.itsummit.models.RealmString;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class presenter extends RealmObject implements Comparable<presenter>, Parcelable {

    public String Image_URL = "";
    public String BIO = "";
    public String ID = "";
    public String Company = "";
    public String Title = "";
    public String Middle_Name = "";
    public String Last_Name = "";
    public String First_Name = "";
    public String Web_URL = "";
    public String Added = "";
    public RealmList<RealmString> Sessions = new RealmList<>();

    public String getImage_URL() {
        return Image_URL;
    }

    public String getBIO() {
        return BIO;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getMiddle_Name() {
        if (Middle_Name == null || Middle_Name.equalsIgnoreCase(null)) return "";
        return Middle_Name;
    }

    public String getID() {
        if (ID.startsWith("[")) return ID.substring(1).trim();
        else if (ID.startsWith("]")) return ID.substring(0, ID.length() - 1).trim();
        return ID.trim();
    }

    public String getCompany() {
        return Company;
    }

    public String getTitle() {
        return Title;
    }


    @Override
    public int compareTo(@NonNull presenter another) {
        return getTitle().compareTo(another.getTitle());
    }

    @Override
    public String toString() {
        return "presenter{" +
                "Image_URL='" + Image_URL + '\'' +
                ", BIO='" + BIO + '\'' +
                ", ID='" + ID + '\'' +
                ", Company='" + Company + '\'' +
                ", Title='" + Title + '\'' +
                ", Middle_Name='" + Middle_Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", First_Name='" + First_Name + '\'' +
                ", Web_URL='" + Web_URL + '\'' +
                ", Added='" + Added + '\'' +
                ", Sessions=" + Sessions +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Image_URL);
        dest.writeString(this.BIO);
        dest.writeString(this.ID);
        dest.writeString(this.Company);
        dest.writeString(this.Title);
        dest.writeString(this.Middle_Name);
        dest.writeString(this.Last_Name);
        dest.writeString(this.First_Name);
        dest.writeString(this.Web_URL);
        dest.writeString(this.Added);
        dest.writeList(this.Sessions);
    }

    public presenter() {
    }

    protected presenter(Parcel in) {
        this.Image_URL = in.readString();
        this.BIO = in.readString();
        this.ID = in.readString();
        this.Company = in.readString();
        this.Title = in.readString();
        this.Middle_Name = in.readString();
        this.Last_Name = in.readString();
        this.First_Name = in.readString();
        this.Web_URL = in.readString();
        this.Added = in.readString();
        this.Sessions = new RealmList<RealmString>();
        in.readList(this.Sessions, RealmString.class.getClassLoader());
    }

    public static final Parcelable.Creator<presenter> CREATOR = new Parcelable.Creator<presenter>() {
        @Override
        public presenter createFromParcel(Parcel source) {
            return new presenter(source);
        }

        @Override
        public presenter[] newArray(int size) {
            return new presenter[size];
        }
    };
}
