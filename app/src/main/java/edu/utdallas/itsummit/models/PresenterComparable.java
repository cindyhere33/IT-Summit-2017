package edu.utdallas.itsummit.models;

import java.util.Comparator;

import edu.utdallas.itsummit.models.jsonmodels.presenter;

/**
 * Created by sxk159231 on 5/5/2017.
 */

public class PresenterComparable {


    public static Comparator<presenter> getComparator(String tabName) {
        switch (tabName) {
            case "LAST_NAME":
                return getLastNameComparator();
            case "FIRST_NAME":
                return getFirstNameComparator();
            case "COMPANY":
                return getCompanyComparator();
        }
        return getLastNameComparator();
    }

    private static Comparator<presenter> getLastNameComparator() {
        return (presenter, t1) -> presenter.getLast_Name().compareTo(t1.getLast_Name());
    }

    private static Comparator<presenter> getFirstNameComparator() {
        return (presenter, t1) -> presenter.getFirst_Name().compareTo(t1.getFirst_Name());
    }

    private static Comparator<presenter> getCompanyComparator() {
        return (presenter, t1) -> presenter.getCompany().compareTo(t1.getCompany());
    }

}
