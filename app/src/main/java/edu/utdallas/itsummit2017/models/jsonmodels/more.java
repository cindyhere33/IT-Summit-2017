package edu.utdallas.itsummit2017.models.jsonmodels;

import io.realm.RealmObject;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class more extends RealmObject {

    String ID = "";
    private String Title = "";
    private String Image_URL = "";
    private String WebModule_URL = "";
    private String TextArea = "";
    private String Order = "";
    private String Added = "";

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

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getWebModule_URL() {
        return WebModule_URL;
    }

    public void setWebModule_URL(String webModule_URL) {
        WebModule_URL = webModule_URL;
    }

    public String getTextArea() {
        return TextArea;
    }

    public void setTextArea(String textArea) {
        TextArea = textArea;
    }

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    @Override
    public String toString() {
        return "more{" +
                "ID='" + ID + '\'' +
                ", Title='" + Title + '\'' +
                ", Image_URL='" + Image_URL + '\'' +
                ", WebModule_URL='" + WebModule_URL + '\'' +
                ", TextArea='" + TextArea + '\'' +
                ", Order='" + Order + '\'' +
                ", Added='" + Added + '\'' +
                '}';
    }
}
