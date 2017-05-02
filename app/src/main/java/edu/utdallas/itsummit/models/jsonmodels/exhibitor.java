package edu.utdallas.itsummit.models.jsonmodels;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class exhibitor extends RealmObject implements Comparable<exhibitor>, Parcelable {

    public String ID = "";
    public String Company = "";
    public String Description = "";
    public String Contact = "";
    public String Email = "";
    public String Phone = "";
    public String Room = "";
    public String Added = "";

    public String getID() {
        return ID;
    }

    public String getCompany() {
        return Company;
    }

    public String getContact() {
        return Contact;
    }

    public String getDescription() {
        return Description;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAdded() {
        return Added;
    }

    public String getRoom() {
        return Room;
    }

    @Override
    public String toString() {
        return "exhibitor{" +
                "ID='" + ID + '\'' +
                ", Company='" + Company + '\'' +
                ", Description='" + Description + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Rooms='" + Room + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }

    @Override
    public int compareTo(exhibitor another) {
        return getCompany().compareTo(another.getCompany());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.Company);
        dest.writeString(this.Description);
        dest.writeString(this.Contact);
        dest.writeString(this.Email);
        dest.writeString(this.Phone);
        dest.writeString(this.Room);
        dest.writeString(this.Added);
    }

    public exhibitor() {
    }

    protected exhibitor(Parcel in) {
        this.ID = in.readString();
        this.Company = in.readString();
        this.Description = in.readString();
        this.Contact = in.readString();
        this.Email = in.readString();
        this.Phone = in.readString();
        this.Room = in.readString();
        this.Added = in.readString();
    }

    public static final Parcelable.Creator<exhibitor> CREATOR = new Parcelable.Creator<exhibitor>() {
        @Override
        public exhibitor createFromParcel(Parcel source) {
            return new exhibitor(source);
        }

        @Override
        public exhibitor[] newArray(int size) {
            return new exhibitor[size];
        }
    };
}
