package edu.utdallas.itsummit.models.jsonmodels;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 3/24/2017.
 */

public class config extends RealmObject {

    private String Version;
    private String Building_URL;
    private String Event_URL;
    private String Map_URL;
    private String Presenter_URL;
    private String Room_URL;
    private String Session_URL;
    private String Sponsor_URL;
    private String News_URL;
    private String SponsorLevel_URL;
    private String Support_URL;
    private String Exhibitor_URL;
    private String More_URL;

    public config() {
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getBuilding_URL() {
        return Building_URL;
    }

    public void setBuilding_URL(String building_URL) {
        Building_URL = building_URL;
    }

    public String getEvent_URL() {
        return Event_URL;
    }

    public void setEvent_URL(String event_URL) {
        Event_URL = event_URL;
    }

    public String getMap_URL() {
        return Map_URL;
    }

    public void setMap_URL(String map_URL) {
        Map_URL = map_URL;
    }

    public String getPresenter_URL() {
        return Presenter_URL;
    }

    public void setPresenter_URL(String presenter_URL) {
        Presenter_URL = presenter_URL;
    }

    public String getRoom_URL() {
        return Room_URL;
    }

    public void setRoom_URL(String room_URL) {
        Room_URL = room_URL;
    }

    public String getSession_URL() {
        return Session_URL;
    }

    public void setSession_URL(String session_URL) {
        Session_URL = session_URL;
    }

    public String getSponsor_URL() {
        return Sponsor_URL;
    }

    public void setSponsor_URL(String sponsor_URL) {
        Sponsor_URL = sponsor_URL;
    }

    public String getNews_URL() {
        return News_URL;
    }

    public void setNews_URL(String news_URL) {
        News_URL = news_URL;
    }

    public String getSponsorLevel_URL() {
        return SponsorLevel_URL;
    }

    public void setSponsorLevel_URL(String sponsorLevel_URL) {
        SponsorLevel_URL = sponsorLevel_URL;
    }

    public String getSupport_URL() {
        return Support_URL;
    }

    public void setSupport_URL(String support_URL) {
        Support_URL = support_URL;
    }

    public String getExhibitor_URL() {
        return Exhibitor_URL;
    }

    public void setExhibitor_URL(String exhibitor_URL) {
        Exhibitor_URL = exhibitor_URL;
    }

    public String getMore_URL() {
        return More_URL;
    }

    public void setMore_URL(String more_URL) {
        More_URL = more_URL;
    }



    @Override
    public String toString() {
        return "config{" +
                "Version='" + Version + '\'' +
                ", Building_URL='" + Building_URL + '\'' +
                ", Event_URL='" + Event_URL + '\'' +
                ", Map_URL='" + Map_URL + '\'' +
                ", Presenter_URL='" + Presenter_URL + '\'' +
                ", Room_URL='" + Room_URL + '\'' +
                ", Session_URL='" + Session_URL + '\'' +
                ", Sponsor_URL='" + Sponsor_URL + '\'' +
                ", News_URL='" + News_URL + '\'' +
                ", SponsorLevel_URL='" + SponsorLevel_URL + '\'' +
                ", Support_URL='" + Support_URL + '\'' +
                ", Exhibitor_URL='" + Exhibitor_URL + '\'' +
                ", More_URL='" + More_URL + '\'' +
                '}';
    }

}
