package edu.utdallas.rxjavaexample;

import java.util.Arrays;

/**
 * Created by sxk159231 on 3/27/2017.
 */

public class session {

    private String ID;
    private String Title;
    private String Description;
    private String Session_Date;
    private String Start_Time;
    private String End_Time;
    private String Session_Track;
    private String Room;
    private String Added;
    private String[] Presenters;

    public session(String ID, String title, String description, String session_Date, String start_Time, String end_Time, String session_Track, String room, String added, String[] presenters) {
        this.ID = ID;
        Title = title;
        Description = description;
        Session_Date = session_Date;
        Start_Time = start_Time;
        End_Time = end_Time;
        Session_Track = session_Track;
        Room = room;
        Added = added;
        Presenters = presenters;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSession_Date() {
        return Session_Date;
    }

    public void setSession_Date(String session_Date) {
        Session_Date = session_Date;
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(String start_Time) {
        Start_Time = start_Time;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String end_Time) {
        End_Time = end_Time;
    }

    public String getSession_Track() {
        return Session_Track;
    }

    public void setSession_Track(String session_Track) {
        Session_Track = session_Track;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getAdded() {
        return Added;
    }

    public void setAdded(String added) {
        Added = added;
    }

    public String[] getPresenters() {
        return Presenters;
    }

    public void setPresenters(String[] presenters) {
        Presenters = presenters;
    }

    @Override
    public String toString() {
        return "session{" +
                "ID='" + ID + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Session_Date='" + Session_Date + '\'' +
                ", Start_Time='" + Start_Time + '\'' +
                ", End_Time='" + End_Time + '\'' +
                ", Session_Track='" + Session_Track + '\'' +
                ", Room='" + Room + '\'' +
                ", Added='" + Added + '\'' +
                ", Presenters=" + Arrays.toString(Presenters) +
                '}';
    }
}
