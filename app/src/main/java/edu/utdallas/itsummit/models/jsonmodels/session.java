package edu.utdallas.itsummit.models.jsonmodels;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.utdallas.itsummit.models.RealmString;
import edu.utdallas.itsummit.models.TimeAndDate;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sxk159231 on 3/27/2017.
 */

public class session extends RealmObject implements Comparable<session>, Parcelable {

    private String Room = "";
    private String Description = "";
    private RealmList<RealmString> Presenters = new RealmList<>();
    public String ID = "";
    private String Start_Time = "";
    private String Session_Track = "";
    private String Title = "";
    private String End_Time = "";
    private Date startDate = null;
    private TimeAndDate timeAndDate = null;
    private room room = null;

    public TimeAndDate getTimeAndDate() {
        return timeAndDate;
    }

    public String getAdded() {
        return Added;
    }

    private String Session_Date = "";
    private String Added = "";

    public void configureSessionDate() {
        timeAndDate = new TimeAndDate(Session_Date, Start_Time, End_Time);
    }

    public String getRoomString() {
        return Room;
    }


    public room getRoom(List<room> roomsList) {
        for (room room : roomsList) {
            if (room.getName().equalsIgnoreCase(this.Room)) return room;
        }
        return room;
    }

    public String getDescription() {
        return Description;
    }

    public List<RealmString> getPresenters() {
        return Presenters;
    }


    public String getID() {
        return ID;
    }

    public String getSession_Track() {
        return Session_Track;
    }

    public String getTitle() {
        return Title;
    }


    @Override
    public int compareTo(@NonNull session another) {
        Date thisDate = timeAndDate.getStartDate(), anotherDate = another.timeAndDate.getStartDate();
        if (thisDate.compareTo(anotherDate) > 0) return 1;
        else if (thisDate.compareTo(anotherDate) == 0) {
            SimpleDateFormat sdf_endTime = new SimpleDateFormat("h:mm a");
            Date thisEnd = null, anotherEnd = null;
            try {
                thisEnd = sdf_endTime.parse(timeAndDate.getEndTime());
                anotherEnd = sdf_endTime.parse(another.timeAndDate.getEndTime());
                if (thisEnd.compareTo(anotherEnd) > 0) return -1;
                else if (thisEnd.compareTo(anotherEnd) == 0)
                    return getTitle().compareTo(another.getTitle());
                else return 1;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 1;
        } else return -1;
    }
//
//    @Override
//    public int compare(session lhs, session rhs) {
//        return lhs.compareTo(rhs);
//    }


    @Override
    public String toString() {
        return "session{" +
                "Room='" + Room + '\'' +
                ", Description='" + Description + '\'' +
                ", Presenters=" + Presenters +
                ", ID='" + ID + '\'' +
                ", Start_Time='" + Start_Time + '\'' +
                ", Session_Track='" + Session_Track + '\'' +
                ", Title='" + Title + '\'' +
                ", End_Time='" + End_Time + '\'' +
                ", startDate=" + startDate +
                ", timeAndDate=" + timeAndDate +
                ", room=" + room +
                ", Session_Date='" + Session_Date + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Room);
        dest.writeString(this.Description);
        dest.writeList(this.Presenters);
        dest.writeString(this.ID);
        dest.writeString(this.Start_Time);
        dest.writeString(this.Session_Track);
        dest.writeString(this.Title);
        dest.writeString(this.End_Time);
        dest.writeLong(this.startDate != null ? this.startDate.getTime() : -1);
        dest.writeParcelable(this.timeAndDate, flags);
        dest.writeParcelable(this.room, flags);
        dest.writeString(this.Session_Date);
        dest.writeString(this.Added);
    }

    public session() {
    }

    protected session(Parcel in) {
        this.Room = in.readString();
        this.Description = in.readString();
        this.Presenters = new RealmList<RealmString>();
        in.readList(this.Presenters, RealmString.class.getClassLoader());
        this.ID = in.readString();
        this.Start_Time = in.readString();
        this.Session_Track = in.readString();
        this.Title = in.readString();
        this.End_Time = in.readString();
        long tmpStartDate = in.readLong();
        this.startDate = tmpStartDate == -1 ? null : new Date(tmpStartDate);
        this.timeAndDate = in.readParcelable(TimeAndDate.class.getClassLoader());
        this.room = in.readParcelable(edu.utdallas.itsummit.models.jsonmodels.room.class.getClassLoader());
        this.Session_Date = in.readString();
        this.Added = in.readString();
    }

    public static final Parcelable.Creator<session> CREATOR = new Parcelable.Creator<session>() {
        @Override
        public session createFromParcel(Parcel source) {
            return new session(source);
        }

        @Override
        public session[] newArray(int size) {
            return new session[size];
        }
    };
}
