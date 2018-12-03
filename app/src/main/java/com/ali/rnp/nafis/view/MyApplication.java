package com.ali.rnp.nafis.view;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;


public class MyApplication extends Application {

    private static Typeface iranianSansFont;
    private static Typeface bYekanFont;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static Typeface getIranianSansFont(Context context){
        if (iranianSansFont==null){
            iranianSansFont=Typeface.createFromAsset(context.getAssets(), "fonts/iranSans.ttf");
        }
        return iranianSansFont;
    }

    public static Typeface getbYekanFont(Context context) {
        if(bYekanFont==null){
            bYekanFont=Typeface.createFromAsset(context.getAssets(), "fonts/byekan.ttf");
        }
        return bYekanFont;
    }
}
