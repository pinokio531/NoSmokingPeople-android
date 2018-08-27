package com.uiappcenter.NoSmokingPeople.retrofit;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 성민 on 2018-03-09.
 */

public class ApplicationController extends Application{
    private static ApplicationController instance;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    //private static String baseUrl = "http://117.16.191.242:3829";
    private static String baseUrl = "http://inucafeteriaaws.us.to:3829";



    // ?ㅽ듃?뚰겕 ?쒕퉬??媛앹껜 ?좎뼵
    private NetworkService networkService;


    // ?몄뒪?댁뒪 媛앹껜 諛섑솚  ?? static ?덈뱶?먯꽌 static ?쇰줈 ?좎뼵??蹂?섎뒗 留ㅻ쾲 媛앹껜瑜??덈줈 ?앹꽦?섏? ?딆븘???ㅻⅨ ?≫떚鍮꾪떚?먯꽌
    // ?먯쑀濡?쾶 ?ъ슜媛?ν빀?덈떎.
    public static ApplicationController getInstance() {
        return instance;
    }

    // ?ㅽ듃?뚰겕?쒕퉬??媛앹껜 諛섑솚
    public NetworkService getNetworkService() {
        return networkService;
    }


    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    //?몄뒪?댁뒪 媛앹껜 珥덇린??
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
        buildService();

    }


    public void buildService() {
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();

        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        networkService = retrofit.create(NetworkService.class);
    }
}
