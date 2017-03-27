package edu.utdallas.rxjavaexample;

import java.util.Arrays;

/**
 * Created by sxk159231 on 3/27/2017.
 */

public class Schedule {

    session[] session;

    public Schedule(edu.utdallas.rxjavaexample.session[] session) {
        this.session = session;
    }

    public edu.utdallas.rxjavaexample.session[] getSession() {
        return session;
    }

    public void setSession(edu.utdallas.rxjavaexample.session[] session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "session=" + Arrays.toString(session) +
                '}';
    }
}
