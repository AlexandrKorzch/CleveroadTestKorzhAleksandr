package com.kotlin.akorzh.cleveroadtestkorzhaleksandr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by akorzh on 07.03.18.
 */

public class DateUtil {

    private static final String YYYY_MM_DD = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static String formatDate(String expDate) { //2017-12-23T08:41:21Z
        String formattedDate = "";
        try {

            SimpleDateFormat yearFirstFormat = new SimpleDateFormat(YYYY_MM_DD, Locale.US);
            Date date = yearFirstFormat.parse(expDate);
            formattedDate =  new SimpleDateFormat(YEAR_MONTH_DAY, Locale.US).format(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            formattedDate =  expDate.split("T")[0];
        }
        return formattedDate;
    }


}
