package com.ali.rnp.nafis.view.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DecimalFormat;

public class Utils {

    public static boolean checkConnection(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo!=null && networkInfo.isConnected();

        if (isConnected){
            return true;
        }else {
            return false;
        }

    }

    public static String formatPrice(String price){

            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String priceFormatted = formatter.format(Integer.parseInt(price));
            return priceFormatted;

    }
}
