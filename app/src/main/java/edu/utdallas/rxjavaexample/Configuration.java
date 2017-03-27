package edu.utdallas.rxjavaexample;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sxk159231 on 3/24/2017.
 */

public class Configuration {

    @SerializedName("config")
    private config config;

    public Configuration(config config) {
        this.config = config;
    }

    public config getConfig() {
        return config;
    }

    public void setConfig(config config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "config=" + config +
                '}';
    }
}
