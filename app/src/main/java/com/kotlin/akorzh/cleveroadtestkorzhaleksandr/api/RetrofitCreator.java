package com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api;

import com.google.gson.GsonBuilder;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.global.Const;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.util.MyLogInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api.ApiSettings.API_KEY;
import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api.ApiSettings.KEY;

/**
 * Created by akorzh on 06.03.18.
 */

public class RetrofitCreator {


    public Retrofit createRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl.Builder builder1 = original.url().newBuilder();
            return chain.proceed(original.newBuilder().url(builder1.build()).build());
        });

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);

        builder.interceptors().add(chain -> {
            Request original = chain.request();
            HttpUrl.Builder httpBuilder = original.url().newBuilder();
            httpBuilder.addQueryParameter(API_KEY, KEY);
            return chain.proceed(original.newBuilder().url(httpBuilder.build()).build());
        });

        if (Const.LOG.equalsIgnoreCase(Const.RETROFIT_LOG)) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(Const.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            builder.interceptors().add(logInterceptor);
        } else if (Const.LOG.equalsIgnoreCase(Const.MY_LOG)) {
            MyLogInterceptor logInterceptor = new MyLogInterceptor();
            logInterceptor.setLevel(Const.DEBUG ? MyLogInterceptor.Level.BODY : MyLogInterceptor.Level.NONE);
            builder.addInterceptor(logInterceptor);
        }


        return new Retrofit.Builder()
                .baseUrl(ApiSettings.SERVER)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(createGsonConverter())
                .client(builder.build())
                .build();
    }

    private static GsonConverterFactory createGsonConverter() {
        GsonBuilder builder = new GsonBuilder();
        return GsonConverterFactory.create(builder.create());
    }
}