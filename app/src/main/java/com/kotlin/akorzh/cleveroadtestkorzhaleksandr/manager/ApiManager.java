package com.kotlin.akorzh.cleveroadtestkorzhaleksandr.manager;

import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api.Api;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api.RetrofitCreator;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.model.Articles;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.model.Data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Retrofit;


/**
 * Created by akorzh on 06.03.18.
 */

public class ApiManager {

    private Api api;

    public ApiManager() {
        Retrofit retrofit = new RetrofitCreator().createRetrofit();
        api = retrofit.create(Api.class);
    }

    public Flowable<List<Articles>> getNews(String country, String category) {
        return api.getNews(country, category)
                .map(Data::getArticles)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
