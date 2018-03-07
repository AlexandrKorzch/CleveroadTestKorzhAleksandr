package com.kotlin.akorzh.cleveroadtestkorzhaleksandr.api;

/**
 * Created by akorzh on 06.03.18.
 */

class ApiSettings {

    /*server*/
    private static final String SCHEME = "https://";
    private static final String HOSTNAME = "newsapi.org/";
    public static final String SERVER = SCHEME + HOSTNAME;

    /*methods*/
    public static final String GET_NEWS = "v2/top-headlines";

    /*params*/
    public static final String COUNTRY = "country";
    public static final String CATEGORY = "category";
    public static final String API_KEY = "apiKey";

    /*apiKey*/
    public static final String KEY = "20f4273ddfff4caa89836ca9629de2af";

}
