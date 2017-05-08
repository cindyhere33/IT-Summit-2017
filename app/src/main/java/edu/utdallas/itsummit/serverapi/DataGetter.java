package edu.utdallas.itsummit.serverapi;

import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import edu.utdallas.itsummit.fragments.WelcomeFragment;
import edu.utdallas.itsummit.models.Buildings;
import edu.utdallas.itsummit.models.Configuration;
import edu.utdallas.itsummit.models.Events;
import edu.utdallas.itsummit.models.Exhibitors;
import edu.utdallas.itsummit.models.Maps;
import edu.utdallas.itsummit.models.MoreItems;
import edu.utdallas.itsummit.models.NewsItems;
import edu.utdallas.itsummit.models.Presenters;
import edu.utdallas.itsummit.models.Rooms;
import edu.utdallas.itsummit.models.Schedule;
import edu.utdallas.itsummit.models.SponsorLevels;
import edu.utdallas.itsummit.models.Sponsors;
import edu.utdallas.itsummit.models.SupportItems;
import edu.utdallas.itsummit.models.jsonmodels.more;
import edu.utdallas.itsummit.models.jsonmodels.session;
import edu.utdallas.itsummit.utils.VolleySingleton;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by sxk159231 on 4/6/2017.
 */

public class DataGetter {

    private NavigationView navView;
    private final String TAG = getClass().getName();
    CompositeDisposable disposables = new CompositeDisposable();
    private final String IMAGE_BASE_URL = "https://www.utdallas.edu/oit/mobileapps/TXBestEvent/images/";

    public enum DataType {
        CONFIG, BUILDING, EVENT, MAP, PRESENTER, ROOM, SESSION, SPONSOR, NEWS, SPONSORLEVEL, SUPPORT, EXHIBITOR, MORE
    }

    public DataGetter() {
    }

    public DataGetter(NavigationView navView) {
        this.navView = navView;
    }

    //To read from database. Pass datatype to get the json model
    public RealmObject readFromRealm(DataType type) {
        Realm realm = Realm.getDefaultInstance();
        switch (type) {
            case CONFIG: {
                RealmResults<Configuration> resultList = realm.where(Configuration.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case BUILDING: {
                RealmResults<Buildings> resultList = realm.where(Buildings.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case EVENT: {
                RealmResults<Events> resultList = realm.where(Events.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case PRESENTER: {
                RealmResults<Presenters> resultList = realm.where(Presenters.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case ROOM: {
                RealmResults<Rooms> resultList = realm.where(Rooms.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case SESSION: {
                RealmResults<Schedule> resultList = realm.where(Schedule.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case MAP: {
                RealmResults<Maps> resultList = realm.where(Maps.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case SPONSOR: {
                RealmResults<Sponsors> resultList = realm.where(Sponsors.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case NEWS: {
                RealmResults<NewsItems> resultList = realm.where(NewsItems.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case SPONSORLEVEL: {
                RealmResults<SponsorLevels> resultList = realm.where(SponsorLevels.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case SUPPORT: {
                RealmResults<SupportItems> resultList = realm.where(SupportItems.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case EXHIBITOR: {
                RealmResults<Exhibitors> resultList = realm.where(Exhibitors.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
            case MORE: {
                RealmResults<MoreItems> resultList = realm.where(MoreItems.class).findAll();
                if (resultList.size() > 0) {
                    return resultList.get(resultList.size() - 1);
                }
            }
            break;
        }
        realm.close();
        return null;
    }

    //Returns whether data has been stored in database or not
    public boolean hasDataInRealm() {
        return (readFromRealm(DataType.CONFIG) != null && readFromRealm(DataType.EVENT) != null && readFromRealm(DataType.SESSION) != null && readFromRealm(DataType.PRESENTER) != null && readFromRealm(DataType.MAP) != null);
    }


    //Modify the original schedule objects to put date and time in a usable format and store in database
    private String writeToRealm(Schedule response) {
        Log.d(TAG, "Received Session response: " + response);
        for (session sess : response.getSessions()) {
            sess.configureSessionDate();
        }
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(transaction -> transaction.copyToRealmOrUpdate(response));
        realm.close();
        return response.toString();
    }

    //Write More menu items into database and return the objects
    private MoreItems writeToRealm(MoreItems response) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(transaction -> transaction.copyToRealmOrUpdate(response));
        realm.close();
        return response;
    }

    //Write Exhibitor items into database and return the objects
    private Exhibitors writeToRealm(Exhibitors response) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(transaction -> transaction.copyToRealmOrUpdate(response));
        realm.close();
        return response;
    }

    //Write Events into database and return the objects
    private Events writeToRealm(Events response) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(transaction -> transaction.copyToRealmOrUpdate(response));
        realm.close();
        return response;
    }


    //Generic method to write the data objects into database
    private <T extends RealmObject> String writeToRealm(T response) {
        Log.d(TAG, "Received response: " + response);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(transaction -> transaction.copyToRealmOrUpdate(response));
        realm.close();
        return response.toString();
    }

    //Write config to database
    private <T extends RealmObject> Configuration writeConfigToRealm(Configuration response) {
        Log.d(TAG, "Received response: " + response);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(transaction -> transaction.copyToRealmOrUpdate(response));
        realm.close();
        return response;
    }

    //Method to map the type to the right observable for data download
    private Observable getRequestingObservable(String url, DataType type) {
        switch (type) {
            case BUILDING:
                return HttpClient.getInterface().getBuildingsData(url);
            case EVENT:
                return HttpClient.getInterface().getEventsData(url);
            case MAP:
                return HttpClient.getInterface().getMapsData(url);
            case PRESENTER:
                return HttpClient.getInterface().getPresentersData(url);
            case ROOM:
                return HttpClient.getInterface().getRoomsData(url);
            case SESSION:
                return HttpClient.getInterface().getSchedule(url);
            case SPONSOR:
                return HttpClient.getInterface().getSponsorsData(url);
            case NEWS:
                return HttpClient.getInterface().getNewsData(url);
            case SPONSORLEVEL:
                return HttpClient.getInterface().getSponsorLevelsData(url);
            case SUPPORT:
                return HttpClient.getInterface().getSupportData(url);
            case EXHIBITOR:
                return HttpClient.getInterface().getExhibitorData(url);
            case MORE:
                return HttpClient.getInterface().getMoreItemsData(url);
        }
        return null;
    }

    //Generic method to initiate server call to download data
    //Downloaded data is written to database and on success, success() method is called and on error, processError() method is called
    private <T extends RealmObject> void requestData(Observable<T> observable) {
        disposables.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(this::writeToRealm)
                .subscribe(this::success, this::processError));
    }

    //Method to download session data
    private void getSessionData(Observable<Schedule> observable) {
        disposables.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(this::writeToRealm)
                .subscribe(this::success, this::processError));
    }

    //Method to download More menu items
    private void getMoreData(Observable<MoreItems> observable) {
        disposables.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(this::writeToRealm)
                .subscribe(this::updateNavDrawer, this::processError));
    }

    //Method to download Exhibitors
    private void getExhibitorData(Observable<Exhibitors> observable) {
        disposables.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(this::writeToRealm)
                .subscribe(this::updateNavDrawerMenu, this::processError));
    }

    //Method to download Exhibitors
    private void getEventData(Observable<Events> observable) {
        disposables.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(this::writeToRealm)
                .subscribe(events -> WelcomeFragment.updateEvents(events), this::processError));
    }

    //Method to start downloading the config
    public void downloadData() {
        HttpClient.getInterface().getConfig().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .map(this::writeConfigToRealm)
                //  .map(this::readFromRealm)
                .subscribe(this::getDataFromConfig, this::processError);
    }

    //In case of an error, print the error stack trace
    private void processError(Throwable e) {
        e.printStackTrace();
    }

    //If data download is successful print the downloaded data object
    private void success(String response) {
        Log.d(TAG, "Successfully downloaded data" + response);
    }

    //Update the navigation drawer with the more menu items
    private void updateNavDrawer(MoreItems items) {
        RealmList<more> moreItems = items.getMores();
        if (navView != null) {
            final Menu menu = navView.getMenu();
            for (int i = 0; i < moreItems.size(); i++) {
//            for(int i=0;i<3;i++){
//                MenuItem item = menu.add("Text");
                MenuItem item = menu.add(moreItems.get(i).getTitle());
                VolleySingleton.getInstance().getImageLoader().get(IMAGE_BASE_URL + moreItems.get(i).getImage_URL(), new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        item.setIcon(new BitmapDrawable(response.getBitmap()));
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "MoreItem Image could not be loaded");
                    }
                });
            }
        }
    }

    //Show or hide Exhibitors in nav drawer
    private void updateNavDrawerMenu(Exhibitors exhibitors) {
        if (navView == null) return;
        if (exhibitors.getExhibitors().size() > 0) navView.getMenu().getItem(5).setVisible(true);
        else navView.getMenu().getItem(5).setVisible(false);
    }


    //Once config is downloaded, download the rest of the data and write to database
    private void getDataFromConfig(RealmObject object) {
        if (object == null) {
            object = readFromRealm(DataType.CONFIG);
            if (object == null) {
                processError(new Throwable("No data"));
                return;
            }
        }
        Configuration config = (Configuration) object;
        if (config.getConfig() == null) {
            processError(new Throwable("No data"));
            return;
        }
        if (config.getConfig().getEvent_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getEvent_URL(), DataType.EVENT));
        }
        if (config.getConfig().getBuilding_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getBuilding_URL(), DataType.BUILDING));
        }
        if (config.getConfig().getMap_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getMap_URL(), DataType.MAP));
        }
        if (config.getConfig().getPresenter_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getPresenter_URL(), DataType.PRESENTER));
        }
        if (config.getConfig().getRoom_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getRoom_URL(), DataType.ROOM));
        }
        if (config.getConfig().getSession_URL().length() > 0) {
            getSessionData(getRequestingObservable(config.getConfig().getSession_URL(), DataType.SESSION));
        }
        if (config.getConfig().getSponsor_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getSponsor_URL(), DataType.SPONSOR));
        }
        if (config.getConfig().getNews_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getNews_URL(), DataType.NEWS));
        }
        if (config.getConfig().getSponsorLevel_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getSponsorLevel_URL(), DataType.SPONSORLEVEL));
        }
        if (config.getConfig().getMore_URL().length() > 0) {
            getMoreData(getRequestingObservable(config.getConfig().getMore_URL(), DataType.MORE));
        }
        if (config.getConfig().getSupport_URL().length() > 0) {
            requestData(getRequestingObservable(config.getConfig().getSupport_URL(), DataType.SUPPORT));
        }
        if (config.getConfig().getExhibitor_URL().length() > 0) {
            getExhibitorData(getRequestingObservable(config.getConfig().getExhibitor_URL(), DataType.EXHIBITOR));
        } else {
            if (navView != null) {
                navView.getMenu().getItem(5).setVisible(false);
            }
        }
    }

    //Get disposables when required so you can dispose them if not needed. Prevents waiting on server calls when not needed
    public CompositeDisposable getDisposables() {
        return disposables;
    }

}
