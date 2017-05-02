package edu.utdallas.itsummit.models.jsonmodels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class event extends RealmObject {


    private String Description = "";
    private String Start_Date = "";
    private String Details = "";
    private String Welcome_Image = "";
    private String SubTitle = "";
    public String ID = "";
    private String Location = "";
    private String End_Date = "";
    private String Title = "";
    private String Added = "";

    public String getAdded() {
        return Added;
    }

    public String getDescription() {
        return Description;
    }

    public String getStart_Date() {
        return getFormattedDate(Start_Date);
    }

    public String getDetails() {
        return Details;
    }

    public String getWelcome_Image() {
        return Welcome_Image;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public String getLocation() {
        return Location;
    }

    public String getID() {
        return ID;
    }

    public String getEnd_Date() {
        return getFormattedDate(End_Date);
    }

    public String getTitle() {
        return Title;
    }


    @Override
    public String toString() {
        return "event{" +
                "Description='" + Description + '\'' +
                ", Start_Date='" + Start_Date + '\'' +
                ", Details='" + Details + '\'' +
                ", Welcome_Image='" + Welcome_Image + '\'' +
                ", SubTitle='" + SubTitle + '\'' +
                ", ID='" + ID + '\'' +
                ", Location='" + Location + '\'' +
                ", End_Date='" + End_Date + '\'' +
                ", Title='" + Title + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }

    public String getEventDate() {
        try {
            if (getStart_Date().equalsIgnoreCase(getEnd_Date())) {
                return getStart_Date();
            } else {
                return getStart_Date() + " - " + getEnd_Date();
            }
        } catch (Exception e) {
            return "May 16, 2017";
        }
    }


    private String getFormattedDate(String date1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdd = new SimpleDateFormat("MMM dd, yyyy");
        return sdd.format(date);
    }

}

