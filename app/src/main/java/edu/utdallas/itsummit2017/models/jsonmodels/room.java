package edu.utdallas.itsummit2017.models.jsonmodels;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class room extends RealmObject implements Parcelable {

    private String Name = "";
    public String ID = "";
    private String Building = "";
    private String Floor = "";

    public String getName() {
        return Name;
    }

    public String getID() {
        return ID;
    }

    public String getBuilding() {
        return Building;
    }

    public String getFloor() {
        switch (Floor) {
            case "1":
                return "First Floor";
            case "2":
                return "Second Floor";
            case "3":
                return "Third Floor";
            case "4":
                return "Fourth Floor";
            case "5":
                return "Fifth Floor";
            case "6":
                return "Sixth Floor";
        }
        return Floor;
    }

    @Override
    public String toString() {
        return "room{" +
                "Name='" + Name + '\'' +
                ", ID='" + ID + '\'' +
                ", Building='" + Building + '\'' +
                ", Floor='" + Floor + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.ID);
        dest.writeString(this.Building);
        dest.writeString(this.Floor);
    }

    public room() {
    }

    protected room(Parcel in) {
        this.Name = in.readString();
        this.ID = in.readString();
        this.Building = in.readString();
        this.Floor = in.readString();
    }

    public static final Parcelable.Creator<room> CREATOR = new Parcelable.Creator<room>() {
        @Override
        public room createFromParcel(Parcel source) {
            return new room(source);
        }

        @Override
        public room[] newArray(int size) {
            return new room[size];
        }
    };
}
