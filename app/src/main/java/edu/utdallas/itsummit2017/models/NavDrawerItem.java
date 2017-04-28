package edu.utdallas.itsummit2017.models;

import android.graphics.drawable.Drawable;

/**
 * Created by sxk159231 on 4/7/2017.
 */

public class NavDrawerItem {

    String title = "";
    Drawable icon;

    public NavDrawerItem(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
