package edu.utdallas.itsummit.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.utdallas.itsummit.R;
import edu.utdallas.itsummit.fragments.AboutFragment;
import edu.utdallas.itsummit.fragments.DetailPresenterFragment;
import edu.utdallas.itsummit.fragments.DetailSessionFragment;
import edu.utdallas.itsummit.fragments.ExhibitorFragment;
import edu.utdallas.itsummit.fragments.MapFragment;
import edu.utdallas.itsummit.fragments.NewsFragment;
import edu.utdallas.itsummit.fragments.PresenterFragment;
import edu.utdallas.itsummit.fragments.SessionFragment;
import edu.utdallas.itsummit.fragments.SupportFragment;
import edu.utdallas.itsummit.fragments.WelcomeFragment;
import edu.utdallas.itsummit.serverapi.DataGetter;
import edu.utdallas.itsummit.utils.Utils;

/**
 * Created by sxk159231 on 4/6/2017.
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    //Heading for Toolbar
    private final String EVENT = "Welcome", SESSION = "Schedule", PRESENTER = "Presenters", MAP = "Maps", NEWS = "News", ABOUT = "About", SUPPORT = "Support", MORE = "More", EXHIBITOR = "Exhibitors", SESSION_DETAIL = "Session Details", PRESENTER_DETAIL = "Presenter Details", NEWS_DETAIL = "Detail";

    private Toolbar toolbar;
    NavigationView navView;
    DrawerLayout drawerLayout;
    DataGetter dataGetter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navView = (NavigationView) findViewById(R.id.nav_view);
        if (dataGetter == null) dataGetter = new DataGetter(navView);
        dataGetter.downloadData();
        if (dataGetter.hasDataInRealm())
            navView.setNavigationItemSelectedListener(navItemSelectedListener);
        else {
            Utils.showNetworkConnectionError(this);
        }
        getSupportFragmentManager().addOnBackStackChangedListener(backStackChangedListener);

        //Set default fragment as the Welcome Fragment
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame, WelcomeFragment.newInstance(), EVENT).addToBackStack(EVENT).commit();
            navView.setCheckedItem(R.id.nav_events);
        }
    }


    //Clicking on the item in navigation drawer should open the respective fragment
    //We add fragments one above the other so that the user can navigate backward by pressing Back button
    NavigationView.OnNavigationItemSelectedListener navItemSelectedListener = menuItem -> {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Utils.hideKeyboard(findViewById(R.id.frame));
        switch (menuItem.getItemId()) {
            case R.id.nav_events:
                if (Utils.isTopMostFragment(fragmentManager, EVENT)) break;
                fragmentManager.beginTransaction().add(R.id.frame, WelcomeFragment.newInstance(), EVENT).addToBackStack(EVENT).commit();
                break;
            case R.id.nav_schedule:
                if (Utils.isTopMostFragment(fragmentManager, SESSION)) break;
                fragmentManager.beginTransaction().add(R.id.frame, SessionFragment.newInstance(), SESSION).addToBackStack(SESSION).commit();
                break;
            case R.id.nav_presenters:
                if (Utils.isTopMostFragment(fragmentManager, PRESENTER)) break;
                fragmentManager.beginTransaction().add(R.id.frame, PresenterFragment.newInstance(), PRESENTER).addToBackStack(PRESENTER).commit();
                break;
            case R.id.nav_news:
                if (Utils.isTopMostFragment(fragmentManager, NEWS)) break;
                fragmentManager.beginTransaction().add(R.id.frame, NewsFragment.newInstance(), NEWS).addToBackStack(NEWS).commit();
                break;
            case R.id.nav_map:
                if (Utils.isTopMostFragment(fragmentManager, MAP)) break;
                fragmentManager.beginTransaction().add(R.id.frame, MapFragment.newInstance(), MAP).addToBackStack(MAP).commit();
                break;
            case R.id.nav_exhibitor:
                if (Utils.isTopMostFragment(fragmentManager, EXHIBITOR)) break;
                fragmentManager.beginTransaction().add(R.id.frame, ExhibitorFragment.newInstance(), EXHIBITOR).addToBackStack(EXHIBITOR).commit();
                break;
            case R.id.nav_support:
                if (Utils.isTopMostFragment(fragmentManager, SUPPORT)) break;
                fragmentManager.beginTransaction().add(R.id.frame, SupportFragment.newInstance(), SUPPORT).addToBackStack(SUPPORT).commit();
                break;
            case R.id.nav_about:
                if (Utils.isTopMostFragment(fragmentManager, ABOUT)) break;
                fragmentManager.beginTransaction().add(R.id.frame, AboutFragment.newInstance(), ABOUT).addToBackStack(ABOUT).commit();
                break;
            default:
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    };


    //In case user presses back button the selection on navigation drawer needs to reflect it
    FragmentManager.OnBackStackChangedListener backStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            //If there are no fragments, do not show plain activity, instead close the app.
            if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
                finish();
                return;
            }
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame);
            String fragmentName = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
            setHeading(fragmentName);
            Log.d(TAG, "Fragment name = " + fragmentName);
            switch (fragmentName) {
                case EVENT:
                    navView.setCheckedItem(R.id.nav_events);
                    break;
                case SESSION:
                    if (fragment != null) ((SessionFragment) fragment).refreshData();
                    navView.setCheckedItem(R.id.nav_schedule);
                    break;
                case SESSION_DETAIL:
                    if (fragment != null) ((DetailSessionFragment) fragment).refreshData();
                    navView.setCheckedItem(R.id.nav_schedule);
                    break;
                case PRESENTER:
                    if (fragment != null) ((PresenterFragment) fragment).refreshData();
                    navView.setCheckedItem(R.id.nav_presenters);
                    break;
                case PRESENTER_DETAIL:
                    if (fragment != null) ((DetailPresenterFragment) fragment).refreshData();
                    navView.setCheckedItem(R.id.nav_presenters);
                    break;
                case MAP:
                    navView.setCheckedItem(R.id.nav_map);
                    break;
                case ABOUT:
                    navView.setCheckedItem(R.id.nav_about);
                    break;
                case SUPPORT:
                    navView.setCheckedItem(R.id.nav_support);
                    break;
                case EXHIBITOR:
                    navView.setCheckedItem(R.id.nav_exhibitor);
                    break;
                case NEWS:
                    if (fragment != null) ((NewsFragment) fragment).refreshNews();
                    navView.setCheckedItem(R.id.nav_news);
                    break;
                case NEWS_DETAIL:
                    navView.setCheckedItem(R.id.nav_news);
                    break;
            }
        }
    };


    //Set the toolbar title to this text
    public void setHeading(String title) {
        ((TextView) toolbar.findViewById(R.id.toolbarTitle)).setText(title);
    }


    //If there are no fragments, kill the activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getCurrentFocus() != null)
            Utils.hideKeyboard(getCurrentFocus());
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) finish();
    }


    //If the activity is destroyed, dispose all the observables(rxjava)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataGetter != null) dataGetter.getDisposables().dispose();
    }

    public DataGetter getDataGetter() {
        if (dataGetter == null) dataGetter = new DataGetter(navView);
        return dataGetter;
    }

    //Reset the heading on rotate
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String fragmentName = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        setHeading(fragmentName);
    }
}
