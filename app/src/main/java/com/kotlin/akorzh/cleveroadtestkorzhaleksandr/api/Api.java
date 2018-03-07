package com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api;


import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.model.Data;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api.ApiSettings.CATEGORY;
import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api.ApiSettings.COUNTRY;
import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api.ApiSettings.GET_NEWS;


/**
 * Created by akorzh on 18.10.2016.
 */
public interface Api {

    @GET(GET_NEWS)
    Flowable<Data> getNews(@Query(COUNTRY) String country,
                           @Query(CATEGORY) String category);

}
