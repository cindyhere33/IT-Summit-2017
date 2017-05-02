package edu.utdallas.itsummit.serverapi;


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
import io.reactivex.Observable;
import io.realm.RealmObject;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by sxk159231 on 3/24/2017.
 */

public interface HttpInterface {

    @GET("config.php")
    Observable<Configuration> getConfig();

    @GET
    Observable<Buildings> getBuildingsData(@Url String url);

    @GET
    Observable<Events> getEventsData(@Url String url);

    @GET
    Observable<Maps> getMapsData(@Url String url);

    @GET
    Observable<Presenters> getPresentersData(@Url String url);

    @GET
    Observable<Rooms> getRoomsData(@Url String url);

    @GET
    Observable<Sponsors> getSponsorsData(@Url String url);

    @GET
    Observable<NewsItems> getNewsData(@Url String url);

    @GET
    Observable<SponsorLevels> getSponsorLevelsData(@Url String url);

    @GET
    Observable<SupportItems> getSupportData(@Url String url);

    @GET
    Observable<Exhibitors> getExhibitorData(@Url String url);

    @GET
    Observable<MoreItems> getMoreItemsData(@Url String url);

    @GET
    Observable<Schedule> getSchedule(@Url String url);

    //   CONFIG, BUILDING, EVENT, MAP, PRESENTER, ROOM, SESSION, SPONSOR, NEWS, SPONSORLEVEL, SUPPORT, EXHIBITOR, MORE

    @GET
    Observable<RealmObject> makeHttpRequest(@Url String url);


}