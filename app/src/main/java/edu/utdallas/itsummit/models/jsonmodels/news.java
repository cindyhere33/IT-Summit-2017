package edu.utdallas.itsummit.models.jsonmodels;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import edu.utdallas.itsummit.models.TimeAndDate;
import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class news extends RealmObject implements Comparable<news>, Parcelable {

    private String Image_URL = "";
    private String Message = "";
    public String ID = "";
    private String Title = "";
    private String News_Time = "09:00:00";
    private String News_Date = "2016-11-07";
    private String Added = "";

    public String getAdded() {
        return Added;
    }

    public String getNews_Time() {
        TimeAndDate completeTime = new TimeAndDate(News_Date, News_Time, News_Time);
        return completeTime.getNewsTime();
    }

    public String getImage_URL() {
        return Image_URL;
    }

    public String getMessage() {
        return Message;
    }

    public String getID() {
        return ID;
    }


    public String getTitle() {
        return Title;
    }


    public String getNews_Date() {
        return News_Date;
    }

    @Override
    public int compareTo(@NonNull news another) {
        return another.getNews_Time().compareTo(getNews_Time());
    }

    @Override
    public String toString() {
        return "news{" +
                "Image_URL='" + Image_URL + '\'' +
                ", Message='" + Message + '\'' +
                ", ID='" + ID + '\'' +
                ", Title='" + Title + '\'' +
                ", News_Time='" + News_Time + '\'' +
                ", News_Date='" + News_Date + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Image_URL);
        dest.writeString(this.Message);
        dest.writeString(this.ID);
        dest.writeString(this.Title);
        dest.writeString(this.News_Time);
        dest.writeString(this.News_Date);
        dest.writeString(this.Added);
    }

    public news() {
    }

    protected news(Parcel in) {
        this.Image_URL = in.readString();
        this.Message = in.readString();
        this.ID = in.readString();
        this.Title = in.readString();
        this.News_Time = in.readString();
        this.News_Date = in.readString();
        this.Added = in.readString();
    }

    public static final Parcelable.Creator<news> CREATOR = new Parcelable.Creator<news>() {
        @Override
        public news createFromParcel(Parcel source) {
            return new news(source);
        }

        @Override
        public news[] newArray(int size) {
            return new news[size];
        }
    };
}
