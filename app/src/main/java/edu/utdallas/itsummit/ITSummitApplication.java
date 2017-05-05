package edu.utdallas.itsummit;

import android.app.Application;
import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sxk159231 on 4/6/2017.
 */

public class ITSummitApplication extends Application {

    private static ITSummitApplication mInstance;

    public static Context getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        try {
            FirebaseMessaging.getInstance().subscribeToTopic("news");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}