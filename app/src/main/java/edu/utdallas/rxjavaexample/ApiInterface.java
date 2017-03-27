package edu.utdallas.rxjavaexample;



import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by sxk159231 on 3/24/2017.
 */

public interface ApiInterface {

    @GET("config.php")
    Call<Configuration> getConfig();

    @GET("config.php")
    Observable<Configuration> getConfig_rxJava();

    @GET
    Observable<Schedule> getSchedule(@Url String url);
}