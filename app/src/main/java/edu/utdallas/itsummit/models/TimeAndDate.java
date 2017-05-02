package edu.utdallas.itsummit.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 11/9/2016.
 */

public class TimeAndDate extends RealmObject implements Parcelable {


    public TimeAndDate() {
    }

    public TimeAndDate(String date, String start, String end) {
        setDate(date);
        setStartTime(start);
        setEndTime(end);
        setTimeAndDate();
    }

    private String weekDay = "";
    private String date = "";
    private String startTime = "";
    private String endTime = "";
    private Date startDate = null;
    private Date endDate = null;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    private void setTimeAndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat sdf_time = new SimpleDateFormat("h:mm a");
        Date date1 = null, date3 = null;
        try {
            date1 = sdf_time.parse(startTime);
            date3 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        startDate = dateTime(date3, date1);

        try {
            date1 = sdf_time.parse(endTime);
            date3 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        endDate = dateTime(date3, date1);
    }

    public String getWeekDay() {
        return weekDay;
    }

    private void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf_week = new SimpleDateFormat("EEEE");
        formatter.setLenient(false);
        Date d = null;
        try {
            d = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.date = sdf.format(d);
        setWeekDay(sdf_week.format(d));
    }

    public String getStartTime() {
        return startTime;
    }

    private void setStartTime(String startTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("kk:mm");
            date = df.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.startTime = sdf.format(date);
    }

    public String getEndTime() {
        return endTime;
    }

    private void setEndTime(String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("kk:mm");
            date = df.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.endTime = sdf.format(date);
    }

    private Date dateTime(Date date, Date time) {
        return new Date(
                date.getYear(), date.getMonth(), date.getDate(),
                time.getHours(), time.getMinutes(), time.getSeconds()
        );
    }

    public String getSessionGroupName() {
        return weekDay + " " + startTime;
    }

    public String getNewsTime() {
        if (startDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a, EEEE, MMMM dd, yyyy");
            return sdf.format(startDate);
        }
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.weekDay);
        dest.writeString(this.date);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeLong(this.startDate != null ? this.startDate.getTime() : -1);
        dest.writeLong(this.endDate != null ? this.endDate.getTime() : -1);
    }

    protected TimeAndDate(Parcel in) {
        this.weekDay = in.readString();
        this.date = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        long tmpStartDate = in.readLong();
        this.startDate = tmpStartDate == -1 ? null : new Date(tmpStartDate);
        long tmpEndDate = in.readLong();
        this.endDate = tmpEndDate == -1 ? null : new Date(tmpEndDate);
    }

    public static final Parcelable.Creator<TimeAndDate> CREATOR = new Parcelable.Creator<TimeAndDate>() {
        @Override
        public TimeAndDate createFromParcel(Parcel source) {
            return new TimeAndDate(source);
        }

        @Override
        public TimeAndDate[] newArray(int size) {
            return new TimeAndDate[size];
        }
    };
}


