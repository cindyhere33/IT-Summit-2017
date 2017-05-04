package edu.utdallas.itsummit.serverapi;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import edu.utdallas.itsummit.adapters.CustomTypeAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sxk159231 on 3/24/2017.
 */

public class HttpClient {

    public static final String BASE_URL = "https://www.utdallas.edu/oit/mobileapps/ITEventApp/ws/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new CustomTypeAdapter().getGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static HttpInterface getInterface() {
        return getClient().create(HttpInterface.class);
    }
}